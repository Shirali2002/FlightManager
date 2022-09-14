package util;

import java.util.Scanner;

public class ConsoleUtil {
  public static int getInt(String inputMessage){
    Scanner sc = new Scanner(System.in);
    System.out.println(inputMessage);
    System.out.print(">>> ");
    try {
      return sc.nextInt();
    } catch (NumberFormatException ex){
      System.out.println("Input is invalid!");
      return getInt(inputMessage);
    }
  }

  public static String getString(String inputMessage){
    Scanner sc = new Scanner(System.in);
    System.out.println(inputMessage);
    System.out.print(">>> ");
    return sc.nextLine();
  }
}
