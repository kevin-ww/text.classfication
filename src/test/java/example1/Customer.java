package example1;

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
public class Customer {

  private double creditLimit;

  public Customer() {
  }

  public double getCreditLimit() {
    return creditLimit;
  }

  public void setCreditLimit(double creditLimit) {
    this.creditLimit = creditLimit;
  }

  // public void decrementCreditLimit(Double amount) {
  // creditLimit = creditLimit-amount.doubleValue();
  // }

  public void decrementCreditLimit(double amount) {
    creditLimit = creditLimit - amount;
  }

}