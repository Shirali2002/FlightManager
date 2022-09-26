package command;

import app.FlightManager;
import console.Console;
import console.RealConsole;
import controller.UserController;
import dao.impl.LogRepository;
import exception.PasswordNotEqualException;
import exception.StrongPasswordException;
import exception.UniqueUsernameException;
import model.Log;
import util.ConsoleUtil;

public class Register {
  private static final LogRepository log = LogRepository.getInstance();

  public static boolean register() {
    return register(new RealConsole());
  }

  public static boolean register(Console console) {
    String name = ConsoleUtil.getString("Please enter your name:", console);
    String surname = ConsoleUtil.getString("Please enter your surname:", console);
    return Register.mainRegister(name, surname, console);
  }

  private static boolean mainRegister(String name, String surname, Console console) {
    try {
      String username = ConsoleUtil
          .getString("Please choose username. If you want to return menu enter exit: ", console);
      String password = ConsoleUtil
          .getString("Please choose password at least 6 character. Use uppercase, lowercase and digit: ", console);
      String passwordAgain = ConsoleUtil.getString("Please enter password again: ", console);
      if (username.equalsIgnoreCase("exit")) {
        console.printLine("Returned to menu...");
        return false;
      } else if (password.equals(passwordAgain)) {
        UserController.getInstance().register(name, surname, username, password);
        log.save(String.format("new user registered. username=%s", username),
            Log.Info, console);
        return true;
      } else {
        throw new PasswordNotEqualException();
      }
    } catch (PasswordNotEqualException pe) {
      console.printLine("Passwords are not equal.");
      return mainRegister(name, surname, console);
    } catch (UniqueUsernameException ue) {
      console.printLine("Username has been used. Please change username.");
      return mainRegister(name, surname, console);
    } catch (StrongPasswordException se) {
      console.printLine("Password is not strong. Use uppercase, lowercase, digit and use at least 6 character.");
      return mainRegister(name, surname, console);
    }
  }
}
