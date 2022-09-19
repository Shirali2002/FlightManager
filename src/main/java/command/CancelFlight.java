package command;

import console.Console;
import console.RealConsole;
import controller.FlightController;
import exception.NoSuchFlightException;
import util.ConsoleUtil;

public class CancelFlight {
  public static void cancelFlight(){
    cancelFlight(new RealConsole());
  }

  public static void cancelFlight(Console console){
    FlightController.getInstance().getAllFlight().values()
        .forEach(f -> System.out.println());
  }

  private static void cancelFlightMain(Console console) {
    int flightId = ConsoleUtil.getInt("Please enter id of the user you want to cancel.", console);
    try {
      if (FlightController.getInstance().cancelFlightById(flightId)){
        console.printLine("Cancelled successfully.");
      } else {
        console.printLine("There is problem with cancel process.");
      }
    } catch (NoSuchFlightException ex) {
      console.printLine("There is no flight with entered id. Please try again.");
      cancelFlightMain(console);
    }
  }
}
