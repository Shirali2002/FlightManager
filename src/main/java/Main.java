import app.FlightManager;
import controller.BookingController;
import controller.FlightController;
import controller.UserController;
import dao.DAO;
import dao.FlightRepository;
import model.Airline;
import model.Airport;
import model.Flight;
import model.User;
import util.FlightDate;

public class Main {
  public static void main(String[] args) {
//    DAO<Flight> flightDAO = FlightRepository.getInstance();
//    Flight flight = new Flight(new FlightDate(2022, 9, 17, 22, 30),
//        new FlightDate(2022, 9, 18, 2, 20),
//        Airport.KYIV_INTERNATIONAL_AIRPORT,
//        Airport.ISTANBUL_AIRPORT,
//        Airline.AIR_FRANCE,
//        200);
//    flightDAO.add(flight.getId(), flight);
//    System.out.println(flightDAO.getAll());

    System.out.println(FlightController.getInstance().getAllFlight());
    System.out.println(UserController.getInstance().getAllUser());
    System.out.println(BookingController.getInstance().getAllBooking());

    FlightManager.getInstance().start();
  }
}
