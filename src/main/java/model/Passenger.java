package model;

import java.io.Serializable;
import java.util.Objects;

public class Passenger implements Serializable {
  private String name;
  private String surname;

  public Passenger(String name, String surname) {
    this.name = name;
    this.surname = surname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (this == o) return true;
    Passenger passenger = (Passenger) o;
    return name.equals(passenger.name) && surname.equals(passenger.surname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, surname);
  }

  @Override
  public String toString() {
    return "%s %s".formatted(name, surname);
  }
}
