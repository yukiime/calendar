package team.frontend.components;

import javax.swing.*;

import team.utils.NewLabel;

public class Sider extends JPanel {
  public static NewLabel yearLabel = new NewLabel();

  public Sider(int year) {
    // this.setLayout()
    this.add(new NewLabel("h1", "详细信息"));
    this.add(yearLabel);
    renderSider(year);
  }

  public void renderSider(int year) {
    yearLabel = new NewLabel("h2", String.valueOf(year));
  }
}
