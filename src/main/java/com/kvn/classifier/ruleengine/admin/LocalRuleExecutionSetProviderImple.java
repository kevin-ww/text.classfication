package com.kvn.classifier.ruleengine.admin;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;

import javax.rules.admin.LocalRuleExecutionSetProvider;
import javax.rules.admin.Rule;
import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetCreateException;

import com.kvn.classifier.ruleengine.RuleSet;

@SuppressWarnings("rawtypes")
public class LocalRuleExecutionSetProviderImple implements
    LocalRuleExecutionSetProvider {

  @Override
  public RuleExecutionSet createRuleExecutionSet(InputStream inputStream,
      Map properties) throws RuleExecutionSetCreateException, IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public RuleExecutionSet createRuleExecutionSet(Reader reader, Map properties)
      throws RuleExecutionSetCreateException, IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public RuleExecutionSet createRuleExecutionSet(Object obj, Map properties)
      throws RuleExecutionSetCreateException {

    RuleSet ruleSet = null;

    if (obj instanceof Rule) {
      Rule rule = (Rule) obj;
      ruleSet = new RuleSet(rule.getName());
      ruleSet.addRule(rule);
    } else if (obj instanceof RuleSet) {
      ruleSet = (RuleSet) obj;
    } else {
      throw new RuleExecutionSetCreateException(
          "Object to create RuleExecutionSet must be Rule or RuleSet");
    }

    return new RuleExecutionSetImpl(ruleSet, properties);
  }

}
