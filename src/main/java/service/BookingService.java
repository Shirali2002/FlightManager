package service;

import controller.BookingController;
import controller.UserController;
import dao.DAO;
import dao.TicketRepository;
import exception.FlightCapacityOverflowException;
import exception.NoSuchBookingException;
import exception.NoSuchFlightException;
import model.Flight;
import model.Passenger;
import model.Ticket;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingService {
  private static final BookingService bookingService = new BookingService();
  private final DAO<Ticket> ticketDAO = TicketRepository.getInstance();

  private BookingService() {
  }

  public static BookingService getInstance() {
    return bookingService;
  }

  public Optional<Ticket> bookFlight(String passengerName, String passengerSurname,
                                     int flightId, int userId) throws FlightCapacityOverflowException{
    Ticket newTicket = new Ticket(new Passenger(passengerName, passengerSurname), flightId, userId);

    if (ticketDAO.add(newTicket.getId(), newTicket)) {
      return Optional.of(newTicket);
    } else {
      return Optional.empty();
    }
  }

  public boolean cancelBooking(int ticketId) throws NoSuchBookingException {
    if (ticketDAO.getById(ticketId).isEmpty()) {
      throw new NoSuchBookingException();
    }
    return ticketDAO.deleteById(ticketId);
  }

  public HashMap<Integer, Ticket> getAllBooking() {
    return ticketDAO.getAll().orElse(new HashMap<>());
  }

  public Optional<Ticket> getBookingById(int id) {
    return ticketDAO.getById(id);
  }

  public List<Integer> getUserBookings(int userId) {
    return ticketDAO.getAll().orElse(new HashMap<>())
        .values().stream()
        .filter(t -> t.getUserIdWhoOrderedTicket() == userId)
        .map(Ticket::getId)
        .collect(Collectors.toList());
  }

  public Optional<Integer> getUserIdByUsername(String username) {
    return UserController.getInstance().getAllUser()
        .values().stream()
        .filter(u -> u.getUsername().equals(username))
        .map(User::getId)
        .findFirst();
  }


}
