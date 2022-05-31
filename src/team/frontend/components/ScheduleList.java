package team.frontend.components;

import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import team.Item.ItemSchedule.CommemorationDay;
import team.Item.ItemSchedule.Festival;
import team.Item.ItemSchedule.Note;
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

abstract class ItemWrapper<T> extends JPanel {
  protected NewLabel contentLabel;
  protected String content;
  protected T instance;

  ItemWrapper(String content) {
    this.content = content;
    this.contentLabel = new NewLabel(this.content);
    this.add(this.contentLabel);
    this.add(this.contentLabel);
    // this.setSize(170, 50);
    this.addMouseListener(new ms());
    this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
  }

  abstract void setStyle();

  public String getContent() {
    return this.content;
  }

  public T getInstance() {
    return instance;
  }
}

class ScheduleWrapper extends ItemWrapper<Schedule> {
  public ScheduleWrapper(Schedule ref) {
    super(ref.getContent());
    this.instance = ref;
  }

  public void setStyle() {
  }
}

class NoteWrapper extends ItemWrapper<Note> {
  public NoteWrapper(Note ref) {
    super(ref.getContent());
    this.instance = ref;
  }

  public void setStyle() {
  }

}

class FestivalWrapper extends ItemWrapper<Festival> {
  public FestivalWrapper(Festival ref) {
    super(ref.getContent());
    this.instance = ref;
  }

  public void setStyle() {
  }
}

class CommemorationDayWrapper extends ItemWrapper<CommemorationDay> {
  public CommemorationDayWrapper(CommemorationDay ref) {
    super(ref.getContent());
    this.instance = ref;
  }

  public void setStyle() {
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
      scheduleListData.add(new ScheduleWrapper(item));
    }
    for (ScheduleWrapper item : scheduleListData) {
      this.add(item);
    }
    this.updateUI();
  }
}
