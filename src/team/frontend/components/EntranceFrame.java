package team.frontend.components;

import javax.swing.*;

import team.frontend.MenuBar;

import java.awt.*;
import java.util.Calendar;

public class EntranceFrame extends JFrame {

  static Calendar date = Calendar.getInstance();
  static MenuBar tb = new MenuBar();

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

  public static void main(String[] args) {
    EntranceFrame entrance = new EntranceFrame("Calendar");
    entrance.setVisible(true);
  }
}
