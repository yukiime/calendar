package team.frontend;

import javax.swing.*;

import team.frontend.components.CalendarGrid;
import team.frontend.components.Header;
import team.frontend.components.TopMenu;

import java.awt.*;
import java.util.Calendar;

public class EntranceFrame extends JFrame {

  static Calendar date = Calendar.getInstance();
  static TopMenu tb = new TopMenu();

  public EntranceFrame(String title) {
    super(title);
    int month = 12; // 数字几代表几月
    date.set(2021, month - 1, 1);
    Header header = new Header(date);
    CalendarGrid cg = new CalendarGrid(date);
    setSize(800, 500);
    this.setJMenuBar(tb);
    this.setLayout(new BorderLayout());
    this.add("North", header);
    this.add("Center", cg);
    centerWindow();
  }

  public void centerWindow() {
    java.awt.Toolkit tk = getToolkit();
    Dimension dm = tk.getScreenSize();
    setLocation((int) (dm.getWidth() - getWidth()) / 2, (int) (dm.getHeight() - getHeight()) / 2);
  }

}
