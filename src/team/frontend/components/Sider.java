package team.frontend.components;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import team.frontend.Context;
import team.frontend.CreateScheduleFrame;
import team.utils.DateCalculator;
import team.utils.DateEvent;
import team.utils.LunarSolar;
import team.utils.VehicleLimit;

class HandleClickCreateBtn implements ActionListener {
  public HandleClickCreateBtn() {

  }

  public void actionPerformed(ActionEvent e) {
    CreateScheduleFrame csf = new CreateScheduleFrame();
  }

}

public class Sider extends JPanel {
  public static NewLabel year_monthLabel = new NewLabel();
  public static NewLabel solarDateLabel = new NewLabel();
  public static NewLabel weekDayLabel = new NewLabel();
  public static NewLabel vehicleLabel = new NewLabel();
  public static NewLabel lunarDateTextLabel = new NewLabel();
  public static NewLabel todayFortuneLabel = new NewLabel();
  public static NewLabel todayMisfortuneLabel = new NewLabel();
  public static JButton createBtn = new JButton("添加日程");
  public static ItemList itemList = new ItemList(
      DateCalculator.get0clockTimeStamp(Context.year, Context.month, Context.solarDate));
  public static ArrayList<ScheduleWrapper> scheduleListData = new ArrayList<ScheduleWrapper>();

  public Sider() {
    this.setLayout(new BorderLayout());
  }

  public Sider(int year, int month, int solarDate) {
    this();

    JPanel jp = new JPanel();
    jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
    this.setBackground(Context.goldColors[3]);
    jp.add(year_monthLabel);
    jp.add(solarDateLabel);
    jp.add(weekDayLabel);
    jp.add(lunarDateTextLabel);
    jp.add(todayFortuneLabel);
    jp.add(todayMisfortuneLabel);
    jp.add(vehicleLabel);
    jp.add(createBtn);
    this.add("North", jp);
    jp.setBackground(Context.goldColors[3]);
    this.add("Center", itemList);

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
    int[] tmp = LunarSolar.solarToLunar(year, month, solarDate);
    String lunarDateText = Context.LunarChar.values()[tmp[2] - 1].toString();
    String lunarMonthText = (tmp[3] == 1 ? "闰" : "") + Context.MonthChar.values()[tmp[1] - 1].toString();

    solarDateLabel.setContent("h1", String.valueOf(solarDate));
    year_monthLabel.setContent("h4", String.valueOf(year) + "年" + String.valueOf(month) + "月");
    weekDayLabel.setContent("h4", Context.DayOfWeekChar.values()[Context.selectedNum % 7].toString());
    vehicleLabel.setContent("text danger", VehicleLimit.computeVehicle(Context.selectedNum % 7));
    lunarDateTextLabel.setContent("text", String.valueOf(lunarMonthText) + String.valueOf(lunarDateText));
    todayFortuneLabel.setContent("text", DateEvent.todayFortune(solarDate)[0]);
    todayMisfortuneLabel.setContent("text", DateEvent.todayFortune(solarDate)[1]);

    long timeStamp = DateCalculator.get0clockTimeStamp(year, month, solarDate);

    try {
      itemList.renderList(timeStamp);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
