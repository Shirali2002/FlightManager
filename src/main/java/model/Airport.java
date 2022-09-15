package model;

public enum Airport {
  LOS_ANGELES_INTERNATIONAL_AIRPORT("LosAngeles International Airport", "LAX"),
  CHENGDU_SHUANGLIU_INTERNATIONAL_AIRPORT("Chengdu Shuangliu International Airport", "CTU"),
  MIAMI_INTERNATIONAL_AIRPORT("Miami International Airport", "MIA"),
  ISTANBUL_AIRPORT("Istanbul Airport", "IST"),
  MEXICO_CITY_INTERNATIONAL_AIRPORT("Mexico City International Airport", "MEX"),
  SHEREMETYEVO_INTERNATIONAL_AIRPORT("Sheremetyevo International Airport", "SVO"),
  DUBAI_INTERNATIONAL_AIRPORT("Dubai International Airport", "DXB"),
  TOKYO_HANEDA_AIRPORT("Tokyo Haneda Airport", "HND"),
  AMSTERDAM_AIRPORT_SCHIPHOL("Amsterdam Airport Schiphol", "AMS"),
  BORYSPIL_INTERNATIONAL_AIRPORT("Boryspil International Airport", "KBP"),
  KYIV_INTERNATIONAL_AIRPORT("KyivInternationalAirport", "IEV");

  private String name;
  private String airportCode;

  Airport(String name, String code) {
    this.name = name;
    this.airportCode = code;
  }

  public String getAirportCode() {
    return airportCode;
  }

  public String getName() {
    return name;
  }
}
