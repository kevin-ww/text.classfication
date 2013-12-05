package example5;

import javax.swing.JComboBox;

public class StatusControl extends JComboBox {

  public void setStatus(String status) {
    setSelectedItem(status);
  }

  public String getStatus() {
    return (String) getSelectedItem();
  }

}
