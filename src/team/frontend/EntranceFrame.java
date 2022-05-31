package team.frontend;

import javax.swing.*;

import team.frontend.components.*;
import team.utils.StaticEvent;

import java.awt.*;
import java.util.*;

public class EntranceFrame extends JFrame {

  public static Calendar date = Calendar.getInstance();
  public static TopMenu tb = new TopMenu();
  public static Sider sd = new Sider(2022, 5, 24, "fuck");
  public static Header header;
  public static JPanel main = new JPanel();
  public static CalendarGrid cg;

  public EntranceFrame(String title) {
    super(title);
    date.set(Context.year, Context.month - 1, 1);
    header = new Header(date);
    cg = new CalendarGrid(date);

    setSize(800, 500);
    this.setJMenuBar(tb);
    this.setLayout(new BorderLayout());
    this.add("North", header);
    this.add("Center", main);
    main.setLayout(new BorderLayout());
    main.add("North", new DayOfWeekBar());
    main.add("Center", cg);
    this.add("East", sd);
    sd.setPreferredSize(new Dimension(200, 425));
    StaticEvent.centerWindow(this);
  }

}
