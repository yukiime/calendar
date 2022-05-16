package team.frontend.components;

import javax.swing.*;
import java.awt.*;

enum LunarChar {
  初一, 初二, 初三, 初四, 初五,
  初六, 初七, 初八, 初九, 初十,
  十一, 十二, 十三, 十四, 十五,
  十六, 十七, 十八, 十九, 廿十,
  廿一, 廿二, 廿三, 廿四, 廿五,
  廿六, 廿七, 廿八, 廿九, 三十,
}

class DayBox extends JPanel {
  private int solarDate;
  private LunarChar lunarDate;

  DayBox(int solarDate) {
    resetDate(solarDate);
    this.add(new JLabel(String.valueOf(solarDate)));
    this.add(new JLabel(String.valueOf(lunarDate)));
    this.setSize(90, 80);
  }

  public void resetDate(int solarDate) {
    this.solarDate = solarDate;
    this.lunarDate = LunarChar.values()[solarDate - 1];
    // TODO: run parseFunc
  }
}

public class CalendarGrid extends JPanel {
  private DayBox[] dayBoxGroup = new DayBox[35];

  public static void renderBox() {
  }

  public CalendarGrid() {

    this.setLayout(new GridLayout(7, 5));
    for (int i = 0; i < 35; i++) {
      dayBoxGroup[i] = new DayBox(9);
      this.add(dayBoxGroup[i]);
    }

  }
}
