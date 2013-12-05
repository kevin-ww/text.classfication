package com.kvn.classifier.ruleengine;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.rules.RuleExecutionSetNotFoundException;
import javax.rules.RuleRuntime;
import javax.rules.RuleSession;
import javax.rules.RuleSessionCreateException;
import javax.rules.RuleSessionTypeUnsupportedException;

import com.kvn.classifier.ruleengine.admin.RuleExecutionSetRepository;

@SuppressWarnings("rawtypes")
public class RuleRuntimeImpl implements RuleRuntime {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public RuleSession createRuleSession(String uri, Map properties,
      int ruleSessionType) throws RuleSessionTypeUnsupportedException,
      RuleSessionCreateException, RuleExecutionSetNotFoundException,
      RemoteException {

    RuleSession ruleSession = null;

    if (ruleSessionType == RuleRuntime.STATELESS_SESSION_TYPE) {
      ruleSession = new StatelessRuleSessionImpl(uri, properties);
    } else if (ruleSessionType == RuleRuntime.STATEFUL_SESSION_TYPE) {
      ruleSession = new StatefulRuleSessionImpl(uri, properties);
    } else {
      throw new RuleSessionTypeUnsupportedException(
          "Not supportable rule session type " + ruleSessionType);
    }

    return ruleSession;
  }

  @Override
  public List<String> getRegistrations() throws RemoteException {
    RuleExecutionSetRepository repository = RuleExecutionSetRepository
        .getInstance();
    return repository.getRegistrations();
  }

}
