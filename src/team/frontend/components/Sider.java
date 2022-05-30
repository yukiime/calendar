package team.frontend.components;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

import team.Item.ItemSchedule.TestItem;
import team.Item.ItemsWork.FindDaySth;
import team.frontend.Context;
import team.frontend.CreateScheduleFrame;
import team.utils.DateCalculator;
import team.utils.NewLabel;

class HandleClickCreateBtn implements ActionListener {
  public HandleClickCreateBtn() {

  }

  public void actionPerformed(ActionEvent e) {
  //  CreateScheduleFrame csf = new CreateScheduleFrame();
  }

}

public class Sider extends JPanel {
  public static NewLabel yearLabel = new NewLabel();
  public static NewLabel monthLabel = new NewLabel();
  public static NewLabel solarDateLabel = new NewLabel();
  public static NewLabel lunarDateTextLabel = new NewLabel();
  public static JButton createBtn = new JButton("+");
  public static ScheduleList scheduleList = new ScheduleList(
      DateCalculator.get0clockTimeStamp(Context.year, Context.month, Context.solarDate));
  public static ArrayList<ScheduleWrapper> scheduleListData = new ArrayList<ScheduleWrapper>();

  public Sider() {
    this.setSize(300, 425);
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

  public Sider(int year, int month, int solarDate, String lunarDateText) {
    this();
    // this.setLayout()
    this.setBackground(Context.goldColors[3]);
    this.add(yearLabel);
    this.add(monthLabel);
    this.add(solarDateLabel);
    this.add(lunarDateTextLabel);
    this.add(createBtn);
    this.add(scheduleList);

    createBtn.addActionListener(new HandleClickCreateBtn());
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
