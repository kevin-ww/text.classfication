package example1;

/**
 * <p>Title: JRule Engine Project</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006 Mauro Carniel</p>
 * <p>Company: </p>
 * @author Mauro Carniel
 * @version 1.0
 */

public class Invoice {
  private String status = "unpaid";
  private double amount;
  public Invoice() {
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public double getAmount() {
    return amount;
  }
  public void setAmount(double amount) {
    this.amount = amount;
  }


}