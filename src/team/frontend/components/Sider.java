package team.frontend.components;

import javax.swing.*;

import team.frontend.Context;
import team.utils.NewLabel;

public class Sider extends JPanel {
  public static NewLabel yearLabel = new NewLabel();
  public static NewLabel monthLabel = new NewLabel();
  public static NewLabel solarDateLabel = new NewLabel();
  public static NewLabel lunarDateTextLabel = new NewLabel();
  public static NewLabel[] scheduleList;

  public Sider() {
    this.setSize(170, 425);
  }

  public Sider(int year, int month, int solarDate, String lunarDateText) {
    this();
    // this.setLayout()
    this.setBackground(Context.goldColors[3]);
    this.add(yearLabel);
    this.add(monthLabel);
    this.add(solarDateLabel);
    this.add(lunarDateTextLabel);
    renderSider(year, month, solarDate, lunarDateText);
  }

  public void renderSider(int year, int month, int solarDate, String lunarDateText) {
    yearLabel.setContent("h2", String.valueOf(year));
    monthLabel.setContent("h2", String.valueOf(month));
    solarDateLabel.setContent("h2", String.valueOf(solarDate));
    lunarDateTextLabel.setContent("h2", String.valueOf(lunarDateText));
  }
}
