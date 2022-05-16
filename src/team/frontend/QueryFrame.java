package team.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HandleParseLunar2Solar implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    QueryFrame.rp.setResult("lun2sor");
    // TODO: convert lunar to solar calendar
  }
}

class HandleParseSolar2Lunar implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    QueryFrame.rp.setResult("sor2lun");
    // TODO: convert solar to lunar calendar
  }
}

class Lunar2SolarPane extends JPanel {
  private JTextField lunarInput = new JTextField(12);
  private JButton btn = new JButton("转换成阳历");

  Lunar2SolarPane() {
    this.setLayout(new FlowLayout());
    this.add(new JLabel("阴历日期："));
    this.add(lunarInput);
    btn.addActionListener(new HandleParseLunar2Solar());
    this.add(btn);
  }
}

class Solar2LunarPane extends JPanel {
  private JTextField solarInput = new JTextField(12);
  private JButton btn = new JButton("转换成阴历");

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
