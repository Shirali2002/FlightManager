import app.FlightManager;
import dao.DAO;
import dao.FlightRepository;
import model.Airline;
import model.Airport;
import model.Flight;
import util.DateUtil;

public class Main {
  public static void main(String[] args) {
//    DAO<Flight> flightDAO = FlightRepository.getInstance();
//    Flight flight = new Flight(new DateUtil(2022, 10, 15, 13, 30),
//        new DateUtil(2022, 10, 15, 15, 0),
//        Airport.KYIV_INTERNATIONAL_AIRPORT,
//        Airport.ISTANBUL_AIRPORT,
//        Airline.AIR_FRANCE,
//        200);
//    flightDAO.updateById(flight.getId(), flight);
//    System.out.println(flightDAO.getAll());
    FlightManager.getInstance().start();
  }
}
