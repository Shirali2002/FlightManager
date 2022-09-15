package model;

public enum Airline {
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

  private String name;
  private String AirlineCode;

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
}
