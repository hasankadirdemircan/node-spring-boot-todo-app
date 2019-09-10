const api = require('./api');

/**
 * GET /todos
 * Todos page.
 */
exports.getTodos = (req, res) => {
    const todoId = req.query.id || null;
    if(todoId != null){
      //TODO: todoId'ye göre veri çek.
      console.info('rendeeerrrrrr data --> ');
      api.getTodo(req.user, todoId, function(data){
        console.info('responseeee -->', data);
        if(data){
          if(data.error){
            req.flash('errors', {msg:data.error});
            return res.render('todo/todos', {title: 'Todos'});
          }else{
            return res.render('todo/todo', {title: 'Todo', todo: data.todo || {}});
          }
        }
      })
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
        return res.redirect('/todo?id=' + data.todo.id);
      }
    }
  })
}

/**
 * PUT
 */
exports.putTodo = (req, res) => {
  const todoId = req.query.id || null;
  const me = req.user;
  const todo = req.body;
  todo.id = todoId;
  todo.active = 'X'
  console.info('putttttttttttttttt data --> ');
  req.session.todo = todo;

  api.putTodo(me, todo, function(data){
    if(data){
      if(data.error){
        req.flash('errors', {msg: `${data.error}`});
        return res.redirect('todo/todo', {title: 'Todo', todo: req.session.todo || {}});
      }else if(data.todo){
        req.flash('info', {msg: '# has been updated.', params: [data.todo.todoId]});
        return res.redirect('/todo?id=' + data.todo.id);
      }
    }else{
      req.flash('errors', {msg: 'An unknown error has occurred. Please contact us.'});
      return res.redirect('todo/todo', {title: 'Todo', todo: req.session.todo || {}});
    }
  });
};
