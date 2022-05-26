package team.frontend.components;

import java.util.Calendar;

import javax.swing.*;

import team.Item.ItemSchedule.Schedule;
import team.Item.ItemsWork.FindDaySth;
import team.Projectexception.ValueException;
import team.frontend.Context;
import team.utils.DateCalculator;
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

  /**
   * 用于渲染侧栏
   * 需要显示的信息有日历详细信息以及日程
   * 
   * @param year          年
   * @param month         月
   * @param solarDate     阳历日期
   * @param lunarDateText 阴历日期
   */
  public void renderSider(int year, int month, int solarDate, String lunarDateText) {
    yearLabel.setContent("h2", String.valueOf(year));
    monthLabel.setContent("h2", String.valueOf(month));
    solarDateLabel.setContent("h2", String.valueOf(solarDate));
    lunarDateTextLabel.setContent("h2", String.valueOf(lunarDateText));
    long timeStamp = DateCalculator.get0clockTimeStamp(year, month, solarDate);
    System.out.println(timeStamp);
    try {
      System.out.println(FindDaySth.findAllSchedule(timeStamp).size());
      // TODO:
      
    } catch (ValueException e) {
      e.printStackTrace();
    }
  }
}
