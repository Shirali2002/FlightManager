package command;

import console.Console;
import console.RealConsole;
import controller.FlightController;
import util.ConsoleUtil;

public class OnlineBoard {
  public static void showOnlineBoard() {
    showOnlineBoard(new RealConsole());
  }

  public static void showOnlineBoard(Console console) {
    int hours = ConsoleUtil.getInt("How many hours you want to see: ", console);
    FlightController.getInstance().getAllFlightsNextHours(hours)
        .forEach(f -> console.printLine(f.prettyFormat()));
  }
}
