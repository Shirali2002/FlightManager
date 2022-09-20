package model;

import console.Console;

import java.util.Arrays;
import java.util.Optional;

public enum Airport implements Formattable, AirCompanyInterface {
  LOS_ANGELES_INTERNATIONAL_AIRPORT("LosAngeles International Airport", "losangeles", "LAX"),
  CHENGDU_SHUANGLIU_INTERNATIONAL_AIRPORT("Chengdu Shuangliu International Airport", "chengdu", "CTU"),
  MIAMI_INTERNATIONAL_AIRPORT("Miami International Airport", "miami", "MIA"),
  ISTANBUL_AIRPORT("Istanbul Airport", "istanbul", "IST"),
  MEXICO_CITY_INTERNATIONAL_AIRPORT("Mexico City International Airport", "mexico", "MEX"),
  SHEREMETYEVO_INTERNATIONAL_AIRPORT("Sheremetyevo International Airport", "moscow","SVO"),
  DUBAI_INTERNATIONAL_AIRPORT("Dubai International Airport", "dubai","DXB"),
  TOKYO_HANEDA_AIRPORT("Tokyo Haneda Airport", "tokyo", "HND"),
  AMSTERDAM_AIRPORT_SCHIPHOL("Amsterdam Airport Schiphol", "amsterdam", "AMS"),
  BORYSPIL_INTERNATIONAL_AIRPORT("Boryspil International Airport", "boryspil", "KBP"),
  KYIV_INTERNATIONAL_AIRPORT("Kyiv International Airport", "kiev", "IEV");

  private final String name;
  private final String cityName;
  private final String airportCode;

  Airport(String name, String cityName, String code) {
    this.name = name;
    this.cityName = cityName;
    this.airportCode = code;
  }

  public String getAirportCode() {
    return airportCode;
  }

  public String getName() {
    return name;
  }

  public String getCityName() {
    return cityName;
  }

  public static Airport getAirportByAirportCode(String airportCode) throws NoSuchFieldException {
    Optional<Airport> airport = Arrays.stream(Airport.values())
        .filter(a -> a.getAirportCode().equalsIgnoreCase(airportCode))
        .findFirst();

    if (airport.isPresent()){
      return airport.get();
    } else {
      throw new NoSuchFieldException();
    }
  }

  public static void displayAllAirports(Console console){
    Arrays.stream(Airport.values())
        .forEach(a -> console.printLine(a.prettyFormat()));
  }

  @Override
  public String prettyFormat() {
    return String.format(" %s | %s | %s ", getName(), getCityName(), getAirportCode());
  }
}
