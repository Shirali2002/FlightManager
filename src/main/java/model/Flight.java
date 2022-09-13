package model;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Objects;

public class Flight {
  private int id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Airport toWhere;
  private Airport fromWhere;
  private Airline airline;
  private int freeSeats;



  public int getId() {
    return id;
  }

  public long getDate() {
    return date;
  }

  public void setDate(long date) {
    this.date = date;
  }

  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  public String getToWhere() {
    return toWhere;
  }

  public String getFromWhere() {
    return fromWhere;
  }

  public int getFreeSeats() {
    return freeSeats;
  }

  public void setFreeSeats(int freeSeats) {
    this.freeSeats = freeSeats;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (this == o) return true;
    Flight flight = (Flight) o;
    return id == flight.id
        && date == flight.date
        && duration == flight.duration
        && toWhere.equals(flight.toWhere)
        && fromWhere.equals(flight.fromWhere);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, date, duration, toWhere, fromWhere);
  }

  @Override
  public String toString() {
    return "Flight{id=%d, date=%d, duration=%d, toWhere='%s', fromWhere='%s', freeSeats=%d}"
        .formatted(id, date, duration, toWhere, fromWhere, freeSeats);
  }
}
