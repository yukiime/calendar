package team.frontend.components;

import java.util.Calendar;

import javax.swing.*;

import team.frontend.Context;
import team.utils.NewLabel;

public class Header extends JPanel {
  private String title;
  private NewLabel titleLabel = new NewLabel();

  public Header(Calendar date) {
    // this.setSize(800, 35);
    this.title = String.valueOf(date.get(Calendar.YEAR)) + "年" + String.valueOf(date.get(Calendar.MONTH) + 1) + "月";
    titleLabel.setStyle("h3");
    titleLabel.setText(title);
    this.add(titleLabel);
    this.setBackground(Context.goldColors[5]);
  }
}
