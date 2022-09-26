package command;

import app.FlightManager;
import console.Console;
import console.RealConsole;
import controller.UserController;
import dao.impl.LogRepository;
import model.Log;

public class Logout {
  private static final LogRepository log = LogRepository.getInstance();
  public static void userLogout() {
    userLogout(new RealConsole());
  }

  public static void userLogout(Console console){
    log.save(String.format("%d user logged out", FlightManager.getLoggedInUserId()),
        Log.Info, console);
    FlightManager.getInstance().logout();
  }

  public static void adminLogout() {
    adminLogout(new RealConsole());
  }

  public static void adminLogout(Console console){
    log.save("admin logged out", Log.Info, console);
    FlightManager.getInstance().adminlogout();
  }




}
