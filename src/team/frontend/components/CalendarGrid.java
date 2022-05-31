package team.frontend.components;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

import team.frontend.Context;
import team.frontend.EntranceFrame;
import team.lunar_solar.LS;
import team.utils.*;

class DayBoxMS implements MouseListener {

  public void mousePressed(MouseEvent e) {
    DayBox obj = (DayBox) e.getSource();

    CalendarGrid.dayBoxGroup[Context.selectedNum].release(); // 释放上次的日期格子
    obj.select(); // 选中当前点击的格子
    Context.selectedNum = obj.getIndex(); // 储存当前选中格子的索引
    Context.solarDate = obj.getSolarDateNum();

    EntranceFrame.sd.renderSider(obj.getYear(), obj.getMonth(), obj.getSolarDateNum(), obj.getLunarDateText());
  }

  public void mouseReleased(java.awt.event.MouseEvent e) {
  }

  public void mouseClicked(java.awt.event.MouseEvent e) {
  }

  public void mouseEntered(java.awt.event.MouseEvent e) {
  }

  public void mouseExited(java.awt.event.MouseEvent e) {
  }

}

class DayBox extends JPanel {
  private int index;
  private int year;
  private int month; // 1 月 month = 1
  private int solarDateNum; // 阳历 19 号 solarDate = 19
  private String lunarDateText;
  private Color defaultBackgroundColor;

  DayBox(int solarDateNum, int month, int year, int index) {
    this.index = index;
    this.year = year;
    this.month = month;
    this.solarDateNum = solarDateNum;
    setDate(solarDateNum);
    this.addMouseListener(new DayBoxMS());
    if (month != Context.month) {
      this.defaultBackgroundColor = Context.goldColors[1];
      this.add(new NewLabel("h4 weak", String.valueOf(solarDateNum)));
      this.add(new NewLabel("text weak", lunarDateText));
    } else {
      this.defaultBackgroundColor = Context.goldColors[2];
      this.add(new NewLabel("h4", String.valueOf(solarDateNum)));
      this.add(new NewLabel(lunarDateText));
    }

    this.setBackground(defaultBackgroundColor);
    this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
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

  public String getLunarDateText() {
    return this.lunarDateText;
  }

  public int getIndex() {
    return this.index;
  }

  public void select() {
    this.setBackground(Context.goldColors[4]);
  }

  public void release() {
    this.setBackground(this.defaultBackgroundColor);
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
    try {
      for (DayBox item : dayBoxGroup) {
        this.remove(item);
      }
    } catch (Exception e) {

    }
    // 该月1号在日历表(每行从星期日数起)一行中的第几个
    int firstDay = date.get(Calendar.DAY_OF_WEEK) - 1;

    // 该月的天数
    int dayOfMonth = date.getActualMaximum(Calendar.DAY_OF_MONTH);

    // 渲染表中上个月的部分日期
    for (int i = 0; i < firstDay; i++) {
      int year = Context.month - 1 == 0 ? Context.year - 1 : Context.year;
      int month = Context.month - 1 == 0 ? 12 : Context.month - 1;
      dayBoxGroup[i] = new DayBox(DateCalculator.dayOfLastMonth(date) - (firstDay - i - 1), month, year, i);
      this.add(dayBoxGroup[i]);
    }

    // 渲染表中本月的所有日期
    for (int i = firstDay; i < dayOfMonth + firstDay; i++) {
      dayBoxGroup[i] = new DayBox(i - firstDay + 1, Context.month, Context.year, i);
      this.add(dayBoxGroup[i]);
    }

    // 渲染表中下个月的部分日期
    for (int i = dayOfMonth + firstDay; i < 42; i++) {
      int year = Context.month + 1 == 13 ? Context.year + 1 : Context.year;
      int month = Context.month + 1 == 13 ? 1 : Context.month + 1;

      dayBoxGroup[i] = new DayBox(i - dayOfMonth - firstDay + 1, month, year, i);
      this.add(dayBoxGroup[i]);
    }
    this.updateUI();
  }

  public CalendarGrid(Calendar date) {
    this.setSize(630, 425);
    this.setLayout(new GridLayout(6, 7));

    this.renderBox(date);

  }
}
