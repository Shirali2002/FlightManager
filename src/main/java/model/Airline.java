package model;

public enum Airline {
  QatarAirways("QA"),
  SingaporeAirlines("SA"),
  NipponAirways("NA"),
  Emirates("EM"),
  JapanAirlines("JA"),
  CathayPacificAirways("CP"),
  EvaAir("EA"),
  QantasAirways("QN"),
  HainanAirlines("HA"),
  AirFrance("AF");

  private String AirlineCode;

  Airline(String code) {
    this.AirlineCode = code;
  }

  public String getAirlineCode() {
    return AirlineCode;
  }
}
