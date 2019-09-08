const api = require('./api');
const passport = require('passport');

/**
 * GET /login
 * Login page.
 */
exports.getLogin = (req, res) => {
  console.info('login istegi->');

  if (req.user) {
    console.info('login istegi->');
    return res.redirect('/');
  }
  res.render('account/login', {
    title: 'Login'
  });
};

/**
 * GET /logout
 * Logout
 */
exports.logout = (req, res) =>{
  req.logout();
  req.session.destroy((err) => {
    if(err)
      console.log('Error : Failed to destroy the session during logout.', err);
    req.user = null;
    res.redirect('/');
  })
}

/**
 * GET /createAccount
 *
 */
exports.getNewUser = (req, res) => {
  res.render('account/signup', {
    title: 'Create Account'
  });
}

/**
 * POST /createAccount
 *
 */
exports.postNewUser = (req, res) => {
  const me = req.user;
  const user = req.body;


  api.postUser(me, user, function (data) {
    if (data) {
      if (data.error) {

        req.session.new_user = user;
        req.flash('errors', {msg: `${data.error}`});
        return res.redirect('/login');

      } else if (data.user) {

        req.session.new_user = {};
        req.flash('info', {msg: 'has been created', params: [data.user.username]});
        return res.redirect('/');

      }
    } else {

      req.session.new_user = user;
      req.flash('errors', {msg: 'An unknown error has occurred. Please contact us.'});
      return res.redirect('/');

    }
  });
}
/**
 * POST /login
 * Sign in using username and password.
 */
exports.postLogin = (req, res, next) => {
  req.assert('username', 'Username cannot be blank').notEmpty();
  req.assert('password', 'Password cannot be blank').notEmpty();

  const errors = req.validationErrors();

  if (errors) {
    req.flash('errors', errors);
    return res.redirect('/login');
  }

  passport.authenticate('local', (err, user, info) => {
    if (err) { return next(err); }
    if (!user) {
      req.flash('errors', info);
      return res.redirect('/login');
    }
    req.logIn(user, (err) => {
      if (err) { return next(err); }
      console.info('basariiii istegi->');
      req.flash('success', { msg: 'Success! You are logged in.' });
      res.redirect(req.session.returnTo || '/');
    });
  })(req, res, next);
};
