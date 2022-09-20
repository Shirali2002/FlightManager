package command;

import app.FlightManager;
import console.Console;
import console.RealConsole;
import controller.BookingController;
import util.ConsoleUtil;

import java.util.Optional;

public class MyBookingInfo {
  public static void showUserBooking(){
    showUserBooking(new RealConsole());
  }

  public static void showUserBooking(Console console){
    BookingController.getInstance()
        .displayUserBookings(FlightManager.getLoggedInUserId(), console);
  }






//  public static void showUserBooking(Console console){
//    BookingController bc = BookingController.getInstance();
//    String username = ConsoleUtil.getString("Please enter your username: ", console);
//    Optional<Integer> userId = bc.getUserIdByUsername(username);
//    userId.ifPresent(id -> bc.displayUserBookings(id, console));
//    if (userId.isEmpty()){
//      console.printLine("There is no user matched username.");
//    }
//  }
  }
