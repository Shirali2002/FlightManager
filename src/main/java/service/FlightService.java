package service;

import dao.DAO;
import dao.FlightRepository;
import exception.NoFlightMatchedIdException;
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

  public HashMap<Integer, Flight> getAllFlight(){
     return flightDAO.getAll().orElse(new HashMap<>());
  }

  public Flight getFlightById(int id) throws NoFlightMatchedIdException {
    Optional<Flight> f = flightDAO.getById(id);
    if (f.isPresent()){
      return f.get();
    } else {
      throw new NoFlightMatchedIdException();
    }
  }
}
