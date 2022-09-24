package service;

import dao.DAO;
import dao.FlightRepository;
import exception.FlightCapacityOverflowException;
import exception.NoSuchFlightException;
import model.Airport;
import model.Flight;
import model.Passenger;
import util.FlightDate;

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

  public HashMap<Integer, Flight> getAllFlight() {
    return flightDAO.getAll().orElse(new HashMap<>());
  }

  public Flight getFlightById(int id) throws NoSuchFlightException {
    Optional<Flight> f = flightDAO.getById(id);
    if (f.isPresent()) {
      return f.get();
    } else {
      throw new NoSuchFlightException();
    }
  }

  public boolean addFlight(Flight value){
    return flightDAO.add(value.getId(), value);
  }

  public boolean cancelFlightById(int id) throws NoSuchFlightException {
    if (flightDAO.getById(id).isEmpty()) {
      throw new NoSuchFlightException();
    }
    return flightDAO.deleteById(id);
  }

  public boolean cancelAllFlight() {
    return flightDAO.save(new HashMap<>());
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

  public List<Flight> searchFlightByFields(List<Airport> startPoint, List<Airport> endPoint,
                                           FlightDate date, int numberOfTicket) {
    return startPoint.stream()
        .flatMap(sp -> endPoint.stream()
            .flatMap(ep ->
                getAllFlight().values().stream()
                    .filter(f ->
                        f.getFromWhere().equals(sp)
                            && f.getToWhere().equals(ep)
                            && f.getStartDate().getYear() == date.getYear()
                            && f.getStartDate().getMonthValue() == date.getMonth()
                            && f.getStartDate().getDayOfMonth() == date.getDay()
                            && f.getFreeSeatCount() >= numberOfTicket
                    )
            )
        ).collect(Collectors.toList());
  }

  public boolean addPassengerFlightById(Passenger passenger, int flightId) throws FlightCapacityOverflowException{
    HashMap<Integer, Flight> allFlight = flightDAO.getAll().orElse(new HashMap<>());
    Flight flight = allFlight.get(flightId);
    if (flight == null) return false;

    if (flight.addPassenger(passenger)){
      flightDAO.save(allFlight);
      return true;
    } else {
      throw new FlightCapacityOverflowException();
    }
  }
}
