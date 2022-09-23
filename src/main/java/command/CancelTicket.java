package command;

import app.FlightManager;
import console.Console;
import console.RealConsole;
import controller.BookingController;
import exception.NoSuchBookingException;
import model.Ticket;
import util.ConsoleUtil;

import java.util.Optional;

public class CancelTicket {
  /** For admin */
  public static void cancelTicketForAdmin() {
    cancelTicketForAdmin(new RealConsole());
  }

  public static void cancelTicketForAdmin(Console console) {
    BookingController.getInstance().getAllBooking().values()
            .forEach(t -> console.printLine(t.prettyFormat()));
    cancelTicketAdminMain(console);
  }


  private static void cancelTicketAdminMain(Console console) {
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
      cancelTicketAdminMain(console);
    }
  }

  /** For users */
  public static void cancelTicketForUser() {
    cancelTicketForUser(new RealConsole());
  }

  public static void cancelTicketForUser(Console console) {
    BookingController bc = BookingController.getInstance();
    bc.displayUserBookings(FlightManager.getLoggedInUserId(), console);
    int inputId = ConsoleUtil.getInt("Please enter booking id which you want to cancel:", console);
    Optional<Ticket> bookingById = bc.getBookingById(inputId);
    if (bookingById.isPresent()) {
      Ticket ticket = bookingById.get();
      if (ticket.getUserIdWhoOrderedTicket() == FlightManager.getLoggedInUserId()) {
        if (bc.cancelBooking(inputId)) {
          console.printLine("Canceled successfully.");
        } else {
          console.printLine("There is problem. Ticket can not canceled");
        }
      } else {
        console.printLine("This ticket is not ordered by you so you can not cancel this ticket.");
      }
    } else {
      console.printLine("There is no booking with this id.");
    }

  }
}
