package com.kvn.classifier.ruleengine;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.rules.InvalidRuleSessionException;
import javax.rules.ObjectFilter;
import javax.rules.RuleRuntime;
import javax.rules.StatelessRuleSession;

@SuppressWarnings("rawtypes")
public class StatefulRuleSessionImpl extends AbstractRuleSessionImpl implements
    StatelessRuleSession {

  public StatefulRuleSessionImpl(String uri, Map properties) {
    // TODO Auto-generated constructor stub
  }

  @Override
  public List executeRules(List objects) throws InvalidRuleSessionException,
      RemoteException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List executeRules(List objects, ObjectFilter filter)
      throws InvalidRuleSessionException, RemoteException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getType() throws RemoteException, InvalidRuleSessionException {
    return RuleRuntime.STATEFUL_SESSION_TYPE;
  }

}
