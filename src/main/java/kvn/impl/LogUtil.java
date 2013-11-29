package kvn.impl;

public class LogUtil {

  public static void debug(String log) {
    System.out.println(log);
  }

  public static void debug(String log, Object... args) {
    System.out.println(String.format(log, args));
  }

}
