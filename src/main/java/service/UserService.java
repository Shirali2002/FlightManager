package service;


import app.FlightManager;
import dao.DAO;
import dao.UserRepository;
import exception.NoSuchUserException;
import exception.StrongPasswordException;
import exception.UniqueUsernameException;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
  public static final UserService USER_SERVICE = new UserService();
  private final DAO<User> userDao = UserRepository.getInstance();

  private UserService() {
  }

  public static UserService getInstance() {
    return USER_SERVICE;
  }

  public boolean login(String username, String password){
    List<User> userList = userDao.getAll().orElse(new HashMap<>())
        .values().stream()
        .filter(u ->
               Objects.equals(u.getUsername(), username)
            && Objects.equals(u.getPassword(), password)
        ).collect(Collectors.toList());

    if (userList.size() == 1 && FlightManager.getLoggedInUserId() == -1){
      User user = userList.get(0);
      FlightManager.getInstance().login(user.getId());

      return true;
    } else {
      return false;
    }
  }

  public void logout(){
    FlightManager.getInstance().logout();
  }

  public boolean register(String name, String surname, String username, String password)
      throws UniqueUsernameException, StrongPasswordException {

    if (password.length() < 6 || !isPasswordStrong(password)) {
      throw new StrongPasswordException();
    } else if (!isUsernameUnique(username)) {
      throw new UniqueUsernameException();
    }  else {
      User newUser = new User(name, surname, username, password);
      return userDao.updateById(newUser.getId(), newUser);
    }
  }

  public HashMap<Integer, User> getAllUser(){
    return userDao.getAll().orElse(new HashMap<>());
  }

  public User getUserById(int id) throws NoSuchUserException{
    Optional<User> user = userDao.getById(id);
    if (user.isPresent()){
      return user.get();
    } else {
      throw new NoSuchUserException();
    }
  }


  private boolean isUsernameUnique(String username) {
    DAO<User> userDAO = UserRepository.getInstance();
    return userDAO.getAll().orElse(new HashMap<>())
        .values().stream()
        .noneMatch(u -> Objects.equals(u.getUsername(), username));
  }

  private boolean isPasswordStrong(String password) {
    boolean hasLower = false;
    boolean hasUpper = false;
    boolean hasDigit = false;

    for (char c : password.toCharArray()) {
      if (Character.isLowerCase(c)) {
        hasLower = true;
      } else if (Character.isUpperCase(c)) {
        hasUpper = true;
      } else if (Character.isDigit(c)) {
        hasDigit = true;
      }
    }

    return hasLower && hasUpper && hasDigit;
  }
}
