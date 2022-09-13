package service;


public class LoginService {
  public static final LoginService loginService = new LoginService();

  private LoginService() {
  }

  public static LoginService getInstance() {
    return loginService;
  }

}
