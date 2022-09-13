package model;

import java.util.Objects;

public class User {
  private final int id;
  private final String fullName;
  private final String password;

  public User(int id, String fullName, String password) {
    this.id = id;
    this.fullName = fullName;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (this == o) return true;
    User user = (User) o;
    return id == user.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "User{id=%d, name='%s', surname='%s'}".formatted(id, name, surname);
  }
}
