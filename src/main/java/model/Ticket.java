package model;

public class Ticket {
  private int id;
  private final User user;
  private Flight flight;
  private final long dateOfReserve;

  public Ticket(User user, Flight flight) {
    this.user = user;
    this.flight = flight;
    this.dateOfReserve = System.currentTimeMillis();
  }

  public User getUser() {
    return user;
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }

  public long getDateOfReserve() {
    return dateOfReserve;
  }


}
