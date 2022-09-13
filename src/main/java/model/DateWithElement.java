package model;

import java.time.LocalDateTime;

public class DateWithElement {
  private final int year;
  private final int month;
  private final int day;
  private final int hour;
  private final int minutes;

  public DateWithElement(int year, int month, int day, int hour, int minutes) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minutes = minutes;
  }

  public LocalDateTime getLocalDateTime(){
    return LocalDateTime.of(year, month, day, hour, minutes);
  }
}
