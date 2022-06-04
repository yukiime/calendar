package team.frontend.components;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.*;

import team.frontend.Context;
import team.safe.DateRangeException;
import team.utils.Alert;

public class Header extends JPanel {
  private JComboBox<Integer> yearBox = new JComboBox<Integer>();
  private JComboBox<Integer> monthBox = new JComboBox<Integer>();

  public Header(Calendar date) {
    for (int i = 0; i < 198; i++) {
      yearBox.addItem(i + 1902);
    }
    for (int i = 1; i <= 12; i++) {
      monthBox.addItem(i);
    }
    this.add(yearBox);
    this.add(new NewLabel("h3", "年"));
    this.add(monthBox);
    this.add(new NewLabel("h3", "月"));

    this.yearBox.setSelectedIndex(date.get(Calendar.YEAR) - 1902);
    this.monthBox.setSelectedIndex(date.get(Calendar.MONTH));

    this.yearBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getID() == ItemEvent.ITEM_STATE_CHANGED) {
          @SuppressWarnings("unchecked")
          JComboBox<Integer> cb = (JComboBox<Integer>) e.getSource();
          Context.toggleFullDateInContext(cb.getSelectedIndex() + 1902, -1, 1);
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

  public void goNext() {
    int year = yearBox.getSelectedIndex() + 1902;
    int month = monthBox.getSelectedIndex() + 1;
    try {
      if (year >= 2099 && month == 12)
        throw new DateRangeException();
      year = month == 12 ? year + 1 : year;
      month = month == 12 ? 1 : month + 1;
      Context.toggleFullDateInContext(year, month, 1);
      this.setSelectedDate(year, month);
    } catch (DateRangeException err) {
      Alert.warn(err.getMessage());
    }
  }

  public void goBack() {
    int year = yearBox.getSelectedIndex() + 1902;
    int month = monthBox.getSelectedIndex() + 1;
    try {
      if (year <= 1902 && month == 1)
        throw new DateRangeException();
      year = month == 1 ? year - 1 : year;
      month = month == 1 ? 12 : month - 1;
      Context.toggleFullDateInContext(year, month, 1);
      this.setSelectedDate(year, month);
    } catch (DateRangeException err) {
      Alert.warn(err.getMessage());
    }
  }

  public void setSelectedDate(int year, int month) {
    this.yearBox.setSelectedIndex(year - 1902);
    this.monthBox.setSelectedIndex(month - 1);
  }

}
