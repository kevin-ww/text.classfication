package com.kvn.classifier.ruleengine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.rules.admin.Rule;

public class RuleSet implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String name;

  private String description;

  /**
   * RuleBase
   */
  private List<Rule> rules;

  public RuleSet(String name) {
    this(name, null);
  }

  public RuleSet(String name, String description) {
    this.name = name;
    this.description = description;
    this.rules = new ArrayList<Rule>();
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return description;
  }

  public void addRule(Rule rule) {
    // validate the rule;
    this.rules.add(rule);
  }

  public Rule getRule(String ruleName) {
    Iterator<Rule> iter = this.rules.iterator();
    while (iter.hasNext()) {
      Rule rule = iter.next();
      if (rule.getName().equalsIgnoreCase(ruleName)) {
        return rule;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return "RuleSet [name=" + name + ", description=" + description
        + ", rules=" + rules + "]";
  }

}
