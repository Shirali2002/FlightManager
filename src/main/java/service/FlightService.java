package service;

import dao.DAO;
import dao.FlightRepository;
import model.Flight;

import java.util.HashMap;
import java.util.Optional;

public class FlightService {
  public static final FlightService flightService = new FlightService();
  private final DAO<Flight> flightDAO = FlightRepository.getInstance();

  private FlightService() {
  }

  public static FlightService getInstance() {
    return flightService;
  }

  public Optional<HashMap<Integer, Flight>> getAllFlight(){
    return flightDAO.getAll();
  }

  public Optional<Flight> getFlightById(int id){
    return flightDAO.getById(id);
  }
}
