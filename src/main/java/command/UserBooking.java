package command;

import console.Console;
import console.RealConsole;
import controller.BookingController;
import controller.UserController;
import model.User;
import util.ConsoleUtil;

import java.util.List;
import java.util.Optional;

public class UserBooking {
  public static void showUserBooking(){
    showUserBooking(new RealConsole());
  }

  public static void showUserBooking(Console console){
    String username = ConsoleUtil.getString("Please enter your username: ", console);
    Optional<Integer> userId = getUserIdByUsername(username);
    userId.ifPresent(id -> displayUserBookings(id, console));
    if (userId.isEmpty()){
      console.printLine("There is no user matched username.");
    }
  }

  private static Optional<Integer> getUserIdByUsername(String username){
    return UserController.getInstance().getAllUser()
        .values().stream()
        .filter(u -> u.getUsername().equals(username))
        .map(User::getId)
        .findFirst();
  }

  private static void displayUserBookings(int userId, Console console){
    BookingController bc = BookingController.getInstance();
    List<Integer> userBookingId = bc.getUserBookings(userId);
    userBookingId.stream()
        .map(id -> bc.getAllBooking().get(id))
        .forEach(t -> console.printLine(t.prettyFormat()));
  }


}
