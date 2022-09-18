package model;

import database.DB;
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


  public Ticket(Passenger passenger, int flightId, int userIdWhoOrderedTicket) {
    this.id = IdUtil.getNewId(DB.TICKET_ID).orElseThrow();
    this.passenger = passenger;
    this.flightId = flightId;
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
    return toString();
//    throw new RuntimeException("pretty format not implemented");
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
