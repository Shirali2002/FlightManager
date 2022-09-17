package controller;

import exception.NoSuchFlightException;
import model.Flight;
import service.FlightService;

import java.util.HashMap;

public class FlightController {
  private static final FlightController flightController = new FlightController();
  private final FlightService flightService = FlightService.getInstance();

  private FlightController() {
  }

  public static FlightController getInstance() {
    return flightController;
  }

  public HashMap<Integer, Flight> getAllFlight(){
    return flightService.getAllFlight();
  }

  public Flight getFlightById(int id) throws NoSuchFlightException {
    return flightService.getFlightById(id);
  }

  }
