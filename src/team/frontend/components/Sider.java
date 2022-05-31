package team.frontend.components;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import team.Item.ItemsWork.FindDaySth;
import team.frontend.Context;
import team.frontend.CreateScheduleFrame;
import team.lunar_solar.LS;
import team.utils.DateCalculator;
import team.utils.DateEvent;
import team.utils.NewLabel;

class HandleClickCreateBtn implements ActionListener {
  public HandleClickCreateBtn() {

  }

  public void actionPerformed(ActionEvent e) {
    CreateScheduleFrame csf = new CreateScheduleFrame();
  }

}

public class Sider extends JPanel {
  public static NewLabel yearLabel = new NewLabel();
  public static NewLabel monthLabel = new NewLabel();
  public static NewLabel solarDateLabel = new NewLabel();
  public static NewLabel lunarDateTextLabel = new NewLabel();
  public static NewLabel lunarMonthTextLabel = new NewLabel();
  public static NewLabel todayFortuneLabel = new NewLabel();
  public static NewLabel todayMisfortuneLabel = new NewLabel();
  public static JButton createBtn = new JButton("+");
  public static ScheduleList scheduleList = new ScheduleList(
      DateCalculator.get0clockTimeStamp(Context.year, Context.month, Context.solarDate));
  public static ArrayList<ScheduleWrapper> scheduleListData = new ArrayList<ScheduleWrapper>();

  public Sider() {
    // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setLayout(new FlowLayout(FlowLayout.LEFT));
  }

  public Sider(int year, int month, int solarDate) {
    this();
    // this.setLayout()
    this.setBackground(Context.goldColors[3]);
    this.add(solarDateLabel);
    this.add(yearLabel);
    this.add(monthLabel);
    this.add(lunarMonthTextLabel);
    this.add(lunarDateTextLabel);
    this.add(todayFortuneLabel);
    this.add(todayMisfortuneLabel);
    this.add(createBtn);
    this.add(scheduleList);

    createBtn.addActionListener(new HandleClickCreateBtn());
    renderSider(year, month, solarDate);
  }

  /**
   * 用于渲染侧栏
   * 需要显示的信息有日历详细信息以及日程
   * 
   * @param year      年
   * @param month     月
   * @param solarDate 阳历日期
   */
  public void renderSider(int year, int month, int solarDate) {
    int[] tmp = LS.solarToLunar(year, month, solarDate);
    String lunarDateText = Context.LunarChar.values()[tmp[2] - 1].toString();
    String lunarMonthText = Context.MonthChar.values()[tmp[1] - 1].toString();

    yearLabel.setContent("h4", String.valueOf(year) + "年");
    monthLabel.setContent("h3", String.valueOf(month) + "月");
    solarDateLabel.setContent("h1", String.valueOf(solarDate));
    lunarDateTextLabel.setContent("text", String.valueOf(lunarDateText));
    lunarMonthTextLabel.setContent("text", String.valueOf(lunarMonthText));
    todayFortuneLabel.setContent("text", DateEvent.todayFortune(solarDate)[0]);
    todayMisfortuneLabel.setContent("text", DateEvent.todayFortune(solarDate)[1]);
    long timeStamp = DateCalculator.get0clockTimeStamp(year, month, solarDate);

    try {

      // 5.26 测试
      // TestItem.siderTest(timeStamp);
      // 测试结束

      System.out.println(timeStamp);
      scheduleList.renderScheduleList(timeStamp);

      System.out.println(FindDaySth.findAllSchedule(timeStamp).size());
      // TODO:

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
