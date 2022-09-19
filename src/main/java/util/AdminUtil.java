package util;

public class AdminUtil {
  public static boolean adminCheck(String username, String password){
    return username.equals("admin") && password.equals("admin");
  }
}
