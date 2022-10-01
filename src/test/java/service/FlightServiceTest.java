package service;

import dao.impl.FlightRepository;
import exception.FlightCapacityOverflowException;
import exception.NoSuchFlightException;
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
import java.util.Map;

class FlightServiceTest {
    FlightService flightService = FlightService.getInstance();
    FlightRepository flightDao = FlightRepository.getInstance();
    HashMap<Integer, Flight> realData;
    Flight testFlight;


    @BeforeEach
    void setTestEnv() {
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
    void endTestEnv() {
        flightDao.save(realData);
    }

    @Test
    void testGetAllFlight_Success() {
        Flight flight = addTestFlight(testFlight);
        var expected = Map.of(flight.getId(), flight);
        var actual = flightService.getAllFlight();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetFlightById_Success() {
        var expected = addTestFlight(testFlight);
        var actual = flightService.getFlightById(testFlight.getId());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetFlightById_WhenFlightNotFound_ThrowNoSuchFlightException() {
        Assertions.assertThrows(NoSuchFlightException.class,() -> flightService.getFlightById(1));
    }

    @Test
    void testAddFlight_Success() {
        var actual = addTestFlight(testFlight);
        Assertions.assertEquals(testFlight, actual);
    }

    @Test
    void testCancelFlightById_Success() {
        Flight flight = addTestFlight(testFlight);
        flightService.cancelFlightById(flight.getId());
        var expected = new HashMap<>();
        var actual = flightService.getAllFlight();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCancelFlightById_WhenFlightNotFound_ThrowNoSuchFlightException() {
        Assertions.assertThrows(NoSuchFlightException.class,
                () -> flightService.cancelFlightById(1));
    }

    @Test
    void testCancelAllFlight_Success() {
        addTestFlight(testFlight);
        flightService.cancelAllFlight();
        var expected = new HashMap<>();
        var actual = flightService.getAllFlight();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetAllFlightsNextHours_Success() {
        addTestFlight(testFlight);
        var expected = 1;
        var actual = flightService.getAllFlightsNextHours(24).size();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void testSearchFlightByFields_Success() {
        addTestFlight(testFlight);
        LocalDateTime startDate = testFlight.getStartDate();
        var expected = 1;
        var actual = flightService.searchFlightByFields(
                List.of(testFlight.getFromWhere()),
                List.of(testFlight.getToWhere()),
                new FlightDate(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth()),
                1
        ).size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddPassengerByFlightId_Success() {
        addTestFlight(testFlight);
        flightService.addPassengerByFlightId(new Passenger("Nicat", "Alihummatov"),
                testFlight.getId());
        var expected = 1;
        var actual = flightService.getFlightById(testFlight.getId()).getPassengerList().size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddPassengerByFlightId_WhenFlightNotFound_ReturnFalse() {
        var expected = false;
        var actual = flightService
                .addPassengerByFlightId(new Passenger("Nicat", "Alihummatov"), 1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddPassengerByFlightId_WhenFlightCapacityOverflow_ThrowFlightCapacityOverflowException() {
        testFlight.setFlightCapacity(0);
        addTestFlight(testFlight);

        Assertions.assertThrows(FlightCapacityOverflowException.class,
                () -> flightService.addPassengerByFlightId(new Passenger("Nicat", "Alihummatov"),
                        testFlight.getId()));
    }


    private Flight addTestFlight(Flight testFlight) {
        flightService.addFlight(testFlight);
        return flightService.getAllFlight()
                .get(testFlight.getId());
    }
}
