package console;

import java.util.Scanner;

public class RealConsole implements Console{
  private final Scanner sc = new Scanner(System.in);

  @Override
  public String nextLine() {
    return sc.nextLine();
  }

  @Override
  public void printLine(String line) {
    System.out.println(line);
  }
}
