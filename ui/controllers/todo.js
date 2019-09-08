const api = require('./api');

/**
 * GET /todos
 * Todos page.
 */
exports.getTodos = (req, res) => {
    const todoId = req.query.id || null;
    if(todoId != null){
      //TODO: todoId'ye göre veri çek.
    }else{
      req.session.todo = {};
      api.getTodos(req.user, function (data) {
        if(data){
          if(data.error){
            req.flash('errors', {msg: data.error});
            return res.render('todo/todos');
          }else{
            return res.render('todo/todos', {title: 'Todos', todos: data.todos});
          }
        }
      })
    }
  };

/**
 * GET /todo
 */
exports.getSaveTodo = (req, res) => {
  if (!req.query || !req.query.action || 'save' != req.query.action) {
    req.session.todo = {};
  }
  return res.render('todo/todo', {title: 'Todo', todo: req.session.todo || {}});
};
  /**
   * POST /todo
   */
exports.postSaveTodo = (req, res) => {
  const me = req.user;
  const todo = req.body;

  req.session.todo = todo;

  api.saveTodo(me, todo, function(data){
    if(data){
      if(data.error){
        req.flash('errors', {msg: `${data.error}`});
      }else if (data.todo) {
        console.info('rendeeerrrrrr data --> ', data);
        req.flash('info', {msg: 'has been created.', params: [data.todo]});
        return res.render('todo/todos', {title: 'Todos', todos: data.todo});
      }
    }
  })
}
