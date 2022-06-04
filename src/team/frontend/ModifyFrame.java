package team.frontend;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;

import team.frontend.components.NewInput;
import team.frontend.components.NewLabel;
import team.frontend.components.Sider;
import team.item.controller.CreateSth;
import team.item.controller.CreateSthRepeat;
import team.item.controller.DeleteSth;
import team.item.schedule.CommemorationDay;
import team.item.schedule.Schedule;
import team.safe.ValueException;
import team.utils.Alert;
import team.utils.DateCalculator;
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

    if (ref instanceof Schedule) {
      long timeStamp = ((Schedule) ref).getCreateTime();
      try {
        DeleteSth.deleteSchedule((Schedule) ref);
        if (order == 5)
          CreateSth.createCommemorationDay(timeStamp, content);
        else if (repeatType == 4)
          CreateSthRepeat.createSchedule(timeStamp, content, isRepeat, duration,
              repeatType, order);
        else
          CreateSthRepeat.createSchedule(timeStamp, content, isRepeat, repeatType,
              order);

        Sider.itemList
            .renderList(DateCalculator.get0clockTimeStamp(Context.year, Context.month, Context.solarDate));
      } catch (Exception err) {
        Alert.warn(err.getMessage());
      }
    } else if (ref instanceof CommemorationDay) {
      long timeStamp = ((CommemorationDay) ref).getCreateTime();
      try {
        DeleteSth.deleteCommemorationDays_festival(((CommemorationDay) frame.getRef()).getId());
        CreateSth.createCommemorationDay(timeStamp, content);
        Sider.itemList
            .renderList(DateCalculator.get0clockTimeStamp(Context.year, Context.month, Context.solarDate));

      } catch (Exception err) {
        Alert.warn(err.getMessage());
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
        Alert.warn(err.getMessage());
      }
    try {
      Sider.itemList
          .renderList(DateCalculator.get0clockTimeStamp(Context.year, Context.month, Context.solarDate));
    } catch (ValueException err) {
      Alert.warn(err.getMessage());
    } finally {
      this.frame.dispose();
    }
  }
}

public class ModifyFrame<T> extends JFrame {
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

  public boolean getIsRepeat() {
    return this.checkRepeat.isSelected();
  }

  public short getRepeatType() {
    try {
      return (short) (this.selectRepeatTypeBox.getSelectedIndex() + 1);
    } catch (Exception e) {
      Alert.warn(e.getMessage());
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
      return 0;
    }
  }

  public T getRef() {
    return ref;
  }

  public ModifyFrame(T ref) {
    this.ref = ref;
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
    this.selectOrderBox.addItem("纪念日");
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
    this.selectOrderBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        @SuppressWarnings("unchecked")
        JComboBox<String> cb = (JComboBox<String>) e.getSource();
        ModifyFrame<?> frame = (ModifyFrame<?>) cb.getRootPane().getParent();
        if (cb.getSelectedIndex() == 4) { // 纪念日
          frame.checkRepeat.setSelected(true);
          frame.checkRepeat.setEnabled(false);
          frame.selectRepeatTypeBox.setSelectedIndex(0);
          frame.selectRepeatTypeBox.setEnabled(false);
        } else {
          frame.checkRepeat.setEnabled(true);
          frame.checkRepeat.setSelected(false);
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
    if (ref instanceof Schedule) {
      this.setTitle("修改日程");
      initialState((Schedule) ref);
    } else if (ref instanceof CommemorationDay) {
      this.setTitle("修改纪念日");
      this.titleDate.setContent("h4", "每年 " + Context.month + "月 " + Context.solarDate + "日");
      initialState((CommemorationDay) ref);
    }

    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  public void initialState(Schedule ref) {
    this.inputContent.setContent(ref.getContent());
    if (ref.getRepeatCode() != 0) {
      this.checkRepeat.setSelected(true);
      this.selectRepeatTypeBox.setSelectedIndex(ref.getRepeatCode() - 1);
    } else {
      this.checkRepeat.setSelected(false);
      this.selectRepeatTypeBox.setEnabled(false);
      this.inputDuration.setAccess(false);
    }
    this.selectOrderBox.setSelectedIndex(ref.getOrder());
    if (ref.getRepeatDuration() != 0)
      this.inputDuration.setContent(String.valueOf(ref.getRepeatDuration()));
  }

  public void initialState(CommemorationDay ref) {
    this.inputContent.setContent(ref.getContent());
    this.selectOrderBox.setSelectedIndex(4);
    this.selectOrderBox.setEnabled(false);
    this.checkRepeat.setSelected(true);
    this.checkRepeat.setEnabled(false);
    this.inputContent.setContent(ref.getContent());
  }
}
