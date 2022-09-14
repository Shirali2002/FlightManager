package model;

import database.DB;
import util.IdUtil;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket implements Serializable {
  private final int id;
  private Passenger passenger;
  private Flight flight;
  private final int userIdWhoOrderedTicket;
  private final LocalDateTime dateOfReserve;


  public Ticket(Passenger passenger, Flight flight, int userIdWhoOrderedTicket) {
    this.id = IdUtil.getNewId(DB.TICKET_ID).orElseThrow();
    this.passenger = passenger;
    this.flight = flight;
    this.userIdWhoOrderedTicket = userIdWhoOrderedTicket;
    this.dateOfReserve = LocalDateTime.now();
  }

  public int getId() {
    return id;
  }

  public Passenger getPassenger() {
    return passenger;
  }

  public void setPassenger(Passenger passenger) {
    this.passenger = passenger;
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }

  public int getUserIdWhoOrderedTicket() {
    return userIdWhoOrderedTicket;
  }

  public LocalDateTime getDateOfReserve() {
    return dateOfReserve;
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
    return "Ticket{id=%d, passenger=%s, flight=%s, userIdWhoOrderedTicket=%d, dateOfReserve=%s}"
        .formatted(id, passenger, flight, userIdWhoOrderedTicket, dateOfReserve);
  }
}
