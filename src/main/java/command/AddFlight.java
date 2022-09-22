package command;

import console.Console;
import console.RealConsole;
import controller.FlightController;
import exception.NoSuchFlightException;
import model.*;
import util.ConsoleUtil;
import util.FlightDate;

import java.time.Duration;

public class AddFlight {
  public static void addNewFlight() {
    addNewFlight(new RealConsole());
  }

  public static void addNewFlight(Console console) {
    try {
      FlightDate startDate = getFLightDateFromUser("time for begin", console);
      Duration duration = getDurationFromUser(console);

      Airport.displayAllAirports(console);
      console.printLine("In this section, please enter begin destination info.");
      Airport whereFrom = getEnumFromUser(AirCompany.AIRPORT, console);
      console.printLine("In this section, please enter finish destination info.");
      Airport whereTo = getEnumFromUser(AirCompany.AIRPORT, console);



      Airline.displayAllAirlines(console);
      console.printLine("In this section, please enter Airline company info.");
      Airline airline = getEnumFromUser(AirCompany.AIRLINE, console);

      int capacity = ConsoleUtil.getInt("Please enter passenger capacity of flight: ", console);

      if (FlightController.getInstance()
          .addFlight(new Flight(startDate, duration, whereTo, whereFrom, airline, capacity))){
        console.printLine("Flight added successfully");
      } else {
        console.printLine("UNSUCCESSFULL. There was a problem. Please try again.");
      }



    } catch (NoSuchFlightException ex){
      console.printLine("Returned to menu.");
    }
  }

  private static Duration getDurationFromUser(Console console){
    int hours = ConsoleUtil.getInt("How many hours flight will continue?", console);
    int minutes = ConsoleUtil.getInt("How many minutes flight will continue?", console);

    if (hours > 24 || minutes>=60){
      console.printLine("Flight duration must be less than 24 hours and minutes must be less than 60. Please try again.");
      return getDurationFromUser(console);
    }

    return Duration.ofMinutes(hours*60L + minutes);
  }

  private static FlightDate getFLightDateFromUser(String whichDate, Console console){
    console.printLine(String.format("In this section, please enter %s information.", whichDate));
    int year = ConsoleUtil.getInt("Please enter year of date:", console);
    int month = ConsoleUtil.getInt("Please enter month of year:", console);
    int day = ConsoleUtil.getInt("Please enter day of month:", console);
    int hour = ConsoleUtil.getInt("Please enter hour of day:", console);
    int minute = ConsoleUtil.getInt("Please enter minute of hour:", console);
    return new FlightDate(year, month, day, hour, minute);
  }

  private static <T extends AirCompanyInterface> T getEnumFromUser(AirCompany airCompany, Console console)
      throws NoSuchFlightException {
    String code = ConsoleUtil
        .getString(String.format("Please enter %s code. Enter 'exit' to return menu:",
            airCompany.name().toLowerCase()), console);

    if (code.equals("exit")){
      throw new NoSuchFlightException();
    }

    try{
      return switch (airCompany) {
        case AIRLINE -> (T) Airline.getAirportByAirlineCode(code);
        case AIRPORT -> (T) Airport.getAirportByAirportCode(code);
      };
    } catch (NoSuchFieldException | ClassCastException ex){
      console.printLine(String.format("There is no %s with this code. Please try again.",
          airCompany.name().toLowerCase()));
      return getEnumFromUser(airCompany, console);
    }
  }

}
