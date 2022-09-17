package dao;

import database.DB;
import model.User;
import util.DatabaseUtil;

import java.util.HashMap;
import java.util.Optional;

public class UserRepository implements DAO<User> {
  public static final UserRepository userRepo = new UserRepository();

  private UserRepository() {
  }

  public static UserRepository getInstance() {
    return userRepo;
  }

  @Override
  public Optional<HashMap<Integer, User>> getAll() {
    return DatabaseUtil.getAll(DB.USER_DB);
  }

  @Override
  public boolean save(HashMap<Integer, User> data) {
    return DatabaseUtil.save(data, DB.USER_DB);
  }

  @Override
  public Optional<User> getById(int id) {
    return DatabaseUtil.getById(id, DB.USER_DB);
  }

  @Override
  public boolean deleteById(int id) {
    return DatabaseUtil.deleteById(id, DB.USER_DB);
  }

  @Override
  public boolean add(int id, User value) {
    return DatabaseUtil.add(id, value, DB.USER_DB);
  }

  @Override
  public boolean updateById(int id, User value) {
    if (getById(id).isEmpty()){
      return false;
    }
    value.setId(id);
    return DatabaseUtil.updateById(id, value, DB.USER_DB);
  }
}
