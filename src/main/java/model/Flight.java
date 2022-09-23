package model;

import database.DB;
import util.FlightDate;
import util.IdUtil;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Flight implements Serializable, Formattable {
  private int id;
  private LocalDateTime startDate;
  private Duration duration;
  private Airport toWhere;
  private Airport fromWhere;
  private Airline airline;
  private final List<Passenger> passengerList = new ArrayList<>();
  private int flightCapacity;

  public Flight(FlightDate startDate,
                FlightDate duration,
                Airport toWhere,
                Airport fromWhere,
                Airline airline,
                int flightCapacity) {
    this.startDate = startDate.getLocalDateTime();
    this.duration = Duration.ofMinutes(duration.getHour() * 60L + duration.getMinutes());
    this.toWhere = toWhere;
    this.fromWhere = fromWhere;
    this.airline = airline;
    this.flightCapacity = flightCapacity >= 1 ? flightCapacity : 0;
    this.id = IdUtil.getNewId(DB.FLIGHT_ID).orElseThrow();
  }

  public Flight(FlightDate startDate,
                Duration duration,
                Airport toWhere,
                Airport fromWhere,
                Airline airline,
                int flightCapacity) {
    this.startDate = startDate.getLocalDateTime();
    this.duration = duration;
    this.toWhere = toWhere;
    this.fromWhere = fromWhere;
    this.airline = airline;
    this.flightCapacity = flightCapacity >= 1 ? flightCapacity : 0;
    this.id = IdUtil.getNewId(DB.FLIGHT_ID).orElseThrow();
  }

  public Flight(LocalDateTime startDate,
                Duration duration,
                Airport toWhere,
                Airport fromWhere,
                Airline airline,
                int flightCapacity) {
    this.startDate = startDate;
    this.duration = duration;
    this.toWhere = toWhere;
    this.fromWhere = fromWhere;
    this.airline = airline;
    this.flightCapacity = flightCapacity >= 1 ? flightCapacity : 0;
    this.id = IdUtil.getNewId(DB.FLIGHT_ID).orElseThrow();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public Duration getDuration() {
    return duration;
  }

  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  public LocalDateTime getEndDate() {
    return this.startDate.plusMinutes(duration.toMinutes());
  }

  public Airport getToWhere() {
    return toWhere;
  }

  public void setToWhere(Airport toWhere) {
    this.toWhere = toWhere;
  }

  public Airport getFromWhere() {
    return fromWhere;
  }

  public void setFromWhere(Airport fromWhere) {
    this.fromWhere = fromWhere;
  }

  public Airline getAirline() {
    return airline;
  }

  public void setAirline(Airline airline) {
    this.airline = airline;
  }

  public List<Passenger> getPassengerList() {
    return passengerList;
  }

  public boolean addPassenger(Passenger passenger) {
    if ((flightCapacity - passengerList.size()) > 0) {
      this.passengerList.add(passenger);
      return true;
    } else {
      return false;
    }
  }

  public boolean removePassenger(Passenger passenger) {
    return this.passengerList.remove(passenger);
  }

  public int getFlightCapacity() {
    return flightCapacity;
  }

  public void setFlightCapacity(int flightCapacity) {
    this.flightCapacity = flightCapacity;
  }

  public int getFreeSeatCount() {
    return flightCapacity - passengerList.size();
  }

  @Override
  public String prettyFormat() {
    return String.format("| %-4.4s | %-15.15s | %-15.15s | %-25.25s | from %-40.40s | to %-40.40s | capacity %-5.5s |",
        getId(),
        FlightDate.prettyLocalDateTime(getStartDate()),
        FlightDate.prettyLocalDateTime(getEndDate()),
        getAirline().getName(),
        getFromWhere().getName(),
        getToWhere().getName(),
        getFreeSeatCount()
    );
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (this == o) return true;
    Flight flight = (Flight) o;
    return id == flight.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Flight{id=%d, startDate=%s, duration=%s, toWhere=%s, fromWhere=%s, airline=%s, passengerList=%s, flightCapacity=%d, freeSeats=%d}"
        .formatted(id, startDate, duration, toWhere, fromWhere, airline, passengerList, flightCapacity, getFreeSeatCount());
  }
}
