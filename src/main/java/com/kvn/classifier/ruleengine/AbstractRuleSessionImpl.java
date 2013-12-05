package com.kvn.classifier.ruleengine;

import java.rmi.RemoteException;
import java.util.List;

import javax.rules.InvalidRuleSessionException;
import javax.rules.RuleExecutionSetMetadata;
import javax.rules.RuleRuntime;
import javax.rules.RuleSession;
import javax.rules.StatefulRuleSession;
import javax.rules.StatelessRuleSession;
import javax.rules.admin.RuleExecutionSet;

import com.kvn.classifier.ruleengine.admin.RuleExecutionSetRepository;

public abstract class AbstractRuleSessionImpl implements RuleSession {

  RuleExecutionSet ruleExecutionSet;

  @Override
  public RuleExecutionSetMetadata getRuleExecutionSetMetadata()
      throws InvalidRuleSessionException, RemoteException {

    RuleExecutionSetRepository repository = RuleExecutionSetRepository
        .getInstance();

    List<String> bindUris = repository.getRegistrations();
    String uri = null;
    for (String bindUri : bindUris) {
      // get the registeredExecutionSet by bindUri;
      RuleExecutionSet registedRuleExecutionSet = repository
          .getRuleExecutionSet(bindUri);
      if (registedRuleExecutionSet == this.ruleExecutionSet) {
        uri = bindUri;
        break;
      }
    }
    return new RuleExecutionSetMetadataImpl(uri,
        this.ruleExecutionSet.getName(), this.ruleExecutionSet.getDescription());
  }

  @Override
  public void release() throws RemoteException, InvalidRuleSessionException {
    this.ruleExecutionSet = null;
  }

  // @Override
  // public int getType() throws RemoteException, InvalidRuleSessionException {
  // // TODO Auto-generated method stub
  //
  // if (this instanceof StatelessRuleSession) {
  // return RuleRuntime.STATELESS_SESSION_TYPE;
  // } else if (this instanceof StatefulRuleSession) {
  // return RuleRuntime.STATEFUL_SESSION_TYPE;
  // }
  // throw new InvalidRuleSessionException("not supportable rule session type");
  // }

}
