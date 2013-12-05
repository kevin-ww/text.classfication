package com.kvn.classifier.ruleengine.rule;

import com.kvn.classifier.ruleengine.util.RuleEngineLog;

public class LogisiticsRule extends BaseRule {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public static final String TAG = "Logisitics";

  public LogisiticsRule(String name) {
    super(name);

  }

  public String applyTag(String text) {
    RuleEngineLog.debug("applying tag for " + text);
    return TAG;
  }

  @Override
  public String toString() {
    return "LogisiticsRule [name=" + getName() + "]";
  }

}
