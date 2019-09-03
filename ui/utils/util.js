exports.exactMatch = function(regx, str) {
  var match = str.match(regx);
  return match && str === match[0];
};
exports.hasRole = function(user, roles) {
  if (user && user.authorities && roles) {
    for (r = 0; r < roles.length; r++) {
      if (user.authorities.indexOf(roles[r]) != -1) {
        return true;
      }
    }
  }
  return false;
};