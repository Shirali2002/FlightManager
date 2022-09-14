public class FlightManager {
  private static final FlightManager flightManager = new FlightManager();
  private static boolean appOnline;
  private static boolean loggedIn;

  private FlightManager() {
  }

  public static FlightManager getInstance() {
    return flightManager;
  }

  public static boolean isAppOnline() {
    return appOnline;
  }

  public static boolean isLoggedIn() {
    return loggedIn;
  }

  public void start() {
    appOnline = true;
//    AbstractMenu menu = Menu.getInstance();
//    menu.startMenu();
  }

  public void stop(){
    appOnline = false;
  }

  public void login(){
    loggedIn = true;
  }

  public void logout(){
    loggedIn = false;
  }
}
