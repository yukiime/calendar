package team.frontend.components;

import javax.swing.*;

import team.Data.ItemsWriter;
import team.frontend.Context;
import team.frontend.EntranceFrame;
import team.frontend.QueryFrame;

import java.awt.event.*;
import java.util.Calendar;

class HandleClickQueryDate implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        QueryFrame qfrm = new QueryFrame();
    }
}

// 存储，调用序列化方法
class SaveDate implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        ItemsWriter.writerAllItems();
    }
}

class HandleBackToToday implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH) + 1;
        int solarDate = date.get(Calendar.DATE);
        Context.toggleFullDateInContext(year, month, 1);
        EntranceFrame.header.setSelectedDate(year, month);
        for (DayBox item : CalendarGrid.dayBoxGroup) {
            if (item.getYear() == year && item.getMonth() == month && item.getSolarDateNum() == solarDate) {
                item.select();
                break;
            }
        }
    }
}

class FgMenu extends JMenu {

    private JMenuItem mQuery = new JMenuItem("Query Date");
    private JMenuItem saveItems = new JMenuItem("Save"); // 存储按钮
    private JMenuItem backToToday = new JMenuItem("Back to today"); // 存储按钮

    FgMenu(String title) {
        super(title);
        this.add(mQuery);
        mQuery.addActionListener(new HandleClickQueryDate());
        this.add(saveItems);
        saveItems.addActionListener(new SaveDate());
        this.add(backToToday);
        backToToday.addActionListener(new HandleBackToToday());
    }
}

public class TopMenu extends JMenuBar {
    static FgMenu mTools = new FgMenu("Tools");

    public TopMenu() {
        this.add(mTools);
    }
}
