package service;

import controller.FlightController;
import dao.DAO;
import dao.FlightRepository;
import exception.NoSuchFlightException;
import model.Flight;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  public Flight getFlightById(int id) throws NoSuchFlightException {
    Optional<Flight> f = flightDAO.getById(id);
    if (f.isPresent()){
      return f.get();
    } else {
      throw new NoSuchFlightException();
    }
  }

  public List<Flight> getAllFlightsNextHours(int howManyHours) {
    return getAllFlight()
        .values().stream()
        .filter(f -> {
          LocalDateTime startDate = f.getStartDate();
          return startDate.isAfter(LocalDateTime.now())
              && startDate.isBefore(LocalDateTime.now().plusHours(howManyHours));
        })
        .collect(Collectors.toList());
  }
}
