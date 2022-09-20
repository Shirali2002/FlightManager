package model;

import console.Console;

import java.util.Arrays;
import java.util.Optional;

public enum Airline implements Formattable, AirCompanyInterface {
  QATAR_AIRWAYS("Qatar Airways", "QA"),
  SINGAPORE_AIRLINES("Singapore Airlines", "SA"),
  NIPPON_AIRWAYS("Nippon Airways", "NA"),
  EMIRATES("Emirates", "EM"),
  JAPAN_AIRLINES("Japan Airlines", "JA"),
  CATHAY_PACIFIC_AIRWAYS("Cathay Pacific Airways", "CP"),
  EVA_AIR("EVA Air", "EA"),
  QANTAS_AIRWAYS("Qantas Airways", "QN"),
  HAINAN_AIRLINES("Hainan Airlines", "HA"),
  AIR_FRANCE("AirFrance", "AF");

  private final String name;
  private final String AirlineCode;

  Airline(String name, String code) {
    this.name = name;
    this.AirlineCode = code;
  }

  public String getAirlineCode() {
    return AirlineCode;
  }

  public String getName() {
    return name;
  }

  public static Airline getAirportByAirlineCode(String airlineCode) throws NoSuchFieldException {
    Optional<Airline> airline = Arrays.stream(Airline.values())
        .filter(a -> a.getAirlineCode().equalsIgnoreCase(airlineCode))
        .findFirst();

    if (airline.isPresent()){
      return airline.get();
    } else {
      throw new NoSuchFieldException();
    }
  }

  public static void displayAllAirlines(Console console){
    Arrays.stream(Airline.values())
        .forEach(a -> console.printLine(a.prettyFormat()));
  }

  @Override
  public String prettyFormat() {
    return String.format(" %s | %s ", getName(), getAirlineCode());
  }
}
