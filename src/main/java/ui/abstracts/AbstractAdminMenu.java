package ui.abstracts;

import app.FlightManager;
import console.RealConsole;
import util.ConsoleUtil;
import util.MenuUtil;

public abstract class AbstractAdminMenu {
  public void start() {
    while (FlightManager.isAdminLoggedIn()) {
      System.out.println(MenuUtil.adminMenuText);
      int input = ConsoleUtil.getInt(MenuUtil.menuMessage, new RealConsole());
      switch (input) {
        case 1 -> addNewFlight();
        case 2 -> cancelFlight();
        case 3 -> removeUser();
        case 4 -> removeTicket();
        case 5 -> generateTestFlightData();
        case 6 -> showPassengerListByFlightId();
        case 7 -> logout();
        default -> System.out.println("Input is not in choices!");
      }
    }
  }

  protected abstract void showPassengerListByFlightId();

  protected abstract void generateTestFlightData();

  protected abstract void logout();

  protected abstract void removeTicket();

  protected abstract void removeUser();

  protected abstract void cancelFlight();

  protected abstract void addNewFlight();
}
