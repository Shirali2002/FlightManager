package model;

public enum IdFIle {
  TICKETID("TicketId.txt"),
  USERID("UserId.txt"),
  FLIGHTID("FlightId.txt");

  private final String fileName;

  IdFIle(String fileName) {
    this.fileName = fileName;
  }

  public String getFileName() {
    return fileName;
  }
}
