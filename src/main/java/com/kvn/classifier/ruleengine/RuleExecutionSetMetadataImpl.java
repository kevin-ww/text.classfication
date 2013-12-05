package com.kvn.classifier.ruleengine;

import javax.rules.RuleExecutionSetMetadata;

public class RuleExecutionSetMetadataImpl implements RuleExecutionSetMetadata {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String uri;

  private String name;

  private String description;

  public RuleExecutionSetMetadataImpl(String uri, String name,
      String description) {
    this.uri = uri;
    this.name = name;
    this.description = description;
  }

  @Override
  public String getUri() {
    return this.uri;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

}
