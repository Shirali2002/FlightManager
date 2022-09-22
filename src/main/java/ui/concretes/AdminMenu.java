package ui.concretes;

import app.FlightManager;
import command.*;
import ui.abstracts.AbstractAdminMenu;

public class AdminMenu extends AbstractAdminMenu {
  public static final AdminMenu adminMenu = new AdminMenu();

  private AdminMenu() {
  }

  public static AdminMenu getInstance() {
    return adminMenu;
  }

  @Override
  protected void logout() {
    FlightManager.getInstance().adminlogout();
  }

  @Override
  protected void removeTicket() {
    CancelTicket.cancelTicketForAdmin();
  }

  @Override
  protected void generateTestFlightData() {
    TestData.generateTestFlightData();
  }

  @Override
  protected void removeUser() {
    RemoveUser.removeUser();
  }

  @Override
  protected void cancelFlight() {
    CancelFlight.cancelFlight();
  }

  @Override
  protected void addNewFlight() {
    AddFlight.addNewFlight();
  }
}
