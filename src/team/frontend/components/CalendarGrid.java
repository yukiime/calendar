package team.frontend.components;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

import team.frontend.Context;
import team.lunar_solar.LS;
import team.utils.*;

class ms implements MouseListener {

  public void mouseClicked(MouseEvent e) {
    System.out.println(((DayBox) e.getSource()).getSolarDateNum());
    // TODO:
  }

  public void mousePressed(java.awt.event.MouseEvent e) {
  }

  public void mouseReleased(java.awt.event.MouseEvent e) {
  }

  public void mouseEntered(java.awt.event.MouseEvent e) {
  }

  public void mouseExited(java.awt.event.MouseEvent e) {
  }

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
    this.addMouseListener(new ms());
    this.add(new NewLabel("h1", String.valueOf(solarDateNum)));
    this.add(new NewLabel("h3", lunarDateText));
    this.setSize(90, 80);
    this.setBackground(new Color(255, 229, 143));
  }

  public void actionPerformed(ActionEvent e) {
    e.getActionCommand();
  }

  public int getYear() {
    return this.year;
  }

  public int getMonth() {
    return this.month;

  }

  public int getSolarDateNum() {
    return this.solarDateNum;
  }

  public String getLunString() {
    return this.lunarDateText;
  }

  /**
   * 用于设置这个格子的阳历和阴历日期
   * 测试阶段暂时把阴历和阳历设置成一样了
   * 因为阳历有 31 天，所以阴历词语枚举设置了一个占位符
   * 
   * @param solarDateNum 阳历日期数字
   */
  public void setDate(int solarDateNum) {

    int lunarDateNum = LS.solarToLunar(this.year, this.month, this.solarDateNum)[2];
    int lunarMonthNum = LS.solarToLunar(this.year, this.month, this.solarDateNum)[1];
    if (lunarDateNum == 1)
      this.lunarDateText = Context.MonthChar.values()[lunarMonthNum - 1].toString();
    else
      this.lunarDateText = Context.LunarChar.values()[lunarDateNum - 1].toString();
  }
}

public class CalendarGrid extends JPanel {
  public static DayBox[] dayBoxGroup = new DayBox[42];

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
