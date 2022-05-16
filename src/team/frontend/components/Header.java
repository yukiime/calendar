package team.frontend.components;

import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel {
  private String title;
  private JLabel titJLabel = new JLabel();

  public Header(Calendar date) {
    this.setSize(800, 35);
    this.title =String.valueOf(date.get(Calendar.YEAR)) +"年" + String.valueOf(date.get(Calendar.MONTH) + 1) +"月"; 
    titJLabel.setText(this.title);
    this.add(titJLabel);
  }
}
