package command;

import console.Console;
import console.RealConsole;

public class Help {
  public static void displayLoginMenuHelp() {
    displayLoginMenuHelp(new RealConsole());
  }

  public static void displayLoginMenuHelp(Console console) {
    console.printLine(
        """
            Login - choose login to activate your user in app.
            Online-board - Choose online board to learn about all flights which in next hours.
            Flight info - Choose view flight to get information about any flight by entering id.
            Register - choose register to register in our app.
            Exit - Choose exit to exit app.
            """
    );
  }

  public static void displayMainMenuHelp() {
    displayMainMenuHelp(new RealConsole());
  }

  public static void displayMainMenuHelp(Console console) {
    console.printLine(
        """
            Online-board - Choose online board to learn about all the flights.
            Flight info - Choose view flight to get information about any flight by entering id.
            Search and book flight - Choose this to search and book flights.
            Cancel booking - Choose this to cancel your tickets.
            My flights - Choose this to learn about your flights.
            Logout - Choose logout to exit from user and return main menu.
            """
    );
  }
}
