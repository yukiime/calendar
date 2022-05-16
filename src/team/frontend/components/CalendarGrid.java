package team.frontend.components;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import team.utils.*;

enum LunarChar {
  初一, 初二, 初三, 初四, 初五,
  初六, 初七, 初八, 初九, 初十,
  十一, 十二, 十三, 十四, 十五,
  十六, 十七, 十八, 十九, 廿十,
  廿一, 廿二, 廿三, 廿四, 廿五,
  廿六, 廿七, 廿八, 廿九, 三十,
  未定义
} // 未定义，测试时用来占位

class DayBox extends JPanel {
  private int solarDate;
  private LunarChar lunarDate;

  DayBox(int solarDate) {
    setDate(solarDate);
    this.add(new JLabel(String.valueOf(solarDate)));
    this.add(new JLabel(String.valueOf(lunarDate)));
    this.setSize(90, 80);
  }

  /**
   * 用于设置这个格子的阳历和阴历日期
   * 测试阶段暂时把阴历和阳历设置成一样了
   * 因为阳历有 31 天，所以阴历词语枚举设置了一个占位符
   * 
   * @param solarDate 阳历日期数字
   */
  public void setDate(int solarDate) {
    this.solarDate = solarDate;
    this.lunarDate = LunarChar.values()[solarDate - 1];
    // TODO: run parseFunc
  }
}

public class CalendarGrid extends JPanel {
  private DayBox[] dayBoxGroup = new DayBox[35];

  /**
   * 用于渲染每个格子
   * 一张完整的月日历表有 5 * 7 个格子(5 行, 7 列)
   * 每行的第一个格子是星期日
   * 渲染分三步，先上个月，再本月，最后下个月
   * 
   * @param date
   */
  public void renderBox(Calendar date) {
    // 该月1号在日历表(每行从星期日数起)一行中的第几个
    int firstDay = date.get(Calendar.DAY_OF_WEEK) - 1;

    // 该月的天数
    int dayOfMonth = date.getActualMaximum(Calendar.DAY_OF_MONTH);

    // 渲染表中上个月的部分日期
    for (int i = 0; i < firstDay; i++) {
      dayBoxGroup[i] = new DayBox(DateCalculator.dayOfLastMonth(date) - (firstDay - i - 1));
      this.add(dayBoxGroup[i]);
    }

    // 渲染表中本月的所有日期
    for (int i = firstDay; i < dayOfMonth + firstDay; i++) {
      dayBoxGroup[i] = new DayBox(i - firstDay + 1);
      this.add(dayBoxGroup[i]);
    }

    // 渲染表中下个月的部分日期
    for (int i = dayOfMonth + firstDay; i < 35; i++) {
      dayBoxGroup[i] = new DayBox(i - dayOfMonth - firstDay + 1);
      this.add(dayBoxGroup[i]);
    }
  }

  public CalendarGrid(Calendar date) {
    this.setLayout(new GridLayout(5, 7));
    this.renderBox(date);
  }
}
