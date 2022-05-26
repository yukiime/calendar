package team.frontend;

import javax.swing.*;

import team.frontend.components.*;

import java.awt.*;
import java.util.*;

public class EntranceFrame extends JFrame {

  public static Calendar date = Calendar.getInstance();
  public static TopMenu tb = new TopMenu();
  public static Sider sd;
  public static Header header;
  public static CalendarGrid cg;

  public EntranceFrame(String title) {
    super(title);
    date.set(Context.year, Context.month - 1, 1);
    header = new Header(date);
    sd = new Sider(2022, 5, 24, "fuck");
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
