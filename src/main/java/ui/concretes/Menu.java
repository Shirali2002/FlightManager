package ui.concretes;

import app.FlightManager;
import command.*;
import ui.abstracts.AbstractMenu;

public class Menu extends AbstractMenu {
  public static final Menu menu = new Menu();

  private Menu() {
  }

  public static Menu getInstance() {
    return menu;
  }

  @Override
  protected void logout() {
    FlightManager.getInstance().logout();
  }

  @Override
  protected void help() {
    Help.displayMainMenuHelp();
  }

  @Override
  protected void showMyFlights() {
    MyBookingInfo.showUserBooking();
  }

  @Override
  protected void cancelBooking() {
    CancelTicket.cancelTicketForUser();
  }

  @Override
  protected void bookFlight() {
    BookFlight.getFlightAndBookFlight();
  }

  @Override
  protected void searchFlight() {
    SearchFlight.searchAndDisplayFlights();
  }

  @Override
  protected void showFlightInfo() {
    FlightInfo.displayFlightInfo();
  }

  @Override
  protected void showOnlineBoard() {
    OnlineBoard.showOnlineBoard();
  }
}
