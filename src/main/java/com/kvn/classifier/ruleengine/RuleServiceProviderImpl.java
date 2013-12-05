package com.kvn.classifier.ruleengine;

import javax.rules.ConfigurationException;
import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.admin.RuleAdministrator;

import com.kvn.classifier.ruleengine.admin.RuleAdministratorImpl;
import com.kvn.classifier.ruleengine.util.RuleEngineLog;

public class RuleServiceProviderImpl extends RuleServiceProvider {

  static {
    try {

      final String uri = UriConstants.RULE_ENGINE_SERVICE_URI;

      RuleServiceProviderManager.registerRuleServiceProvider(uri,
          RuleServiceProviderImpl.class);
      RuleEngineLog.debug("Rule serivice provider %s registered", uri);
    } catch (ConfigurationException ce) {
      ce.printStackTrace();
    }
  }

  private RuleRuntime ruleRuntime;
  private RuleAdministrator ruleAdministrator;

  @Override
  public RuleAdministrator getRuleAdministrator() throws ConfigurationException {
    if (this.ruleAdministrator == null)
      this.ruleAdministrator = new RuleAdministratorImpl();
    return this.ruleAdministrator;
  }

  @Override
  public RuleRuntime getRuleRuntime() throws ConfigurationException {
    if (this.ruleRuntime == null)
      this.ruleRuntime = new RuleRuntimeImpl();
    return this.ruleRuntime;
  }

  @Override
  public String toString() {
    return "RuleServiceProviderImpl [" + UriConstants.RULE_ENGINE_SERVICE_URI
        + "]";
  }

}
