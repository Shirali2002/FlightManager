package command;

import console.Console;
import console.RealConsole;
import controller.FlightController;
import exception.NoSuchFlightException;
import util.ConsoleUtil;

public class FlightInfo {
  public static String getFlightInfo() {
    return getFlightInfo(new RealConsole());
  }

  public static String getFlightInfo(Console console) {
    try {
      int id = ConsoleUtil.getInt("Please choose id to get INFO:", console);
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
