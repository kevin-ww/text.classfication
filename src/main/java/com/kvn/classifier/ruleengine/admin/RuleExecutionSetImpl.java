package com.kvn.classifier.ruleengine.admin;

import java.util.List;
import java.util.Map;

import javax.rules.ObjectFilter;
import javax.rules.admin.Rule;
import javax.rules.admin.RuleExecutionSet;

import com.kvn.classifier.ruleengine.RuleSet;

@SuppressWarnings("rawtypes")
public class RuleExecutionSetImpl implements RuleExecutionSet {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private RuleSet ruleSet;

  private ObjectFilter objectFilter;

  private Map properties;

  public RuleExecutionSetImpl(RuleSet ruleSet, Map properties) {
    this.ruleSet = ruleSet;
    this.properties = properties;
  }

  @Override
  public String getName() {
    return ruleSet.getName();
  }

  @Override
  public String getDescription() {
    return this.ruleSet.getDescription();
  }

  @Override
  public Object getProperty(Object key) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setProperty(Object key, Object value) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setDefaultObjectFilter(String defaultObjectFilter) {
    // TODO Auto-generated method stub

  }

  @Override
  public String getDefaultObjectFilter() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Rule> getRules() {
    return null;
  }

  @Override
  public String toString() {
    return "RuleExecutionSetImpl [ruleSet=" + ruleSet + ", objectFilter="
        + objectFilter + ", properties=" + properties + "]";
  }

}
