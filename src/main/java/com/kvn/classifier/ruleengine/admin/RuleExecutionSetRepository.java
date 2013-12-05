package com.kvn.classifier.ruleengine.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetRegisterException;

public final class RuleExecutionSetRepository {

  private static RuleExecutionSetRepository REPOSITORY;
  /**
   * using the in-memory map for storage at this point;
   */
  private Map<String, RuleExecutionSet> map = new HashMap<String, RuleExecutionSet>();

  public static synchronized RuleExecutionSetRepository getInstance() {
    if (REPOSITORY != null) {
      return REPOSITORY;
    }
    return RuleExecutionSetRepository.REPOSITORY = new RuleExecutionSetRepository();
  }

  public List<String> getRegistrations() {
    List<String> list = new ArrayList<String>();
    list.addAll(this.map.keySet());
    return list;
  }

  public RuleExecutionSet getRuleExecutionSet(String bindUri) {
    return (RuleExecutionSet) this.map.get(bindUri);
  }

  public void registerRuleExecutionSet(String bindUri, RuleExecutionSet ruleSet)
      throws RuleExecutionSetRegisterException {
    if (bindUri == null) {
      throw new RuleExecutionSetRegisterException("bindUri cannot be null");
    }

    if (ruleSet == null) {
      throw new RuleExecutionSetRegisterException("ruleSet cannot be null");
    }

    this.map.put(bindUri, ruleSet);
  }

  public void unregisterRuleExecutionSet(String bindUri) {
    if (bindUri == null) {
      throw new NullPointerException("bindUri cannot be null");
    }
    this.map.remove(bindUri);
  }

}
