package team.frontend;

import javax.swing.*;

import team.frontend.components.*;
import team.utils.DateCalculator;
import team.utils.StaticEvent;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class EntranceFrame extends JFrame implements MouseWheelListener {

    public static TopMenu tb = new TopMenu();
    public static Sider sd = new Sider(Context.year, Context.month, Context.solarDate);
    public static Header header;
    public static JPanel main = new JPanel();
    public static CalendarGrid cg;

    public EntranceFrame(String title) {
        super(title);
        Calendar date = DateCalculator.getCalendarInstance(Context.year, Context.month, 1);
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseWheelListener(this);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        e.consume();
        int notches = e.getWheelRotation();
        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL)
            if (notches < 0) {
                header.goBack();
            } else {
                header.goNext();
            }
    }

}
