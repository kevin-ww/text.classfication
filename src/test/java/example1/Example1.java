package example1;

import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.StatefulRuleSession;
import javax.rules.StatelessRuleSession;
import javax.rules.admin.RuleAdministrator;
import javax.rules.admin.RuleExecutionSet;

import java.io.*;
import java.util.*;

/**
 * <p>
 * Title: JRuleEngine Project
 * </p>
 * <p>
 * Description: This class implements a simple example using a rule execution
 * set.
 * </p>
 * <p>
 * Copyright: Copyright (C) 2006 Mauro Carniel
 * </p>
 * 
 * <p>
 * This file is part of JRuleEngine project. This library is free software; you
 * can redistribute it and/or modify it under the terms of the (LGPL) Lesser
 * General Public License as published by the Free Software Foundation;
 * 
 * GNU LESSER GENERAL PUBLIC LICENSE Version 2.1, February 1999
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Library General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Library General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 675 Mass Ave, Cambridge, MA 02139, USA.
 * 
 * The author may be contacted at: maurocarniel@tin.it
 * </p>
 * 
 * @author Mauro Carniel
 * @version 1.0
 */
public class Example1 {

  public Example1() {

    try {
      // Load the rule service provider of the reference
      // implementation.
      // Loading this class will automatically register this
      // provider with the provider manager.
      Class.forName("org.jruleengine.RuleServiceProviderImpl");

      // Get the rule service provider from the provider manager.
      RuleServiceProvider serviceProvider = RuleServiceProviderManager
          .getRuleServiceProvider("org.jruleengine");

      // get the RuleAdministrator
      RuleAdministrator ruleAdministrator = serviceProvider
          .getRuleAdministrator();
      System.out.println("\nAdministration API\n");
      System.out.println("Acquired RuleAdministrator: " + ruleAdministrator);

      // get an input stream to a test XML ruleset
      // This rule execution set is part of the TCK.
      InputStream inStream = new FileInputStream("./example1.xml");
      System.out.println("Acquired InputStream to example1.xml: " + inStream);

      // parse the ruleset from the XML document
      RuleExecutionSet res1 = ruleAdministrator
          .getLocalRuleExecutionSetProvider(null).createRuleExecutionSet(
              inStream, null);
      inStream.close();
      System.out.println("Loaded RuleExecutionSet: " + res1);

      // register the RuleExecutionSet
      String uri = res1.getName();
      ruleAdministrator.registerRuleExecutionSet(uri, res1, null);
      System.out.println("Bound RuleExecutionSet to URI: " + uri);

      // Get a RuleRuntime and invoke the rule engine.
      System.out.println("\nRuntime API\n");

      RuleRuntime ruleRuntime = serviceProvider.getRuleRuntime();
      System.out.println("Acquired RuleRuntime: " + ruleRuntime);

      // create a StatelessRuleSession
      StatelessRuleSession statelessRuleSession = (StatelessRuleSession) ruleRuntime
          .createRuleSession(uri, new HashMap(),
              RuleRuntime.STATELESS_SESSION_TYPE);

      System.out.println("Got Stateless Rule Session: " + statelessRuleSession);

      // call executeRules with some input objects

      // Create a Customer as specified by the TCK documentation.
      Customer inputCustomer = new Customer();
      inputCustomer.setCreditLimit(5000);

      // Create an Invoice as specified by the TCK documentation.
      Invoice inputInvoice = new Invoice();
      inputInvoice.setAmount(2000);

      // Create a input list.
      List input = new ArrayList();
      input.add(inputCustomer);
      input.add(inputInvoice);

      // Print the input.
      System.out.println("Calling rule session with the following data");
      System.out.println("Customer credit limit input: "
          + inputCustomer.getCreditLimit());
      System.out.println("Invoice:\n" + " amount: " + inputInvoice.getAmount()
          + " status: " + inputInvoice.getStatus());

      // Execute the rules without a filter.
      List results = statelessRuleSession.executeRules(input);

      System.out.println("Called executeRules on Stateless Rule Session: "
          + statelessRuleSession);

      System.out.println("Result of calling executeRules: " + results.size()
          + " results.");

      // Loop over the results.
      Iterator itr = results.iterator();
      while (itr.hasNext()) {
        Object obj = itr.next();
        if (obj instanceof Customer)
          System.out.println("Customer credit limit result: "
              + ((Customer) obj).getCreditLimit());
        if (obj instanceof Invoice)
          System.out.println("Invoice:\n" + " amount: "
              + ((Invoice) obj).getAmount() + " status: "
              + ((Invoice) obj).getStatus());
      }

      // Release the session.
      statelessRuleSession.release();
      System.out.println("Released Stateless Rule Session.");
      System.out.println();

      // create a StatefulRuleSession
      StatefulRuleSession statefulRuleSession = (StatefulRuleSession) ruleRuntime
          .createRuleSession(uri, new HashMap(),
              RuleRuntime.STATEFUL_SESSION_TYPE);

      System.out.println("Got Stateful Rule Session: " + statefulRuleSession);
      // Add another Invoice.
      Invoice inputInvoice2 = new Invoice();
      inputInvoice2.setAmount(1750);
      input.add(inputInvoice2);
      System.out.println("Calling rule session with the following data");
      System.out.println("Customer credit limit input: "
          + inputCustomer.getCreditLimit());
      System.out.println("Invoice:\n" + " amount: " + inputInvoice.getAmount()
          + " status: " + inputInvoice.getStatus());
      System.out.println("Invoice:\n" + " amount: " + inputInvoice2.getAmount()
          + " status: " + inputInvoice2.getStatus());

      // add an Object to the statefulRuleSession
      statefulRuleSession.addObjects(input);
      System.out.println("Called addObject on Stateful Rule Session: "
          + statefulRuleSession);

      statefulRuleSession.executeRules();
      System.out.println("Called executeRules");

      // extract the Objects from the statefulRuleSession
      results = statefulRuleSession.getObjects();

      System.out.println("Result of calling getObjects: " + results.size()
          + " results.");

      // Loop over the results.
      itr = results.iterator();
      while (itr.hasNext()) {
        Object obj = itr.next();
        if (obj instanceof Customer)
          System.out.println("Customer credit limit result: "
              + ((Customer) obj).getCreditLimit());
        if (obj instanceof Invoice)
          System.out.println("Invoice:\n" + " amount: "
              + ((Invoice) obj).getAmount() + " status: "
              + ((Invoice) obj).getStatus());
      }

      // release the statefulRuleSession
      statefulRuleSession.release();
      System.out.println("Released Stateful Rule Session.");
      System.out.println();

    } catch (NoClassDefFoundError e) {
      if (e.getMessage().indexOf("Exception") != -1) {
        System.err
            .println("Error: The Rule Engine Implementation could not be found.");
      } else {
        System.err.println("Error: " + e.getMessage());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    Example1 example11 = new Example1();
  }

}