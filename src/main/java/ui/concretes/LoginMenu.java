package ui.concretes;

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
    throw new RuntimeException("not implemented");
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
