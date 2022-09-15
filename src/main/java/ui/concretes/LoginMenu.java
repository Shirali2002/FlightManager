package ui.concretes;

import command.Register;
import ui.abstracts.AbstractLoginMenu;

public class LoginMenu extends AbstractLoginMenu {
  public static final LoginMenu loginMenu = new LoginMenu();

  private LoginMenu() {
  }

  public static LoginMenu getInstance() {
    return loginMenu;
  }
  @Override
  protected void exit() {
    throw new RuntimeException("not implemented");
  }

  @Override
  protected void help() {
    throw new RuntimeException("not implemented");
  }

  @Override
  protected void register() {
    if (Register.register()){
      System.out.println("Register is successful.");
    } else {
      System.out.println("Register is not successful.");
    }
  }

  @Override
  protected void showFlightInfo() {
    throw new RuntimeException("not implemented");
  }

  @Override
  protected void showOnlineBoard() {
    throw new RuntimeException("not implemented");
  }

  @Override
  protected void login() {
    throw new RuntimeException("not implemented");
  }
}
