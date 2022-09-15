package service;

import dao.DAO;
import dao.TicketRepository;
import model.Passenger;
import model.Ticket;

import java.util.Optional;

public class BookingService {
  public static final BookingService bookingService = new BookingService();
  private final DAO<Ticket> ticketDAO = TicketRepository.getInstance();

  private BookingService() {
  }

  public static BookingService getInstance() {
    return bookingService;
  }

  public Optional<Ticket> bookFlight(String passengerName, String passengerSurname,
                                     int flightId, int userId){
    Ticket newTicket = new Ticket(new Passenger(passengerName, passengerSurname), flightId, userId);
    if (ticketDAO.updateById(newTicket.getId(), newTicket)){
      return Optional.of(newTicket);
    } else {
      return Optional.empty();
    }
  }

  public boolean cancelBooking(int ticketId){
    return ticketDAO.deleteById(ticketId);
  }


}
