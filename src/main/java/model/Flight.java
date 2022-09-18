package model;

import database.DB;
import util.FlightDate;
import util.IdUtil;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Flight implements Serializable, Formattable {
  private int id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Airport toWhere;
  private Airport fromWhere;
  private Airline airline;
  private final List<Passenger> passengerList = new ArrayList<>();
  private int flightCapacity;

  public Flight(FlightDate startDate,
                FlightDate endDate,
                Airport toWhere,
                Airport fromWhere,
                Airline airline,
                int flightCapacity) {
    this.startDate = startDate.getLocalDateTime();
    this.endDate = endDate.getLocalDateTime();
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

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
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
    return toString();
//    throw new RuntimeException("pretty format not implemented");
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
    return "Flight{id=%d, startDate=%s, endDate=%s, toWhere=%s, fromWhere=%s, airline=%s, passengerList=%s}"
        .formatted(id, startDate, endDate, toWhere, fromWhere, airline, passengerList);
  }
}
