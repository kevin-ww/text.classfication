package com.kvn.classifier.test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.rules.ConfigurationException;
import javax.rules.InvalidRuleSessionException;
import javax.rules.RuleExecutionSetNotFoundException;
import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.RuleSessionCreateException;
import javax.rules.RuleSessionTypeUnsupportedException;
import javax.rules.StatelessRuleSession;
import javax.rules.admin.RuleAdministrator;
import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetCreateException;
import javax.rules.admin.RuleExecutionSetRegisterException;

import com.kvn.classifier.ruleengine.RuleSet;
import com.kvn.classifier.ruleengine.UriConstants;
import com.kvn.classifier.ruleengine.rule.LogisiticsRule;
import com.kvn.classifier.ruleengine.util.RuleEngineLog;

public class RuleEngineTest {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static void main(String[] args) throws ConfigurationException,
      RuleExecutionSetCreateException, IOException,
      RuleSessionTypeUnsupportedException, RuleSessionCreateException,
      RuleExecutionSetNotFoundException, InvalidRuleSessionException,
      ClassNotFoundException, RuleExecutionSetRegisterException {

    // rule engine server side:

    // steps:

    String ruleEngineServiceUri = UriConstants.RULE_ENGINE_SERVICE_URI;

    Class.forName(UriConstants.RULE_SERVICE_PROVIDER_CLASS_NAME);

    RuleServiceProvider serviceProvider = RuleServiceProviderManager
        .getRuleServiceProvider(ruleEngineServiceUri);

    RuleAdministrator ruleAdministrator = serviceProvider
        .getRuleAdministrator();

    RuleEngineLog.debug(ruleAdministrator.toString());

    Map params = null;
    // InputStream stream = null;

    String bindUri = null;

    LogisiticsRule logisticsRule = new LogisiticsRule("kevin");
    LogisiticsRule logisticsRule1 = new LogisiticsRule("kevin_rul1");

    RuleSet ruleSet = new RuleSet("multi-logisitic--ruleset");

    ruleSet.addRule(logisticsRule);
    ruleSet.addRule(logisticsRule1);
    // ruleSet.add(logisticsRule1);

    RuleExecutionSet ruleExecutionSet = ruleAdministrator
        .getLocalRuleExecutionSetProvider(params).createRuleExecutionSet(
            ruleSet, null);

    bindUri = ruleExecutionSet.getName();

    ruleAdministrator.registerRuleExecutionSet(bindUri, ruleExecutionSet, null);

    RuleEngineLog.debug(ruleExecutionSet);
    //
    RuleRuntime ruleRunTime = serviceProvider.getRuleRuntime();

    StatelessRuleSession srs = (StatelessRuleSession) ruleRunTime
        .createRuleSession(bindUri, params, RuleRuntime.STATELESS_SESSION_TYPE);

    List inputList = new LinkedList();
    inputList.add(new String("Foo"));
    // inputList.add(new String("Bar"));
    // inputList.add(new Integer(5));
    // inputList.add(new Float(6));
    List resultList = srs.executeRules(inputList);
    System.out.println("executeRules: " + resultList);
    // release the session
    srs.release();

    // StatefulRuleSession strs = (StatefulRuleSession) ruleRunTime
    // .createRuleSession(null, params, RuleRuntime.STATEFUL_SESSION_TYPE);

    // strs.re
  }

}
