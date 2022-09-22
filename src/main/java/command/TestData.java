package command;

import console.Console;
import console.RealConsole;
import controller.FlightController;
import model.Airline;
import model.Airport;
import model.Flight;
import util.ConsoleUtil;

import java.time.LocalDateTime;
import java.util.Random;

public class TestData {
  public static void generateTestFlightData() {
    generateTestFlightData(new RealConsole());
  }

  public static void generateTestFlightData(Console console) {
    checkDeleteOldData(console);
    int countData = ConsoleUtil.getInt("How many data you want to generate?", console);
    for (int i = 0; i < countData; i++) {
      FlightController.getInstance()
          .addFlight(generateFlight());
    }
    console.printLine("Test data added successfully.");
  }

//  private static Flight generateFlight() {
//    Random rand = new Random();
//    Airline airline = Airline.values()[rand.nextInt(Airline.values().length)];
//    Airport fromWhere = Airport.values()[rand.nextInt(Airport.values().length)];
//    Airport toWhere = Airport.values()[rand.nextInt(Airport.values().length)];
//    while (fromWhere.equals(toWhere)) {
//      toWhere = Airport.values()[rand.nextInt(Airport.values().length)];
//    }
//    LocalDateTime dateTime = LocalDateTime.now().plusDays(rand.nextInt(5)).plusHours(rand.nextInt(24)).plusMinutes(rand.nextInt(60));
//    Duration duration = Duration.ofHours(rand.nextInt(10));
//    int seats = rand.nextInt(100);
//    return new Flight(flightDesignation, fromWhere, toWhere, airline, dateTime, duration, seats);
//  }

  private static void checkDeleteOldData(Console console) {
    String choice = ConsoleUtil.getString("Do you want to remove old data while generating new data? (y/n)", console);
    if (choice.equalsIgnoreCase("y")) {
      if (FlightController.getInstance().cancelAllFlight()) {
        console.printLine("Old data deleted.");
      } else {
        console.printLine("There was problem. Please connect with our customer service.");
      }
    } else if (choice.equalsIgnoreCase("n")) {
      console.printLine("Old data will not delete.");
    } else {
      console.printLine("Please enter 'y' or 'n'");
      checkDeleteOldData(console);
    }
  }
}
