package ui.concretes;

import app.FlightManager;
import command.AddFlight;
import command.CancelFlight;
import command.RemoveTicketForAdmin;
import command.RemoveUser;
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
  protected void help() {
    throw new RuntimeException("not implemented");
  }

  @Override
  protected void removeTicket() {
    RemoveTicketForAdmin.removeTicket();
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
