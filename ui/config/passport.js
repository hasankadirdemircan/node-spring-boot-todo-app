const api = require('../controllers/api');
const passport = require('passport');

const { Strategy: LocalStrategy } = require('passport-local');

const util = require('../utils/util');

passport.serializeUser(function(user, done) {
  done(null, user);
});

passport.deserializeUser(function(user, done) {
  done(null, user);
});

/**
 * Sign in using Email and Password.
 */
passport.use(new LocalStrategy({ usernameField: 'username' }, (username, password, done) => {
    api.login(username, password, function(user) {
      if (!user) {
        return done(null, false, {msg: 'Invalid username or password.'});
      } else {
        return done(null, user);
      }
    });
  }));


/**
 * Login Required middleware.
 */
exports.isAuthenticated = (req, res, next) => {
    if (req.isAuthenticated()) {
      return next();
    }
    res.redirect('/login');
  };

  /**
 * Authorization Required middleware.
 */
exports.isAdmin = (req, res, next) => {
    if (util.hasRole(req.user, ['ADMIN'])) {
      next();
    } else {
      res.redirect('/403');
    }
  };
  exports.isSuperUser = (req, res, next) => {
    if (util.hasRole(req.user, ['SUPER_USER'])) {
      next();
    } else {
      res.redirect('/403');
    }
  };
