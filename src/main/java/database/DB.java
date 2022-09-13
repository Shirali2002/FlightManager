package database;

public enum DB {
  TICKET_ID("TicketId.txt"),
  USER_ID("UserId.txt"),
  FLIGHT_ID("FlightId.txt"),
  FLIGHT_DB("Flights.bin"),
  TICKET_DB("Tickets.bin"),
  USER_DB("Users.bin");

  private final String fileName;

  DB(String fileName) {
    this.fileName = fileName;
  }

  public String getFileName() {
    return fileName;
  }
}
