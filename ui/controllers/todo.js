const api = require('./api');

/**
 * GET /todos
 * Todos page.
 */
exports.getTodos = (req, res) => {

    return res.render('todo/todos');
  };
