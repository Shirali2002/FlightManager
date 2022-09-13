package model;

import util.IdUtil;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket {
  private final int id;
  private User user;
  private Flight flight;
  private final LocalDateTime dateOfReserve;

  public Ticket(User user, Flight flight) {
    this.id = IdUtil.getNewId(IdFIle.TICKETID).orElseThrow();
    this.user = user;
    this.flight = flight;
    this.dateOfReserve = LocalDateTime.now();
  }

  public int getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }

  public LocalDateTime getDateOfReserve() {
    return dateOfReserve;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ticket ticket = (Ticket) o;
    return id == ticket.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Ticket{id=%d, user=%s, flight=%s, dateOfReserve=%s}"
        .formatted(id, user, flight, dateOfReserve);
  }
}
