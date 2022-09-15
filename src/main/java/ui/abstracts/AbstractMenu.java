package ui.abstracts;

import app.FlightManager;
import util.ConsoleUtil;
import util.MenuUtil;

public abstract class AbstractMenu {
  public void start(){
    while (FlightManager.isLoggedIn()){
      System.out.println(MenuUtil.mainMenuText);
      int input = ConsoleUtil.getInt(MenuUtil.menuMessage);
      switch (input){
        case 1 -> showOnlineBoard();
        case 2 -> showFlightInfo();
        case 3 -> searchAndBookFlight();
        case 4 -> cancelBooking();
        case 5 -> showMyFlights();
        case 6 -> help();
        case 7 -> logout();
        default -> System.out.println("Input is not in choices!");
      }
    }
  }

  protected abstract void logout();

  protected abstract void help();

  protected abstract void showMyFlights();

  protected abstract void cancelBooking();

  protected abstract void searchAndBookFlight();

  protected abstract void showFlightInfo();

  protected abstract void showOnlineBoard();

}
