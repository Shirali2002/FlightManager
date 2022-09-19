package controller;

import exception.NoSuchUserException;
import exception.StrongPasswordException;
import exception.UniqueUsernameException;
import model.User;
import service.UserService;

import java.util.HashMap;
import java.util.Optional;

public class UserController {
  private static final UserController USER_CONTROLLER = new UserController();
  private final UserService userService = UserService.getInstance();

  private UserController() {
  }

  public static UserController getInstance() {
    return USER_CONTROLLER;
  }

  public boolean register(String name, String surname, String username, String password)
      throws UniqueUsernameException, StrongPasswordException {
    return userService.register(name, surname, username, password);
  }

  public boolean login(String username, String password) {
    return userService.login(username, password);
  }

  public void logout(){
    userService.logout();
  }

  public HashMap<Integer, User> getAllUser(){
    return userService.getAllUser();
  }

  public User getUserById(int id) throws NoSuchUserException {
    return userService.getUserById(id);
  }

  public boolean deleteUserById(int id) throws NoSuchUserException {
    return userService.deleteUserById(id);
  }

}
