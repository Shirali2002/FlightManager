package service;

import dao.impl.FlightRepository;
import model.Airline;
import model.Airport;
import model.Flight;
import model.Passenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.FlightDate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class FlightServiceTest {
  FlightService flightService = FlightService.getInstance();
  FlightRepository flightDao = FlightRepository.getInstance();
  HashMap<Integer, Flight> realData;
  Flight testFlight;


  @BeforeEach
  public void setTestEnv() {
    realData = flightService.getAllFlight();
    flightDao.save(new HashMap<>());
    testFlight = new Flight(
        LocalDateTime.now().plusHours(2),
        Duration.ofHours(2),
        Airport.KYIV_INTERNATIONAL_AIRPORT,
        Airport.ISTANBUL_AIRPORT,
        Airline.AIR_FRANCE,
        200);

  }

  @AfterEach
  public void endTestEnv() {
    flightDao.save(realData);
  }

  @Test
  public void testAddFlight(){
    Flight resFlight = addTestFlight(testFlight);
    Assertions.assertEquals(testFlight, resFlight);
  }

  @Test
  public void testCancelFlightById(){
    Flight resFlight = addTestFlight(testFlight);
    flightService.cancelFlightById(resFlight.getId());
    Assertions.assertEquals(new HashMap<Integer, Flight>() ,flightService.getAllFlight());
  }

  @Test
  public void testGetAllFlightsNextHours(){
    addTestFlight(testFlight);
    List<Flight> nextHoursFlights = flightService.getAllFlightsNextHours(24);
    Assertions.assertEquals(1, nextHoursFlights.size());
  }


  @Test
  public void testSearchFlightByFields(){
    addTestFlight(testFlight);
    LocalDateTime startDate = testFlight.getStartDate();
    List<Flight> resFlights = flightService.searchFlightByFields(
        List.of(testFlight.getFromWhere()),
        List.of(testFlight.getToWhere()),
        new FlightDate(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth()),
        1
    );
    Assertions.assertEquals(1, resFlights.size());
  }

  @Test
  public void testAddPassengerFlightById(){
    addTestFlight(testFlight);
    flightService.addPassengerFlightById(new Passenger("Nicat", "Alihummatov"), testFlight.getId());

    Assertions.assertEquals(1, flightService.getFlightById(testFlight.getId()).getPassengerList().size());
  }


  private Flight addTestFlight(Flight testFlight){
    flightService.addFlight(testFlight);
    return flightService.getAllFlight()
        .get(testFlight.getId());
  }
}
