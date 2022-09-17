package ui.concretes;

import app.FlightManager;
import command.FlightInfo;
import command.Login;
import command.OnlineBoard;
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
    FlightManager.getInstance().stop();
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
    FlightInfo.displayFlightInfo();
  }

  @Override
  protected void showOnlineBoard() {
    OnlineBoard.showOnlineBoard();
  }

  @Override
  protected void login() {
    Login.login();
  }
}
