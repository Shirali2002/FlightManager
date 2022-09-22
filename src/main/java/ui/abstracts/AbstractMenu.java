package ui.abstracts;

import app.FlightManager;
import console.RealConsole;
import util.ConsoleUtil;
import util.MenuUtil;

public abstract class AbstractMenu {
  public void start(){
    while (FlightManager.isLoggedIn()){
      System.out.println(MenuUtil.mainMenuText);
      int input = ConsoleUtil.getInt(MenuUtil.menuMessage, new RealConsole());
      switch (input){
        case 1 -> showOnlineBoard();
        case 2 -> showFlightInfo();
        case 3 -> searchFlight();
        case 4 -> bookFlight();
        case 5 -> cancelBooking();
        case 6 -> showMyFlights();
        case 7 -> help();
        case 8 -> logout();
        default -> System.out.println("Input is not in choices!");
      }
    }
  }

  protected abstract void bookFlight();

  protected abstract void searchFlight();

  protected abstract void logout();

  protected abstract void help();

  protected abstract void showMyFlights();

  protected abstract void cancelBooking();

  protected abstract void showFlightInfo();

  protected abstract void showOnlineBoard();

}
