package controller;

import model.Passenger;
import model.Ticket;
import service.BookingService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class BookingController {
  private static final BookingController bookingController = new BookingController();
  private final BookingService bookingService = BookingService.getInstance();

  private BookingController() {
  }

  public static BookingController getInstance() {
    return bookingController;
  }

  public Optional<Ticket> bookFlight(String passengerName, String passengerSurname, int flightId, int userId) {
    return bookingService.bookFlight(passengerName, passengerSurname, flightId, userId);
  }

  public boolean cancelBooking(int ticketId) {
    return bookingService.cancelBooking(ticketId);
  }

  public HashMap<Integer, Ticket> getAllBooking() {
    return bookingService.getAllBooking();
  }

  public List<Integer> getUserBookings(int userId) {
    return bookingService.getUserBookings(userId);
  }
}
