package command;

import console.Console;
import console.RealConsole;
import controller.FlightController;
import exception.NoSuchFlightException;
import exception.UnsuccessfulSearchException;
import model.Airport;
import model.Flight;
import util.ConsoleUtil;
import util.FlightDate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchFlight {
  public static void searchAndDisplayFlights() {
    searchAndDisplayFlights(new RealConsole());


  }

  public static void searchAndDisplayFlights(Console console) {
    Optional<List<Flight>> flights = searchFlight(console);
    if (flights.isPresent()) {
      flights.get().forEach(f -> console.printLine(f.prettyFormat()));
    } else {
      console.printLine("There is no flight founded.");
    }



  }

  public static Optional<List<Flight>> searchFlight() throws UnsuccessfulSearchException {
    return searchFlight(new RealConsole());
  }

  public static Optional<List<Flight>> searchFlight(Console console) throws UnsuccessfulSearchException {
    try {
      String startPoint = ConsoleUtil.getString("Please enter name of start point city:", console);
      String endPoint = ConsoleUtil.getString("Please enter name of end point city:", console);
      int year = ConsoleUtil.getInt("Please enter year of flight date.", console);
      int month = ConsoleUtil.getInt("Please enter month of flight date.", console);
      int day = ConsoleUtil.getInt("Please enter day of flight date.", console);
      int numberOfTicket = ConsoleUtil.getInt("How many ticket do you want to buy? ", console);

      try {
        return Optional.of(FlightController.getInstance().searchFlightByFields(
            getAirportsFromCityName(startPoint),
            getAirportsFromCityName(endPoint),
            new FlightDate(year, month, day),
            numberOfTicket
        ));
      } catch (NoSuchFlightException ex) {
        return searchAgainCheck(console);
      }
    } catch (UnsuccessfulSearchException ex) {
      return Optional.empty();
    }
  }

  private static Optional<List<Flight>> searchAgainCheck(Console console) throws UnsuccessfulSearchException {
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
