import app.FlightManager;

public class Main {
  public static void main(String[] args) {
    FlightManager.getInstance().start();
//    DAO< Flight> flightDAO = FlightRepository.getInstance();
//    HashMap<Integer, Flight> h = flightDAO.getAll().orElse(new HashMap<>());
//    System.out.println(h);

  }
}
