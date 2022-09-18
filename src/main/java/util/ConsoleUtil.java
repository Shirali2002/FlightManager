package util;

import console.Console;
import console.RealConsole;

public class ConsoleUtil {
//  public static int getInt(String inputMessage) {
//    return getInt(inputMessage, new RealConsole());
//  }

  public static int getInt(String inputMessage, Console console) {
    System.out.println(inputMessage);
    System.out.print(">>> ");
    try {
      return Integer.parseInt(console.nextLine());
    } catch (NumberFormatException ex) {
      System.out.println("Input is invalid!");
      return getInt(inputMessage, console);
    }
  }

//  public static String getString(String inputMessage) {
//    return getString(inputMessage, new RealConsole());
//  }

  public static String getString(String inputMessage, Console console) {
    System.out.println(inputMessage);
    System.out.print(">>> ");
    return console.nextLine();
  }
}
