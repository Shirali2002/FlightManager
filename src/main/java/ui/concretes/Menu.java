package ui.concretes;


import app.FlightManager;
import command.FlightInfo;
import command.UserBooking;
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
    UserBooking.showUserBooking();
  }

  @Override
  protected void cancelBooking() {
    throw new RuntimeException("not implemented");
  }

  @Override
  protected void searchAndBookFlight() {
    throw new RuntimeException("not implemented");
  }

  @Override
  protected void showFlightInfo() {
    FlightInfo.displayFlightInfo();
  }

  @Override
  protected void showOnlineBoard() {
    throw new RuntimeException("not implemented");
  }
}
