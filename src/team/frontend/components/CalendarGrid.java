package team.frontend.components;

import javax.swing.JLabel;
import javax.swing.JPanel;

class DayBox extends JPanel {
  private int solarDate;
  private int lunarDate;

  DayBox() {
    this.solarDate = 19;
    this.lunarDate = 20;
    this.add(new JLabel(String.valueOf(solarDate)));
    this.add(new JLabel(String.valueOf(lunarDate)));
    this.setSize(90, 80);
  }
}

public class CalendarGrid extends JPanel {
  private DayBox[] dayBoxGroup = new DayBox[35];

  public CalendarGrid() {
    for (int i = 0; i < 35; i++) {
      dayBoxGroup[i] = new DayBox();
      System.out.print(i);
      this.add(dayBoxGroup[i]);
    }
  }
}
