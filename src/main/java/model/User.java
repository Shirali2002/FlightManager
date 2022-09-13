package model;

import util.IdUtil;

import java.util.Objects;

public class User {
  private final int id;
  private String name;
  private String surname;
  private String password;

  public User(String name, String surname, String password) {
    this.id = IdUtil.getNewId(IdFIle.USERID).orElseThrow();
    this.name = name;
    this.surname = surname;
    this.password = password;
  }

  public int getId() {
    return id;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "User{id=%d, name='%s', surname='%s', password='%s'}".formatted(id, name, surname, password);
  }
}
