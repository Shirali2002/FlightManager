package util;

import java.util.Scanner;

public class Util {
  private static final Util util = new Util();

  private Util(){
  }

  public static Util getInstance(){
    return util;
  }

  public static Scanner getNewScanner(){
    return new Scanner(System.in);
  }

  public static int getIntWithScanner(String inputMessage){
    Scanner sc = Util.getNewScanner();
    System.out.println(inputMessage);
    System.out.print(">>> ");
    try {
      return sc.nextInt();
    } catch (NumberFormatException ex){
      System.out.println("Input is invalid!");
      return getIntWithScanner(inputMessage);
    }
  }

  public static String getStringWithScanner(String inputMessage){
    Scanner sc = Util.getNewScanner();
    System.out.println(inputMessage);
    System.out.print(">>> ");
    return sc.nextLine();
  }


}
