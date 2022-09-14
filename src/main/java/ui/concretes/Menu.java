package ui.concretes;


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
    throw new RuntimeException("not implemented");
  }

  @Override
  protected void help() {
    throw new RuntimeException("not implemented");
  }

  @Override
  protected void showMyFlights() {
    throw new RuntimeException("not implemented");
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
    throw new RuntimeException("not implemented");
  }

  @Override
  protected void showOnlineBoard() {
    throw new RuntimeException("not implemented");
  }
}
