package command;

import console.Console;
import console.RealConsole;
import controller.UserController;
import ui.concretes.Menu;
import util.ConsoleUtil;

public class Login {
  public static void login(Console console){
    String username = ConsoleUtil.getString("Please enter your username:", console);
    String password = ConsoleUtil.getString("Please enter your password:", console);

    if (UserController.getInstance().login(username, password)){
      console.printLine("Login success.");
      Menu.getInstance().start();
    } else {
      console.printLine("Username or password is not correct. Please try again.");
    }
  }

  public static void login() {
    login(new RealConsole());
  }

  }
