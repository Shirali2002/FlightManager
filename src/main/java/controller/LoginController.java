package controller;

import exception.StrongPasswordException;
import exception.UniqueUsernameException;
import service.LoginService;

public class LoginController {
  public static final LoginController loginController = new LoginController();
  private final LoginService loginService = LoginService.getInstance();

  private LoginController() {
  }

  public static LoginController getInstance() {
    return loginController;
  }

  public boolean register(String name, String surname, String username, String password)
      throws UniqueUsernameException, StrongPasswordException {
    return loginService.register(name, surname, username, password);
  }

  public boolean login(String username, String password) {
    return loginService.login(username, password);
  }

  public void logout(){
    loginService.logout();
  }
}
