package command;

import app.FlightManager;
import console.Console;
import console.RealConsole;
import controller.FlightController;
import dao.impl.LogRepository;
import exception.NoSuchFlightException;
import model.Log;
import util.ConsoleUtil;

public class FlightInfo {
  private static final LogRepository log = LogRepository.getInstance();

  public static String getFlightInfo() {
    return getFlightInfo(new RealConsole());
  }

  public static String getFlightInfo(Console console) {
    try {
      int id = ConsoleUtil.getInt("Please choose id to get INFO:", console);
      log.save(String.format("id=%d user get id=%d flight info",
              FlightManager.getLoggedInUserId(), id),
          Log.Info, console);
      return FlightController.getInstance().getFlightById(id).prettyFormat();
    } catch (NoSuchFlightException ne) {
      return "There is no flight matching this id.";
    }
  }

  public static void displayFlightInfo() {
    displayFlightInfo(new RealConsole());
  }

  public static void displayFlightInfo(Console console) {
    console.printLine(getFlightInfo(console));
  }
}
