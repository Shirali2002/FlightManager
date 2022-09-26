package command;

import app.FlightManager;
import console.Console;
import console.RealConsole;
import controller.UserController;
import dao.impl.LogRepository;
import model.Log;
import ui.concretes.AdminMenu;
import ui.concretes.Menu;
import util.AdminUtil;
import util.ConsoleUtil;

public class Login {
  private static final LogRepository  log = LogRepository.getInstance();
  public static void login(Console console){
    String username = ConsoleUtil.getString("Please enter your username:", console);
    String password = ConsoleUtil.getString("Please enter your password:", console);

    if (AdminUtil.adminCheck(username, password)){
      FlightManager.getInstance().adminlogin();
      console.printLine("Admin panel started..");
      log.save("admin logged in", Log.Info, console);
      AdminMenu.getInstance().start();
    }else if (UserController.getInstance().login(username, password)){
      console.printLine("Login success.");
      log.save(String.format("id=%d user logged in.", FlightManager.getLoggedInUserId()), Log.Info, console);
      Menu.getInstance().start();
    } else {
      console.printLine("Username or password is not correct. Please try again.");
    }
  }

  public static void login() {
    login(new RealConsole());
  }



  }
