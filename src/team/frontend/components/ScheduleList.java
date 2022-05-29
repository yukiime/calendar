package team.frontend.components;

import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import team.Item.ItemSchedule.Schedule;
import team.Item.ItemsWork.FindDaySth;
import team.Projectexception.ValueException;
import team.frontend.ModifyFrame;
import team.utils.NewLabel;

class ms implements MouseListener {

  public void mouseReleased(MouseEvent e) {
    ScheduleWrapper sw = (ScheduleWrapper) e.getSource();
    System.out.println(sw.getContent());
    ModifyFrame modifyFrame = new ModifyFrame();
  }

  public void mousePressed(java.awt.event.MouseEvent e) {
  }

  public void mouseClicked(java.awt.event.MouseEvent e) {
  }

  public void mouseEntered(java.awt.event.MouseEvent e) {
  }

  public void mouseExited(java.awt.event.MouseEvent e) {
  }

}

class ScheduleWrapper extends JPanel {
  private NewLabel contentLabel;
  private String content;

  public ScheduleWrapper(String content) {
    this.content = content;

    this.contentLabel = new NewLabel("text", content);
    this.add(this.contentLabel);
    this.add(this.contentLabel);
    this.setSize(170, 50);
    this.addMouseListener(new ms());
    // this.add(new JButton("修改"));
    this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
  }

  public String getContent() {
    return this.content;
  }

}

public class ScheduleList extends JPanel {
  public static ArrayList<ScheduleWrapper> scheduleListData = new ArrayList<ScheduleWrapper>();

  public ScheduleList(long timeStamp) {
    try {
      this.renderScheduleList(timeStamp);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

  public void renderScheduleList(long timeStamp) throws ValueException {
    for (ScheduleWrapper item : scheduleListData) {
      this.remove(item);
    }
    scheduleListData.clear();
    for (Schedule item : FindDaySth.findAllSchedule(timeStamp)) {
      scheduleListData.add(new ScheduleWrapper(item.getContent()));
    }
    for (ScheduleWrapper item : scheduleListData) {
      this.add(item);
    }

  }
}
