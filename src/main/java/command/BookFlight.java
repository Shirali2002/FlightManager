package command;

import app.FlightManager;
import console.Console;
import console.RealConsole;
import controller.BookingController;
import controller.FlightController;
import exception.FlightCapacityOverflowException;
import exception.NoSuchFlightException;
import model.Flight;
import model.Ticket;
import util.ConsoleUtil;

import java.util.Optional;

public class BookFlight {
  public static void getFlightAndBookFlight() {
    getFlightAndBookFlight(new RealConsole());
  }

  public static void getFlightAndBookFlight(Console console) {
    int fId = ConsoleUtil.getInt("Please enter the flight id you want to book:", console);
    try {
      Flight flight = FlightController.getInstance().getFlightById(fId);
      if (bookFlight(flight, console)){
        console.printLine("Flight booked successfully.");
      }
    } catch (NoSuchFlightException e) {
      console.printLine("There is no flight with this id.");
    }
  }

  private static boolean bookFlight(Flight flight, Console console) {
    try {
      String passengerName = ConsoleUtil.getString("Please enter passenger name:", console);
      String passengerSurname = ConsoleUtil.getString("Please enter passenger surname:", console);
      Optional<Ticket> ticket = BookingController.getInstance().bookFlight(passengerName,
          passengerSurname,
          flight.getId(),
          FlightManager.getLoggedInUserId());

      if (ticket.isEmpty()) {
        console.printLine("There is problem with booking. Please connect with our customer service..");
        return false;
      } else {
        return bookAgainCheck(flight, console);
      }
    } catch (FlightCapacityOverflowException ex) {
      console.printLine("The flight capacity for this flight is full. Please choose another flight.");
      return false;
    }
  }

  private static boolean bookAgainCheck(Flight flight, Console console) {
    String input = ConsoleUtil.getString("Book again?(y/n)", console);
    if (input.equalsIgnoreCase("y")) {

      return bookFlight(flight, console);
    } else if (input.equalsIgnoreCase("n")) {
      return true;
    } else {
      console.printLine("Please enter 'y' or 'n'.");
      return bookAgainCheck(flight, console);
    }
  }


}
