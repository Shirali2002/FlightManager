package model;

public enum Airport {
  LosAngelesInternationalAirport("LAX"),
  ChengduShuangliuInternationalAirport("CTU"),
  MiamiInternationalAirport("MIA"),
  IstanbulAirport("IST"),
  MexicoCityInternationalAirport("MEX"),
  SheremetyevoInternationalAirport("SVO"),
  DubaiInternationalAirport("DXB"),
  TokyoHanedaAirport("HND"),
  AmsterdamAirportSchiphol("AMS"),
  BoryspilInternationalAirport("KBP"),
  KyivInternationalAirport("IEV");

  private String airportCode;

  Airport(String code) {
    this.airportCode = code;
  }

  public String getAirportCode() {
    return airportCode;
  }
}
