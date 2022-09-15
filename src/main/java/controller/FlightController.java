package controller;

import exception.NoFlightMatchedIdException;
import model.Flight;
import service.FlightService;

import java.util.HashMap;
import java.util.Optional;

public class FlightController {
  public static final FlightController flightController = new FlightController();
  private final FlightService flightService = FlightService.getInstance();

  private FlightController() {
  }

  public static FlightController getInstance() {
    return flightController;
  }

  public HashMap<Integer, Flight> getAllFlight(){
    return flightService.getAllFlight();
  }

  public Flight getFlightById(int id) throws NoFlightMatchedIdException {
    return flightService.getFlightById(id);
  }

  }
