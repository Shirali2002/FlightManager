package command;

import console.Console;
import console.RealConsole;
import controller.BookingController;
import exception.NoSuchBookingException;
import util.ConsoleUtil;

public class RemoveTicketForAdmin {
  public static void removeTicket() {
    removeTicket(new RealConsole());
  }

  public static void removeTicket(Console console) {
    BookingController.getInstance().getAllBooking().values()
        .forEach(t -> System.out.println(t.prettyFormat()));
    int ticketId = ConsoleUtil.getInt("Please enter id of the ticket you want to delete.", console);
    removeTicketMain(console);
  }


  private static void removeTicketMain(Console console) {
    int ticketId = ConsoleUtil
        .getInt("Please enter id of the ticket you want to delete. Enter '-1' to return menu.", console);

    if (ticketId == -1){
      console.printLine("returned to menu.");
      return;
    }

    try {
      if (BookingController.getInstance().cancelBooking(ticketId)){
        console.printLine("Removed successfully.");
      } else {
        console.printLine("There is problem with delete process.");
      }
    } catch (NoSuchBookingException ex) {
      console.printLine("There is no ticket with entered id. Please try again.");
      removeTicketMain(console);
    }
  }
}
