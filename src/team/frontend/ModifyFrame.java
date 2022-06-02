package team.frontend;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;

import team.Item.ItemSchedule.CommemorationDay;
import team.Item.ItemSchedule.Schedule;
import team.Item.ItemsWork.CreateSth;
import team.Item.ItemsWork.CreateSthRepeat;
import team.Item.ItemsWork.DeleteSth;
import team.Item.ItemsWork.FindDaySth;
import team.Projectexception.ValueException;
import team.frontend.components.Sider;
import team.utils.NewInput;
import team.utils.NewLabel;
import team.utils.StaticEvent;

class HandleClickModifyBtn<T> implements ActionListener {
    private ModifyFrame<T> frame;

    /**
     *
     * @param frame 窗口的引用，用于关闭窗口
     */
    HandleClickModifyBtn(ModifyFrame<T> frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        String content = this.frame.getContent();
        boolean isRepeat = this.frame.getIsRepeat();
        short repeatType = this.frame.getRepeatType();
        short order = this.frame.getOrder();
        int duration = this.frame.getDuration();
        T ref = frame.getRef();
        // TODO: 纪念日处理

        if (ref instanceof Schedule) {
            long timeStamp = ((Schedule) ref).getCreateTime();
            try {
                DeleteSth.deleteSchedule((Schedule) ref);
                if (repeatType != 4)
                    CreateSthRepeat.createSchedule(timeStamp, content, isRepeat, repeatType,
                            order);
                else
                    CreateSthRepeat.createSchedule(timeStamp, content, isRepeat, duration,
                            repeatType, order);

                System.out.println(FindDaySth.findAllSchedule(timeStamp).size());
                Sider.itemList.renderList(timeStamp);
            } catch (Exception err) {
                System.err.println(err.getMessage());
            }
        } else if (ref instanceof CommemorationDay) {
            long timeStamp = ((CommemorationDay) ref).getCreateTime();
            try {
                DeleteSth.deleteCommemorationDays_festival(((CommemorationDay) frame.getRef()).getId());
                CreateSth.createCommemorationDay(timeStamp, content);
                Sider.itemList.renderList(timeStamp);
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }
        this.frame.dispose();
    }
}

class HandleClickDeleteBtn<T> implements ActionListener {
    private ModifyFrame<T> frame;

    HandleClickDeleteBtn(ModifyFrame<T> frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        if (frame.getRef() instanceof Schedule)
            DeleteSth.deleteSchedule((Schedule) frame.getRef());
        else if (frame.getRef() instanceof CommemorationDay)
            try {
                DeleteSth.deleteCommemorationDays_festival(((CommemorationDay) frame.getRef()).getId());
            } catch (Exception err) {
                System.err.println(err.getMessage());
            }
        try {
            Sider.itemList.renderList(frame.getTimeStamp());
        } catch (ValueException err) {
            System.err.println(err.getMessage());
        } finally {
            this.frame.dispose();
        }
    }
}

public class ModifyFrame<T> extends JFrame {
    private long timeStamp;
    private T ref;

    private NewLabel titleDate = new NewLabel("h4",
            Context.year + "年 " + Context.month + "月 " + Context.solarDate + "日");

    private NewInput inputContent = new NewInput("内容");

    // 设置重复状态控件
    private JCheckBox checkRepeat = new JCheckBox("是否重复", false);

    // 设置重复类型控件
    private NewLabel selectRepeatTypeText = new NewLabel("选择重复类型");
    private JComboBox<String> selectRepeatTypeBox = new JComboBox<String>();
    private JPanel panSelectRepeatType = new JPanel();

    private NewInput inputDuration = new NewInput("重复间隔(天)");

    private NewLabel selectOrderText = new NewLabel("优先级");
    private JComboBox<String> selectOrderBox = new JComboBox<String>();
    private JPanel panSelectOrder = new JPanel();

    // 提交按钮控件
    private JButton submitBtn = new JButton("修改");
    private JButton deleteBtn = new JButton("删除");
    private JPanel panButtons = new JPanel();

    public String getContent() {
        return this.inputContent.getContent();
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public boolean getIsRepeat() {
        return this.checkRepeat.isSelected();
    }

    public short getRepeatType() {
        try {
            return (short) (this.selectRepeatTypeBox.getSelectedIndex() + 1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1; // 转换失败
        }
    }

    public short getOrder() {
        return (short) this.selectOrderBox.getSelectedIndex();
    }

    public int getDuration() {
        try {
            return Integer.valueOf(this.inputDuration.getContent());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    public T getRef() {
        return ref;
    }

    public ModifyFrame(T ref) {
        this.ref = ref;
        this.setTitle("修改日程");
        this.setSize(300, 300);
        this.setLayout(new GridLayout(7, 1, 5, 5));
        StaticEvent.centerWindow(this);

        // 渲染当前选中日期
        this.titleDate.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(titleDate);
        // 渲染日程内容输入框
        this.inputContent.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(inputContent);
        // 渲染优先级选择套件
        this.selectOrderBox.addItem("无优先级");
        this.selectOrderBox.addItem("低优先级");
        this.selectOrderBox.addItem("中优先级");
        this.selectOrderBox.addItem("高优先级");
        this.panSelectOrder.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.panSelectOrder.add(selectOrderText);
        this.panSelectOrder.add(selectOrderBox);
        this.add(panSelectOrder);
        // 渲染设置重复状态
        this.checkRepeat.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(checkRepeat);
        // 渲染重复类型选择套件
        this.selectRepeatTypeBox.addItem("每年重复");
        this.selectRepeatTypeBox.addItem("每月重复");
        this.selectRepeatTypeBox.addItem("每星期重复");
        this.selectRepeatTypeBox.addItem("自定义重复");
        this.panSelectRepeatType.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.panSelectRepeatType.add(selectRepeatTypeText);
        this.panSelectRepeatType.add(selectRepeatTypeBox);
        this.add(panSelectRepeatType);
        // 渲染日程重复间隔
        this.inputDuration.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(inputDuration);
        // 渲染提交按钮
        this.panButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.panButtons.add(deleteBtn);
        this.panButtons.add(submitBtn);
        this.add(panButtons);

        this.selectRepeatTypeBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                @SuppressWarnings("unchecked")
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                ModifyFrame<?> frame = (ModifyFrame<?>) cb.getRootPane().getParent();
                if (cb.getSelectedIndex() == 3)
                    frame.inputDuration.setAccess(true);
                else {
                    frame.inputDuration.setAccess(false);
                    frame.inputDuration.setContent("");
                }
            }
        });
        this.checkRepeat.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JCheckBox cb = (JCheckBox) e.getSource();
                ModifyFrame<?> frame = (ModifyFrame<?>) cb.getRootPane().getParent();
                frame.selectRepeatTypeBox.setEnabled(cb.isSelected());
                frame.inputDuration.setAccess(false);
                frame.inputDuration.setContent("");
            }
        });
        this.submitBtn.addActionListener(
                new HandleClickModifyBtn<T>(this));
        this.deleteBtn.addActionListener(new HandleClickDeleteBtn<T>(this));

        // TODO: 泛型！！！
        if (ref instanceof Schedule)
            initialState((Schedule) ref);
        else
            initialState((CommemorationDay) ref);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void initialState(Schedule ref) {
        this.inputContent.setContent(ref.getContent());
        if (ref.getRepeatCode() != 0) {
            this.checkRepeat.setSelected(true);
            this.selectRepeatTypeBox.setSelectedIndex(ref.getRepeatCode() - 1);
        } else
            this.selectRepeatTypeBox.setEnabled(false);
        this.selectOrderBox.setSelectedIndex(ref.getOrder());
        if (ref.getRepeatDuration() != 0)
            this.inputDuration.setContent(String.valueOf(ref.getRepeatDuration()));
    }

    public void initialState(CommemorationDay ref) {
        System.out.println(ref.getClass());
        this.inputContent.setContent(ref.getContent());
    }
}
