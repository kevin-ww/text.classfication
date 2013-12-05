package com.kvn.classifier.ruleengine.rule;

import javax.rules.admin.Rule;

public class BaseRule implements Rule {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String name;

  private String description;

  public BaseRule(String name) {
    this(name, null);
  }

  private BaseRule(String name, String description) {
    this.name = name;
    this.description = description;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public Object getProperty(Object paramObject) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setProperty(Object paramObject1, Object paramObject2) {
    // TODO Auto-generated method stub

  }

}
