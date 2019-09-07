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
            console.info('hellllllllllllllllllll', data.todos)
            return res.render('todo/todos', {title: 'Todos', todos: data.todos});
          }
        }
      })
    }
  };
