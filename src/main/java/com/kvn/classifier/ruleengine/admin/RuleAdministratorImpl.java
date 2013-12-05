package com.kvn.classifier.ruleengine.admin;

import java.rmi.RemoteException;
import java.util.Map;

import javax.rules.admin.LocalRuleExecutionSetProvider;
import javax.rules.admin.RuleAdministrator;
import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetDeregistrationException;
import javax.rules.admin.RuleExecutionSetProvider;
import javax.rules.admin.RuleExecutionSetRegisterException;

@SuppressWarnings("rawtypes")
public class RuleAdministratorImpl implements RuleAdministrator {

  @Override
  public RuleExecutionSetProvider getRuleExecutionSetProvider(Map properties)
      throws RemoteException {
    return new RuleExecutionSetProviderImpl();
  }

  @Override
  public LocalRuleExecutionSetProvider getLocalRuleExecutionSetProvider(
      Map properties) throws RemoteException {
    return new LocalRuleExecutionSetProviderImple();
  }

  @Override
  public void registerRuleExecutionSet(String bindUri,
      RuleExecutionSet ruleExecutionSet, Map properties)
      throws RuleExecutionSetRegisterException, RemoteException {

    RuleExecutionSetRepository repository = RuleExecutionSetRepository
        .getInstance();
    repository.registerRuleExecutionSet(bindUri, ruleExecutionSet);

  }

  @Override
  public void deregisterRuleExecutionSet(String bindUri, Map properties)
      throws RuleExecutionSetDeregistrationException, RemoteException {

    RuleExecutionSetRepository repository = RuleExecutionSetRepository
        .getInstance();

    if (repository.getRuleExecutionSet(bindUri) == null) {
      throw new RuleExecutionSetDeregistrationException(
          "no execution set bound to: " + bindUri);
    }

    repository.unregisterRuleExecutionSet(bindUri);

  }

}
