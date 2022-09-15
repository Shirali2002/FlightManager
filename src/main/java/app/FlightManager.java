package app;

import ui.abstracts.AbstractLoginMenu;
import ui.concretes.LoginMenu;

public class FlightManager {
  private static final FlightManager flightManager = new FlightManager();
  private static boolean appOnline;
  private static int loggedInUserId = -1;

  private FlightManager() {
  }

  public static FlightManager getInstance() {
    return flightManager;
  }

  public static boolean isAppOnline() {
    return appOnline;
  }

  public static boolean isLoggedIn() {
    return loggedInUserId != -1;
  }

  public static int getLoggedInUserId(){
    return loggedInUserId;
  }

  public void start() {
    appOnline = true;
    AbstractLoginMenu loginMenu = LoginMenu.getInstance();
    loginMenu.start();
  }

  public void stop(){
    appOnline = false;
  }

  public void login(int userId){
    loggedInUserId = userId;
  }

  public void logout(){
    loggedInUserId = -1;
  }
}