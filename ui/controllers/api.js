const request = require('request');
const jwt = require('jsonwebtoken');

const SECRET = "secret";
const TOKEN_PREFIX = "Bearer ";
const AUTHORITIES = "Authorities";

exports.login = (username, password, cb) => {
    var data = {
      username: username,
      password: password
    };

    console.info('request data-->', data);

    var options = {
      url: `${process.env.API_URL}${process.env.API_LOGIN}`,
      method: 'POST',
      json: data
    };

    request(options, function (error, response, data) {
      console.info('response data-->', data);

      if (response.statusCode == 200 && response.headers && response.headers.authorization) {
        var authorization = response.headers.authorization;
        var token = authorization.replace(TOKEN_PREFIX, '');

        jwt.verify(token, SECRET, function(err, claims) {
          if (!err && claims) {
            var authorities = claims[AUTHORITIES].split(',');

            return cb({
              username: username,
              authorities: authorities,
              authorization: authorization
            });
          } else {
            return cb(null);
          }
        });
      } else {
        return cb(null);
      }
    });
  };

  exports.getTodos = (me, cb) => {
    var data = {};

    console.info('request data -->', data);

    console.info('request headers --> ', me.authorization);
    const options = {
      url: `${process.env.API_URL}${process.env.API_TODO}`,
      method: 'GET',
      json: data,
      headers: {
        Authorization: me.authorization
      }
    };
    console.info('request url --> ',  options.url);
    request(options, function (error, response, data) {
      console.info('response data-->', data);
      return cb(data);
    });
  };

  /**
   *  create user
   * POST /user
   */
  exports.postUser = (me, user, cb)=>{
    user.role = 'admin';
    const data ={
      user: user
    };
    console.info('request data --> ', data);
    const options = {
      url: `${process.env.API_URL}${process.env.API_USER}`,
      method: 'POST',
      json: data
    };
    console.info('url:', options);
    request(options, function (error, response, data) {
      console.info('response data-->', data);
      return cb(data);
    });
  };

  /**
   * save todo
   * POST /todo
   */
exports.saveTodo = (me, todo, cb) => {
    todo.active='X';
    const data = {
      todo: todo
    };
    console.info('request data --> ', data);


  const options = {
    url: `${process.env.API_URL}${process.env.API_TODO}`,
    method: 'POST',
    json: data,
    headers: {
      Authorization: me.authorization
    }
  };

  request(options, function(err, response, data){
    console.info('response data --> ', data);
    return cb(data);
  });
};

/**
 * getTodo
 * GET  /{id}
 */
exports.getTodo = (me, todoId, cb) => {
  const data = {};

  console.info('request data-->', data);

  const options = {
    url: `${process.env.API_URL}${process.env.API_TODO}/${todoId}`,
    method: 'GET',
    json: data,
    headers: {
      Authorization: me.authorization
    }
  };

  request(options, function (error, response, data) {
    console.info('response data-->', data);
    return cb(data);
  });
};

/**
 * PUT /
 */
exports.putTodo = (me, todo, cb) => {
  const data = {
    todo: todo
  };

  console.info('request data --> ', data);

  const options = {
    url: `${process.env.API_URL}${process.env.API_TODO}/${todo.id}`,
    method: 'PUT',
    json: data,
    headers: {
      Authorization: me.authorization
    }
  };
  request(options, function (error, response, data) {
    console.info('response data-->', data);
    return cb(data);
  });
}

/**
 * DELETE /
 */
exports.deleteTodo = (me, todoId, cb) => {
  const data = {};
  console.info('request data --> ', data);

  const options = {
    url: `${process.env.API_URL}${process.env.API_TODO}/${todoId}`,
    method: 'DELETE',
    json: data,
    headers: {
      Authorization: me.authorization
    }
  };
  request(options, function (error, response, data) {
    console.info('response data-->', data);
    return cb(data);
  });
}
