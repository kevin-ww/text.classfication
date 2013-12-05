package com.kvn.classifier.ruleengine;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.rules.InvalidRuleSessionException;
import javax.rules.ObjectFilter;
import javax.rules.RuleExecutionSetNotFoundException;
import javax.rules.RuleRuntime;
import javax.rules.StatelessRuleSession;
import javax.rules.admin.RuleExecutionSet;

import com.kvn.classifier.ruleengine.admin.RuleExecutionSetRepository;

@SuppressWarnings("rawtypes")
public class StatelessRuleSessionImpl extends AbstractRuleSessionImpl implements
    StatelessRuleSession {

  public StatelessRuleSessionImpl(String uri, Map properties)
      throws RuleExecutionSetNotFoundException {

    RuleExecutionSetRepository repository = RuleExecutionSetRepository
        .getInstance();

    RuleExecutionSet ruleExecutionSet = repository.getRuleExecutionSet(uri);
    if (ruleExecutionSet == null) {
      throw new RuleExecutionSetNotFoundException(
          " No RuleExecutionSet bounded to: " + uri);
    }

    this.ruleExecutionSet = ruleExecutionSet;
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.rules.StatelessRuleSession#executeRules(java.util.List)
   * 
   * The process of matching the new or existing facts against Production Rules
   * is called Pattern Matching, which is performed by the Inference Engine.
   * There are a number of algorithms used for Pattern Matching by Inference
   * Engines including:
   * 
   * Linear Rete Treat Leaps
   */
  @Override
  public List executeRules(List objects) throws InvalidRuleSessionException,
      RemoteException {
    return executeRules(objects, null);
  }

  @Override
  public List executeRules(List objects, ObjectFilter filter)
      throws InvalidRuleSessionException, RemoteException {
    System.out.println("now applying rules" + this.ruleExecutionSet + " for "
        + objects);
    return null;
  }

  @Override
  public int getType() throws RemoteException, InvalidRuleSessionException {
    return RuleRuntime.STATELESS_SESSION_TYPE;
  }

}
