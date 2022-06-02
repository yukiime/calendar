package team.frontend.components;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.*;

import team.frontend.Context;
import team.utils.NewLabel;

public class Header extends JPanel {
    private JComboBox<Integer> yearBox = new JComboBox<Integer>();
    private JComboBox<Integer> monthBox = new JComboBox<Integer>();

    public Header(Calendar date) {
        for (int i = 0; i < 200; i++) {
            yearBox.addItem(i + 1901);
        }
        for (int i = 1; i <= 12; i++) {
            monthBox.addItem(i);
        }
        this.add(yearBox);
        this.add(new NewLabel("h3", "年"));
        this.add(monthBox);
        this.add(new NewLabel("h3", "月"));

        this.yearBox.setSelectedIndex(date.get(Calendar.YEAR) - 1901);
        this.monthBox.setSelectedIndex(date.get(Calendar.MONTH));

        this.yearBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getID() == ItemEvent.ITEM_STATE_CHANGED) {
                    @SuppressWarnings("unchecked")
                    JComboBox<Integer> cb = (JComboBox<Integer>) e.getSource();
                    Context.toggleFullDateInContext(cb.getSelectedIndex() + 1901, -1, 1);
                }
            }
        });

        this.monthBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getID() == ItemEvent.ITEM_STATE_CHANGED) {
                    @SuppressWarnings("unchecked")
                    JComboBox<Integer> cb = (JComboBox<Integer>) e.getSource();
                    Context.toggleFullDateInContext(-1, cb.getSelectedIndex() + 1, 1);
                }
            }
        });
        this.setBackground(Context.goldColors[5]);
    }
}
