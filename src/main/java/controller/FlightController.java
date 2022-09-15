package controller;

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

  public Optional<HashMap<Integer, Flight>> getAllFlight(){
    return flightService.getAllFlight();
  }

  public Optional<Flight> getFlightById(int id){
    return flightService.getFlightById(id);
  }

  }
