package ui.abstracts;

import app.FlightManager;
import util.ConsoleUtil;
import util.MenuUtil;

public abstract class AbstractLoginMenu {
  public void start() {
    while (FlightManager.isAppOnline()) {
      System.out.println(MenuUtil.loginMenuText);
      int input = ConsoleUtil.getInt(MenuUtil.menuMessage);
      switch (input) {
        case 1 -> login();
        case 2 -> showOnlineBoard();
        case 3 -> showFlightInfo();
        case 4 -> register();
        case 5 -> help();
        case 6 -> exit();
        default -> System.out.println("Input is not in choices!");
      }
    }
  }

  protected abstract void exit();

  protected abstract void help();

  protected abstract void register();

  protected abstract void showFlightInfo();

  protected abstract void showOnlineBoard();

  protected abstract void login();

}
