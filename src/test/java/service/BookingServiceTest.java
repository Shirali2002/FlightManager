package service;

import dao.impl.TicketRepository;
import dao.impl.UserRepository;
import exception.FlightCapacityOverflowException;
import exception.NoSuchBookingException;
import model.Airline;
import model.Airport;
import model.Flight;
import model.Passenger;
import model.Ticket;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class BookingServiceTest {
    BookingService bookingService = BookingService.getInstance();
    FlightService flightService = FlightService.getInstance();
    UserRepository userDao = UserRepository.getInstance();
    TicketRepository ticketDao = TicketRepository.getInstance();
    HashMap<Integer, Ticket> realData;
    Passenger testPassenger;
    User testUser;
    Flight testFlight;
    Ticket testTicket;


    @BeforeEach
    void setTestEnv() {
        realData = bookingService.getAllBooking();
        ticketDao.save(new HashMap<>());
        testPassenger = new Passenger("Nicat", "Alihummatov");
        testUser = new User("Shirali", "Alihummatov", "shirali", "Ss12345678");
        testFlight = new Flight(
                LocalDateTime.now().plusHours(2),
                Duration.ofHours(2),
                Airport.KYIV_INTERNATIONAL_AIRPORT,
                Airport.ISTANBUL_AIRPORT,
                Airline.AIR_FRANCE,
                200);
        testTicket = new Ticket(testPassenger, testFlight.getId(), testUser.getId());
    }

    @AfterEach
    void endTestEnv() {
        ticketDao.save(realData);
    }

    @Test
    void testGetAllBooking_Success() {
        Optional<Ticket> ticket = addTestTicket(testTicket);
        var expected = Map.of(ticket.get().getId(), ticket.get());
        var actual = bookingService.getAllBooking();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetBookingById_Success() {
        Optional<Ticket> ticket = addTestTicket(testTicket);
        var expected = ticket.get();
        var actual = bookingService.getBookingById(ticket.get().getId()).get();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetBookingById_WhenTicketNotFound_ThenReturnOptionalEmpty() {
        var expected = Optional.empty();
        var actual = bookingService.getBookingById(1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testBookFlight_Success() {
        var expected = testTicket;
        var actual = bookingService.bookFlight(testPassenger.getName(),
                testPassenger.getSurname(),
                testFlight.getId(),
                testUser.getId()).get();
        Assertions.assertEquals(expected.getPassenger(), actual.getPassenger());
        Assertions.assertEquals(expected.getFlightId(), actual.getFlightId());
        Assertions.assertEquals(expected.getUserIdWhoOrderedTicket(),
                actual.getUserIdWhoOrderedTicket());
    }

    @Test
    void testBookFlight_WhenFlightCapacityOverflow_ThrowFlightCapacityOverflowException() {
        testFlight.setFlightCapacity(0);
        flightService.addFlight(testFlight);
        Assertions.assertThrows(FlightCapacityOverflowException.class,
                () -> bookingService.bookFlight(testPassenger.getName(),
                testPassenger.getSurname(),
                testFlight.getId(),
                testUser.getId()));
    }

    @Test
    void testCancelBooking_Success() {
        Ticket ticket = addTestTicket(testTicket).get();
        bookingService.cancelBooking(ticket.getId());
        var expected = new HashMap<Integer, Ticket>();
        var actual = bookingService.getAllBooking();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCancelBooking_WhenTicketNotFound_ThrowNoSuchBookingException() {
        Assertions.assertThrows(NoSuchBookingException.class,
                () -> bookingService.cancelBooking(1));
    }

    @Test
    void testGetUserBookings_Success() {
        var expected = addTestTicket(testTicket).get();
        List<Integer> ticketIdList = bookingService
                .getUserBookings(expected.getUserIdWhoOrderedTicket());
        var actual = bookingService.getBookingById(ticketIdList.get(0)).get();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetUserIdByUsername_Success() {
        userDao.add(testUser.getId(), testUser);
        addTestTicket(testTicket);
        var expected = testUser.getId();
        var actual =  bookingService.getUserIdByUsername(testUser.getUsername()).get();
        Assertions.assertEquals(expected, actual);
    }


    private Optional<Ticket> addTestTicket(Ticket testTicket) {
        bookingService.bookFlight(testTicket.getPassenger().getName(),
                testTicket.getPassenger().getSurname(),
                testTicket.getFlightId(),
                testTicket.getUserIdWhoOrderedTicket());
        return bookingService.getAllBooking()
                .values().stream()
                .filter(t -> testTicket.getPassenger().equals(t.getPassenger())
                        && testTicket.getFlightId() == t.getFlightId()
                        && testTicket.getUserIdWhoOrderedTicket() == t.getUserIdWhoOrderedTicket())
                .findFirst();
    }


}
