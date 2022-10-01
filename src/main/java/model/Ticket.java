package model;

import controller.FlightController;
import controller.UserController;
import database.DB;
import exception.FlightCapacityOverflowException;
import exception.NoSuchFlightException;
import exception.NoSuchUserException;
import util.FlightDate;
import util.IdUtil;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket implements Serializable, Formattable {
  private int id;
  private Passenger passenger;
  private int flightId;
  private final int userIdWhoOrderedTicket;
  private final LocalDateTime dateOfReserve;


  public Ticket(Passenger passenger, int flightId, int userIdWhoOrderedTicket) throws FlightCapacityOverflowException {
    this.id = IdUtil.getNewId(DB.TICKET_ID).orElseThrow();
    this.passenger = passenger;
    this.flightId = flightId;
    FlightController.getInstance().addPassengerByFlightId(passenger, this.flightId);
    this.userIdWhoOrderedTicket = userIdWhoOrderedTicket;
    this.dateOfReserve = LocalDateTime.now();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Passenger getPassenger() {
    return passenger;
  }

  public void setPassenger(Passenger passenger) {
    this.passenger = passenger;
  }

  public int getFlightId() {
    return flightId;
  }

  public void setFlightId(int flightId) {
    this.flightId = flightId;
  }

  public int getUserIdWhoOrderedTicket() {
    return userIdWhoOrderedTicket;
  }

  public LocalDateTime getDateOfReserve() {
    return dateOfReserve;
  }

  @Override
  public String prettyFormat() {
    Flight flightById = null;
    User userById;
    try {
      flightById = FlightController.getInstance().getFlightById(flightId);
      userById = UserController.getInstance().getUserById(userIdWhoOrderedTicket);
    } catch (NoSuchFlightException ex) {
      return String.format("| %-4.4s | There is no flight with ticket's flight id. So there is no ticket.",
          getId()
      );
    } catch (NoSuchUserException ue) {
      userById = null;
    }

    assert flightById != null;
    return String.format("| %-4.4s | customer - %-15.15s | passenger - %-30.30s | from %-5.5s | to %-5.5s | %-20.20s | %-15.15s |",
        getId(),
        userById != null ? userById.getUsername() : "-",
        passenger.getFullName(),
        flightById.getFromWhere().getAirportCode(),
        flightById.getToWhere().getAirportCode(),
        flightById.getAirline().getName(),
        FlightDate.prettyLocalDateTime(dateOfReserve)
    );
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (this == o) return true;
    Ticket ticket = (Ticket) o;
    return id == ticket.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Ticket{id=%d, passenger=%s, flightId=%d, userIdWhoOrderedTicket=%d, dateOfReserve=%s}"
        .formatted(id, passenger, flightId, userIdWhoOrderedTicket, dateOfReserve);
  }
}
