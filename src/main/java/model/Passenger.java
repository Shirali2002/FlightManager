package model;

import java.util.Objects;

public class Passenger {
  private int userId;
  private String fullName;

  public Passenger(int userId, String fullName) {
    this.userId = userId;
    this.fullName = fullName;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (this == o) return true;
    Passenger passenger = (Passenger) o;
    return userId == passenger.userId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }

  @Override
  public String toString() {
    return "Passenger{userId=%d, fullName='%s'}".formatted(userId, fullName);
  }
}
