package com.kvn.classifier.ruleengine.util;

public final class RuleEngineLog {

  public final static void debug(String log) {
    System.out.println(log);
  }

  public final static void debug(String log, Object... args) {
    System.out.println(String.format(log, args));
  }

  public final static void debug(Object o) {
    debug(o.toString());
  }

}
