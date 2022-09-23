package command;

import console.Console;
import console.RealConsole;
import controller.FlightController;
import exception.NoSuchFlightException;
import util.ConsoleUtil;

public class ShowPassengerList {
  private static final FlightController flightController = FlightController.getInstance();

  public static void showPassengerList() {
    showPassengerList(new RealConsole());
  }

  public static void showPassengerList(Console console) {
    flightController.getAllFlight().values()
        .forEach(f -> console.printLine(f.prettyFormat()));
    showPassengerListByFlightId(console);
  }

  private static void showPassengerListByFlightId(Console console) {
    int flightId = ConsoleUtil.getInt("Please enter flight id. Enter '-1' to return menu:", console);
    if (flightId == -1){
      console.printLine("Returned to menu..");
      return;
    }
    try {
      FlightController.getInstance().getFlightById(flightId)
          .getPassengerList()
          .forEach(p -> console.printLine(p.getFullName()));
    } catch (NoSuchFlightException ex){
      console.printLine("There is no flight with this id. Please try again.");
      showPassengerListByFlightId(console);
    }
  }
}
