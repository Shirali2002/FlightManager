import app.FlightManager;
import controller.BookingController;
import controller.FlightController;
import controller.UserController;

public class Main {
  public static void main(String[] args) {
    System.out.println(FlightController.getInstance().getAllFlight());
    System.out.println(UserController.getInstance().getAllUser());
    System.out.println(BookingController.getInstance().getAllBooking());

    FlightManager.getInstance().start();
  }

}
