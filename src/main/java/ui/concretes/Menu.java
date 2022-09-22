package ui.concretes;

import app.FlightManager;
import command.CancelTicket;
import command.FlightInfo;
import command.Help;
import command.MyBookingInfo;
import command.OnlineBoard;
import command.SearchAndBookFlight;
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
