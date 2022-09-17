package service;

import controller.BookingController;
import dao.DAO;
import dao.TicketRepository;
import model.Flight;
import model.Passenger;
import model.Ticket;

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
                                     int flightId, int userId){
    Ticket newTicket = new Ticket(new Passenger(passengerName, passengerSurname), flightId, userId);
    if (ticketDAO.add(newTicket.getId(), newTicket)){
      return Optional.of(newTicket);
    } else {
      return Optional.empty();
    }
  }

  public boolean cancelBooking(int ticketId){
    return ticketDAO.deleteById(ticketId);
  }

  public HashMap<Integer, Ticket> getAllBooking(){
    return ticketDAO.getAll().orElse(new HashMap<>());
  }

  public List<Integer> getUserBookings(int userId){
    return ticketDAO.getAll().orElse(new HashMap<>())
        .values().stream()
        .filter(t -> t.getUserIdWhoOrderedTicket() == userId)
        .map(Ticket::getId)
        .collect(Collectors.toList());
  }


}
