package controller;

import console.Console;
import exception.FlightCapacityOverflowException;
import exception.NoSuchBookingException;
import exception.NoSuchFlightException;
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

  public Optional<Ticket> bookFlight(String passengerName, String passengerSurname, int flightId, int userId)
      throws FlightCapacityOverflowException {
    return bookingService.bookFlight(passengerName, passengerSurname, flightId, userId);
  }

  public boolean cancelBooking(int ticketId) throws NoSuchBookingException {
    return bookingService.cancelBooking(ticketId);
  }

  public HashMap<Integer, Ticket> getAllBooking() {
    return bookingService.getAllBooking();
  }

  public Optional<Ticket> getBookingById(int id){
    return bookingService.getBookingById(id);
  }

  public void displayUserBookings(int userId, Console console) {
    getInstance().getUserBookings(userId).stream()
        .map(id -> getInstance().getAllBooking().get(id))
        .forEach(t -> console.printLine(t.prettyFormat()));
  }

  public List<Integer> getUserBookings(int userId) {
    return bookingService.getUserBookings(userId);
  }

  public Optional<Integer> getUserIdByUsername(String username) {
    return bookingService.getUserIdByUsername(username);
  }

}
