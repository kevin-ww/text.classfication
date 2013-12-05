package com.kvn.classifier.ruleengine.admin;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Map;

import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetCreateException;
import javax.rules.admin.RuleExecutionSetProvider;

import org.w3c.dom.Element;

/**
 * TODO implementation;
 * 
 */
public class RuleExecutionSetProviderImpl implements RuleExecutionSetProvider {

  @Override
  public RuleExecutionSet createRuleExecutionSet(
      Element ruleExecutionSetElement, Map properties)
      throws RuleExecutionSetCreateException, RemoteException {
    return null;
  }

  @Override
  public RuleExecutionSet createRuleExecutionSet(Serializable serializable,
      Map properties) throws RuleExecutionSetCreateException, RemoteException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public RuleExecutionSet createRuleExecutionSet(String ruleExecutionSetUri,
      Map properties) throws RuleExecutionSetCreateException, IOException,
      RemoteException {
    // TODO Auto-generated method stub
    return null;
  }

}
