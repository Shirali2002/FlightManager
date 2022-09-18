package command;

import app.FlightManager;
import console.Console;
import console.RealConsole;
import controller.BookingController;
import model.Ticket;
import util.ConsoleUtil;

import java.util.Optional;

public class CancelTicket {
  public static void cancelTicket() {
    cancelTicket(new RealConsole());
  }

  public static void cancelTicket(Console console) {
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
