package team.frontend.components;

import java.util.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

import team.Item.ItemSchedule.CommemorationDay;
import team.Item.ItemSchedule.Festival;
import team.Item.ItemSchedule.Schedule;
import team.Item.ItemsWork.FindDaySth;
import team.Projectexception.ValueException;
import team.frontend.Context;
import team.frontend.ModifyFrame;
import team.utils.Alert;
import team.utils.NewLabel;

class ms<T1 extends ItemWrapper<T2>, T2> implements MouseListener {

    public void mouseReleased(MouseEvent e) {
        @SuppressWarnings("unchecked")
        T1 wrapper = (T1) e.getSource();
        if (!(wrapper.getRef() instanceof Festival)) {
            ModifyFrame<T2> modifyFrame = new ModifyFrame<T2>(wrapper.getRef());
        }
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
    protected T ref;

    ItemWrapper(String content) {
        this.content = content;
        this.contentLabel = new NewLabel(this.content);
        this.add(this.contentLabel);
        this.add(this.contentLabel);
    }

    public String getContent() {
        return this.content;
    }

    public T getRef() {
        return ref;
    }

    /**
     * 不同优先级不同样式
     *
     * @param order 优先级，节日纪念日默认为4
     */
    public void setStyle(int order) {
        this.setBackground(Context.orderColors[order]);
        this.setBorder(BorderFactory.createLineBorder(Context.orderBorderColors[order], 2));
    }
}

class ScheduleWrapper extends ItemWrapper<Schedule> {
    public ScheduleWrapper(Schedule ref) {
        super(ref.getContent());
        this.ref = ref;
        this.addMouseListener(new ms<ScheduleWrapper, Schedule>());
        this.setStyle(ref.getOrder());
    }
}

class CommemorationDayWrapper extends ItemWrapper<CommemorationDay> {
    public CommemorationDayWrapper(CommemorationDay ref) {
        super(ref.getContent());
        this.ref = ref;
        this.addMouseListener(new ms<CommemorationDayWrapper, CommemorationDay>());
        this.setStyle(4);
    }
}

public class ItemList extends JScrollPane {
    public static ArrayList<ScheduleWrapper> scheduleListData = new ArrayList<ScheduleWrapper>();
    public static ArrayList<CommemorationDayWrapper> commDayListData = new ArrayList<CommemorationDayWrapper>();
    private JPanel container = new JPanel();
    private JPanel listPanel = new JPanel();

    public ItemList(long timeStamp) {
        this.container.setBackground(Context.goldColors[3]);
        this.setViewportView(container);
        try {
            this.renderList(timeStamp);
        } catch (Exception e) {
            Alert.warn(e.getMessage());
        }
        this.listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        this.container.setLayout(new BorderLayout());
        this.container.add("North", listPanel);
    }

    public void renderList(long timeStamp) throws ValueException {

        // TODO: Genetic format

        // 节日纪念日
        for (CommemorationDayWrapper item : commDayListData) {
            this.listPanel.remove(item);
        }

        commDayListData.clear();
        for (CommemorationDay item : FindDaySth.findCommemorationDays_festival(timeStamp)) {
            commDayListData.add(new CommemorationDayWrapper(item));
        }
        for (CommemorationDayWrapper item : commDayListData) {
            this.listPanel.add(item);
        }

        // 日程
        for (ScheduleWrapper item : scheduleListData) {
            this.listPanel.remove(item);
        }
        scheduleListData.clear();
        for (Schedule item : FindDaySth.findAllSchedule(timeStamp)) {
            scheduleListData.add(new ScheduleWrapper(item));
        }
        for (ScheduleWrapper item : scheduleListData) {
            this.listPanel.add(item);
        }

        this.updateUI();
    }
}
