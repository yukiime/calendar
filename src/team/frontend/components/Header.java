package team.frontend.components;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel {
  private String title;
  private JLabel titJLabel = new JLabel();

  public Header() {
    this.setSize(800, 35);
    this.title = "2020年5月";
    titJLabel.setText(this.title);
    this.add(titJLabel);
  }
}
