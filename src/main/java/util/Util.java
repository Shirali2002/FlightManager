package util;

public class Util {
  private static final Util util = new Util();

  private Util(){
  }

  public static Util getInstance(){
    return util;
  }


}
