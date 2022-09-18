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
    throw new RuntimeException("not implemented");
  }

  @Override
  protected void showMyFlights() {
    MyBookingInfo.showUserBooking();
  }

  @Override
  protected void cancelBooking() {
    CancelTicket.cancelTicket();
  }

  @Override
  protected void searchAndBookFlight() {
    SearchAndBookFlight.searchAndBookFlight();
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
