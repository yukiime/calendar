package team.frontend;

import javax.swing.*;
import javax.swing.text.Style;

import team.lunar_solar.LS;

import java.awt.*;
import java.awt.event.*;

enum MonthChar {
  正月, 二月, 三月, 四月,
  五月, 六月, 七月, 八月,
  九月, 十月, 十一月, 十二月,
}

enum LunarChar {
  初一, 初二, 初三, 初四, 初五,
  初六, 初七, 初八, 初九, 初十,
  十一, 十二, 十三, 十四, 十五,
  十六, 十七, 十八, 十九, 廿十,
  廿一, 廿二, 廿三, 廿四, 廿五,
  廿六, 廿七, 廿八, 廿九, 三十,
}

class HandleParseLunar2Solar implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    QueryFrame.rp.setResult("lun2sor");

    // TODO: convert lunar to solar calendar
  }
}

class HandleParseSolar2Lunar implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    QueryFrame.rp.setResult("sor2lun");
    String tmp = Solar2LunarPane.solarInput.getText();
    try {
      int year = Integer.parseInt(tmp.split("年")[0]);
      int month = Integer.parseInt(tmp.split("年")[1].split("月")[0]);
      int day = Integer.parseInt(tmp.split("年")[1].split("月")[1].split("日")[0]);

      int[] res = LS.solarToLunar(year, month, day);
      QueryFrame.rp
          .setResult(
              "农历 " + res[0] + " 年 " + (res[3] == 1 ? "闰 " : " ") + MonthChar.values()[res[1] - 1].toString() + " "
                  + LunarChar.values()[res[2] - 1]);
    } catch (Exception err) {
      System.err.println(err.getMessage());
      QueryFrame.rp.setResult("转换失败");
    }
  }
}

class Lunar2SolarPane extends JPanel {
  public static JTextField lunarInput = new JTextField(12);
  public static JButton btn = new JButton("转换成阳历");

  Lunar2SolarPane() {
    this.setLayout(new FlowLayout());
    this.add(new JLabel("阴历日期："));
    this.add(lunarInput);
    btn.addActionListener(new HandleParseLunar2Solar());
    this.add(btn);
  }
}

class Solar2LunarPane extends JPanel {
  public static JTextField solarInput = new JTextField(12);
  public static JButton btn = new JButton("转换成阴历");

  Solar2LunarPane() {
    this.setLayout(new FlowLayout());
    this.add(new JLabel("阳历日期："));
    this.add(solarInput);
    btn.addActionListener(new HandleParseSolar2Lunar());
    this.add(btn);

  }
}

class ResultPane extends JPanel {
  private JLabel result = new JLabel("等待输入...");

  ResultPane() {
    this.add(new JLabel("结果："));
    this.add(result);
  }

  public void setResult(String res) {
    result.setText(res);
  }
}

public class QueryFrame extends JFrame {
  public static Solar2LunarPane s2lp = new Solar2LunarPane();
  public static Lunar2SolarPane l2sp = new Lunar2SolarPane();
  public static ResultPane rp = new ResultPane();

  public QueryFrame() {
    super("Query Date");
    setSize(400, 200);
    Container container = this.getContentPane();
    container.setLayout(new FlowLayout());
    container.add(s2lp);
    container.add(l2sp);
    container.add(rp);

    centerWindow();
    this.setVisible(true);
  }

  public void centerWindow() {
    java.awt.Toolkit tk = getToolkit();
    Dimension dm = tk.getScreenSize();
    setLocation((int) (dm.getWidth() - getWidth()) / 2, (int) (dm.getHeight() - getHeight()) / 2);
  }
}
