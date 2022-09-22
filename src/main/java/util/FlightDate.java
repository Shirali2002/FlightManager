package util;

import java.time.LocalDateTime;

public class FlightDate {
  private int year;
  private int month;
  private int day;
  private int hour;
  private int minutes;

  public FlightDate(int year, int month, int day, int hour, int minutes) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minutes = minutes;
  }

  public FlightDate(int year, int month, int day) {
    this.year = year;
    this.month = month;
    this.day = day;
  }

  public FlightDate(int hour, int minutes) {
    this.hour = hour;
    this.minutes = minutes;
  }

  public LocalDateTime getLocalDateTime(){
    return LocalDateTime.of(year, month, day, hour, minutes);
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getMinutes() {
    return minutes;
  }

  public void setMinutes(int minutes) {
    this.minutes = minutes;
  }
}
