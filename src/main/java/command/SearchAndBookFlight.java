package command;

import app.FlightManager;
import console.Console;
import console.RealConsole;
import controller.BookingController;
import controller.FlightController;
import exception.FlightCapacityOverflowException;
import exception.NoSuchFlightException;
import exception.UnsuccessfulSearchException;
import model.Airport;
import model.Flight;
import model.Ticket;
import util.ConsoleUtil;
import util.FlightDate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchAndBookFlight {
  public static void searchAndBookFlight() {
    searchAndBookFlight(new RealConsole());
  }

  public static void searchAndBookFlight(Console console) {
    try {
      List<Flight> matchedFlights = searchFlight(console);
      matchedFlights.forEach(f -> console.printLine(f.prettyFormat()));
    } catch (UnsuccessfulSearchException ex) {
      return;
    }

    if (getFlightAndBookFlight(console)) {
      console.printLine("Flight booked successfully.");
    } else {
      console.printLine("Booking ");
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
    } catch (FlightCapacityOverflowException ex){
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

  private static boolean getFlightAndBookFlight(Console console) {
    int fId = ConsoleUtil.getInt("Please enter the flight id you want to book:", console);
    try {
      Flight flight = FlightController.getInstance().getFlightById(fId);
      return bookFlight(flight, console);
    } catch (NoSuchFlightException e) {
      console.printLine("There is no flight with this id.");
      return false;
    }
  }

  private static List<Flight> searchFlight(Console console) throws UnsuccessfulSearchException {
    String startPoint = ConsoleUtil.getString("Please enter name of start point city:", console);
    String endPoint = ConsoleUtil.getString("Please enter name of end point city:", console);
    int year = ConsoleUtil.getInt("Please enter year of flight date.", console);
    int month = ConsoleUtil.getInt("Please enter month of flight date.", console);
    int day = ConsoleUtil.getInt("Please enter day of flight date.", console);
    int numberOfTicket = ConsoleUtil.getInt("How many ticket do you want to buy? ", console);

    try {
      return FlightController.getInstance().searchFlightByFields(
          getAirportsFromCityName(startPoint),
          getAirportsFromCityName(endPoint),
          new FlightDate(year, month, day),
          numberOfTicket
      );
    } catch (NoSuchFlightException ex) {
      return searchAgainCheck(console);
    }
  }

  private static List<Flight> searchAgainCheck(Console console) throws UnsuccessfulSearchException {
    String input = ConsoleUtil.getString("There is no result. Search again?(y/n)", console);
    if (input.equalsIgnoreCase("y")) {
      return searchFlight(console);
    } else if (input.equalsIgnoreCase("n")) {
      throw new UnsuccessfulSearchException();
    } else {
      console.printLine("Please enter 'y' or 'n'.");
      return searchAgainCheck(console);
    }
  }

  private static List<Airport> getAirportsFromCityName(String cityName) {
    return Arrays.stream(Airport.values())
        .filter(f -> f.getCityName().equalsIgnoreCase(cityName))
        .collect(Collectors.toList());
  }
}
