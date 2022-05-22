package team.frontend.components;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

import team.frontend.Context;
import team.lunar_solar.LS;
import team.utils.*;

enum LunarChar {
  初一, 初二, 初三, 初四, 初五,
  初六, 初七, 初八, 初九, 初十,
  十一, 十二, 十三, 十四, 十五,
  十六, 十七, 十八, 十九, 廿十,
  廿一, 廿二, 廿三, 廿四, 廿五,
  廿六, 廿七, 廿八, 廿九, 三十,
} // 未定义，测试时用来占位

enum MonthChar {
  正月, 二月, 三月, 四月,
  五月, 六月, 七月, 八月,
  九月, 十月, 十一月, 十二月,
}

class DayBox extends JPanel {
  private int year;
  private int month; // 1 月 month = 1
  private int solarDateNum; // 阳历 19 号 solarDate = 19
  private String lunarDateText;

  DayBox(int solarDateNum, int month, int year) {
    this.year = year;
    this.month = month;
    this.solarDateNum = solarDateNum;
    setDate(solarDateNum);
    this.add(new JLabel(String.valueOf(solarDateNum)));
    this.add(new JLabel(String.valueOf(lunarDateText)));
    this.setSize(90, 80);
  }

  /**
   * 用于设置这个格子的阳历和阴历日期
   * 测试阶段暂时把阴历和阳历设置成一样了
   * 因为阳历有 31 天，所以阴历词语枚举设置了一个占位符
   * 
   * @param solarDate 阳历日期数字
   */
  public void setDate(int solarDateNum) {

    int lunarDateNum = LS.solarToLunar(this.year, this.month, this.solarDateNum)[2];
    int lunarMonthNum = LS.solarToLunar(this.year, this.month, this.solarDateNum)[1];
    System.out.println(this.year + " " + this.month + " " + solarDateNum + " " + lunarDateNum);
    if (lunarDateNum == 1)
      this.lunarDateText = MonthChar.values()[lunarMonthNum - 1].toString();
    else
      this.lunarDateText = LunarChar.values()[lunarDateNum - 1].toString();
  }
}

public class CalendarGrid extends JPanel {
  private DayBox[] dayBoxGroup = new DayBox[42];

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
      int year = Context.month - 1 == 0 ? Context.year - 1 : Context.year;
      int month = Context.month - 1 == 0 ? 12 : Context.month - 1;
      dayBoxGroup[i] = new DayBox(DateCalculator.dayOfLastMonth(date) - (firstDay - i - 1), month, year);
      this.add(dayBoxGroup[i]);
    }

    // 渲染表中本月的所有日期
    for (int i = firstDay; i < dayOfMonth + firstDay; i++) {
      dayBoxGroup[i] = new DayBox(i - firstDay + 1, Context.month, Context.year);
      this.add(dayBoxGroup[i]);
    }

    // 渲染表中下个月的部分日期
    for (int i = dayOfMonth + firstDay; i < 42; i++) {
      int year = Context.month + 1 == 13 ? Context.year + 1 : Context.year;
      int month = Context.month + 1 == 13 ? 1 : Context.month + 1;

      dayBoxGroup[i] = new DayBox(i - dayOfMonth - firstDay + 1, month, year);
      this.add(dayBoxGroup[i]);
    }
  }

  public CalendarGrid(Calendar date) {
    this.setLayout(new GridLayout(6, 7));
    this.renderBox(date);
  }
}
