package controller;

import exception.FlightCapacityOverflowException;
import exception.NoSuchFlightException;
import model.Airport;
import model.Flight;
import model.Passenger;
import service.FlightService;
import util.FlightDate;

import java.util.HashMap;
import java.util.List;

public class FlightController {
  private static final FlightController flightController = new FlightController();
  private final FlightService flightService = FlightService.getInstance();

  private FlightController() {
  }

  public static FlightController getInstance() {
    return flightController;
  }

  public HashMap<Integer, Flight> getAllFlight() {
    return flightService.getAllFlight();
  }

  public Flight getFlightById(int id) throws NoSuchFlightException {
    return flightService.getFlightById(id);
  }

  public boolean addFlight(Flight value) {
    return flightService.addFlight(value);
  }

  public boolean cancelFlightById(int id) throws NoSuchFlightException {
    return flightService.cancelFlightById(id);
  }

  public boolean cancelAllFlight() {
    return flightService.cancelAllFlight();
  }

  public List<Flight> getAllFlightsNextHours(int howManyHours) {
    return flightService.getAllFlightsNextHours(howManyHours);
  }

  public List<Flight> searchFlightByFields(List<Airport> startPoint, List<Airport> endPoint,
                                           FlightDate date, int numberOfTicket)
      throws NoSuchFlightException {
    List<Flight> result = flightService.searchFlightByFields(startPoint, endPoint, date, numberOfTicket);
    if (result.size() > 0) {
      return result;
    } else {
      throw new NoSuchFlightException();
    }
  }

  public boolean addPassengerById(Passenger passenger, int flightId) throws FlightCapacityOverflowException {
    return flightService.addPassengerFlightById(passenger, flightId);
  }
}
