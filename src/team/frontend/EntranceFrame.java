package team.frontend;

import javax.swing.*;

import team.frontend.components.*;

import java.awt.*;
import java.util.*;

public class EntranceFrame extends JFrame {

  static Calendar date = Calendar.getInstance();
  static TopMenu tb = new TopMenu();
  static Sider sd;
  static Header header;
  static CalendarGrid cg;

  public EntranceFrame(String title) {
    super(title);
    date.set(Context.year, Context.month - 1, 1);
    header = new Header(date);
    sd = new Sider(Context.year);
    cg = new CalendarGrid(date);

    setSize(800, 500);
    this.setJMenuBar(tb);
    this.setLayout(new BorderLayout());
    this.add("North", header);
    this.add("Center", cg);
    this.add("East", sd);
    centerWindow();
  }

  public void centerWindow() {
    java.awt.Toolkit tk = getToolkit();
    Dimension dm = tk.getScreenSize();
    setLocation((int) (dm.getWidth() - getWidth()) / 2, (int) (dm.getHeight() - getHeight()) / 2);
  }

}
