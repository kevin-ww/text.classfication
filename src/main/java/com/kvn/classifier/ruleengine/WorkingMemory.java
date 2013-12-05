package com.kvn.classifier.ruleengine;

/**
 * The Working Memory is the main Class for using the Rule Engine at runtime. It
 * holds references to all data that has been "asserted" into it (until
 * retracted) and it is the place where the interaction with your application
 * occurs. Working memories are stateful objects. They may be shortlived or
 * longlived. If you are interacting with an engine in a stateless manner, that
 * means you would use the RuleBase object to create a newWorkingMemory for each
 * session, and then discard the working memory when finished (creating a
 * working memory is a cheap operation). An alternative pattern is a working
 * memory that is kept around for a longer time (such as a conversation) - and
 * kept updated with new facts. When you wish to dispose of WorkingMemory the
 * best pactice is to use the dispose() method, so that the reference to it is
 * removed in the parent Rule Base. However, this is a weak reference, so it
 * should eventually be garbage collected anyway. The term Working Memory Action
 * is used to describe assertions, retractions and modifications with the
 * Working Memory
 * 
 * http://docs.jboss.org/drools/release/5.3.0.Final/drools-expert-docs/html/ch01
 * .html
 * 
 */
public class WorkingMemory {

}
