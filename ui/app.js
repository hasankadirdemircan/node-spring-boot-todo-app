/**
 * Module dependencies.
 */
const express = require('express');
const compression = require('compression');
const session = require('express-session');
const bodyParser = require('body-parser');
const logger = require('morgan');
const cookieParser = require('cookie-parser');
const chalk = require('chalk');
const errorHandler = require('errorhandler');
const lusca = require('lusca');
const dotenv = require('dotenv');
const MongoStore = require('connect-mongo')(session);
const flash = require('express-flash');
const path = require('path');
const mongoose = require('mongoose');
const passport = require('passport');
const expressValidator = require('express-validator');
const expressStatusMonitor = require('express-status-monitor');
const sass = require('node-sass-middleware');
const multer = require('multer');
const i18n = require("i18n");

const upload = multer({ dest: path.join(__dirname, 'uploads') });
const util = require('./utils/util');
/**
 * Load environment variables from .env file, where API keys and passwords are configured.
 */
/**
 * Load environment variables from .env file, where API keys and passwords are configured.
 */
  dotenv.load({ path: './env/dev.env' });


/**
 * Controllers (route handlers).
 */
const userController = require('./controllers/user');
const todoController = require('./controllers/todo');


/**
 * API keys and Passport configuration.
 */
const passportConfig = require('./config/passport');

/**
 * Create Express server.
 */
const app = express();

/**
 * Express configuration.
 */
app.set('host', process.env.OPENSHIFT_NODEJS_IP || '0.0.0.0');
app.set('port', process.env.PORT || process.env.OPENSHIFT_NODEJS_PORT || 8085);
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');
app.use(expressStatusMonitor());
app.use(compression());
app.use(sass({
  src: path.join(__dirname, 'public'),
  dest: path.join(__dirname, 'public')
}));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(expressValidator());
app.use(cookieParser());
app.use(session({
  resave: true,
  saveUninitialized: true,
  secret: process.env.SESSION_SECRET,
  cookie: { maxAge: 1209600000 }, // two weeks in milliseconds
}));
app.use(passport.initialize());
app.use(passport.session());
app.use(flash());
/*app.use(session({
  resave: true,
  saveUninitialized: true,
  secret: process.env.SESSION_SECRET,
  cookie: { maxAge: 1209600000 }, // two weeks in milliseconds
  store: new MongoStore({
    url: process.env.MONGODB_URI,
    autoReconnect: true,
  })
}));
*/

/*app.use((req, res, next) => {
  if (req.path === '/api/upload') {
    // Multer multipart/form-data handling needs to occur before the Lusca CSRF check.
    next();
  } else {
    lusca.csrf()(req, res, next);
  }
});*/
app.use(lusca.xframe('SAMEORIGIN'));
app.use(lusca.xssProtection(true));
app.disable('x-powered-by');
app.use((req, res, next) => {
  res.locals.user = req.user;
  next();
});


/*app.use(i18n.init);
app.use((req, res, next) => {
  var locale = req.cookies['_locale'];

  if (!locale) {
    locale = process.env.DEFAULT_LOCALE;
  }
  if (req.query &&
    req.query.lang &&
    acceptedLocales.includes(req.query.lang)) {
    locale = req.query.lang;
  }

  res.cookie('_locale', locale, { maxAge: 31557600000, httpOnly: true });
  res.setLocale(locale);
  next();
});

*/
app.use('/', express.static(path.join(__dirname, 'public'), { maxAge: 31557600000 }));
app.use('/js/lib', express.static(path.join(__dirname, 'node_modules/chart.js/dist'), { maxAge: 31557600000 }));
app.use('/js/lib', express.static(path.join(__dirname, 'node_modules/popper.js/dist/umd'), { maxAge: 31557600000 }));
app.use('/js/lib', express.static(path.join(__dirname, 'node_modules/bootstrap/dist/js'), { maxAge: 31557600000 }));
app.use('/js/lib', express.static(path.join(__dirname, 'node_modules/jquery/dist'), { maxAge: 31557600000 }));
app.use('/webfonts', express.static(path.join(__dirname, 'node_modules/@fortawesome/fontawesome-free/webfonts'), { maxAge: 31557600000 }));


app.locals.message = function(msg, params = []) {
  if (params && params.length > 0) {
    for (i=0; i < params.length; i++) {
      msg = msg.replace('#', params[i]);
    }
  }
  return msg;
};

app.locals.hasRole = function(user, roles) {
  return util.hasRole(user, roles);
};
/**
 * Primary app routes.
 */
app.get('/', passportConfig.isAuthenticated, todoController.getTodos);
app.get('/todo', passportConfig.isAuthenticated, todoController.getTodos);

app.get('/todo/new', passportConfig.isAuthenticated, todoController.getSaveTodo);
app.post('/todo/new',passportConfig.isAuthenticated, todoController.postSaveTodo);

app.get('/login', userController.getLogin);
app.post('/login', userController.postLogin);

app.get('/signup', userController.getNewUser);
app.post('/signup', userController.postNewUser);

app.get('/logout', passportConfig.isAuthenticated, userController.logout);

/**
 * Error Handler.
 */
if (process.env.NODE_ENV === 'development') {
  // only use in development
  app.use(errorHandler());
} else {
  app.use((err, req, res, next) => {
    console.error(err);
    res.status(500).send('Server Error');
  });
}

/**
 * Start Express server.
 */
app.listen(app.get('port'), () => {
  console.log('%s App is running at http://localhost:%d in %s mode', chalk.green('✓'), app.get('port'), app.get('env'));
  console.log('  Press CTRL-C to stop\n');
});

module.exports = app;
