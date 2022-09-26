package command;

import app.FlightManager;
import console.Console;
import console.RealConsole;
import controller.FlightController;
import dao.impl.LogRepository;
import model.Log;
import util.ConsoleUtil;

public class OnlineBoard {
  private static final LogRepository log = LogRepository.getInstance();

  public static void showOnlineBoard() {
    showOnlineBoard(new RealConsole());
  }

  public static void showOnlineBoard(Console console) {
    int hours = ConsoleUtil.getInt("How many hours you want to see: ", console);
    log.save(String.format("id=%d user get online board by %d hours.",
            FlightManager.getLoggedInUserId(), hours),
        Log.Info, console);
    FlightController.getInstance().getAllFlightsNextHours(hours)
        .forEach(f -> console.printLine(f.prettyFormat()));
  }
}
