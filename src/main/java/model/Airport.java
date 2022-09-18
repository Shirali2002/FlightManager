package model;

public enum Airport {
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
  KYIV_INTERNATIONAL_AIRPORT("KyivInternationalAirport", "kiev", "IEV");

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
}
