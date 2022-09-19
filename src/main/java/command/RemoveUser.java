package command;

import console.Console;
import console.RealConsole;
import controller.UserController;
import exception.NoSuchUserException;
import util.ConsoleUtil;

public class RemoveUser {
  public static void removeUser() {
    removeUser(new RealConsole());
  }

  public static void removeUser(Console console) {
    UserController.getInstance().getAllUser().values()
        .forEach(u -> console.printLine(u.prettyFormat()));
    removeUserMain(console);
  }

  private static void removeUserMain(Console console) {
    int userId = ConsoleUtil.getInt("Please enter id of the user you want to delete.", console);
    try {
      if (UserController.getInstance().deleteUserById(userId)){
        console.printLine("Removed successfully.");
      } else {
        console.printLine("There is problem with delete process.");
      }
    } catch (NoSuchUserException ex) {
      console.printLine("There is no user with entered id. Please try again.");
      removeUserMain(console);
    }
  }
}
