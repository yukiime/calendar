package team.frontend;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import team.frontend.components.NewInput;
import team.frontend.components.NewLabel;
import team.frontend.components.Sider;
import team.item.controller.CreateSth;
import team.item.controller.CreateSthRepeat;
import team.safe.EmptyContentException;
import team.utils.*;

class HandleClickSubmitBtn implements ActionListener {
  private CreateScheduleFrame frame;

  /**
   *
   * @param frame 窗口的引用，用于关闭窗口
   */
  HandleClickSubmitBtn(CreateScheduleFrame frame) {
    this.frame = frame;
  }

  public void actionPerformed(ActionEvent e) {
    String content = this.frame.getContent();
    long timeStamp = this.frame.getTimeStamp();
    boolean isRepeat = this.frame.getIsRepeat();
    short repeatType = this.frame.getRepeatType();
    short order = this.frame.getOrder();
    int duration = this.frame.getDuration();

    try {
      if (content.length() == 0) {
        throw new EmptyContentException();
      }
      if (order == 4)
        CreateSth.createCommemorationDay(timeStamp, content);
      else if (repeatType == 4)
        CreateSthRepeat.createSchedule(timeStamp, content, isRepeat, duration, repeatType, order);
      else
        CreateSthRepeat.createSchedule(timeStamp, content, isRepeat, repeatType, order);

      Sider.itemList.renderList(timeStamp);
      this.frame.dispose();
    } catch (EmptyContentException err) {
      Alert.warn(err.getMessage());
    } catch (Exception err) {
      Alert.warn(err.getMessage());
    }
  }
}

public class CreateScheduleFrame extends JFrame {
  private long timeStamp;

  private NewLabel titleDate = new NewLabel("h4",
      Context.year + "年 " + Context.month + "月 " + Context.solarDate + "日");
  // 日程内容控件
  private NewInput inputContent = new NewInput("输入内容");

  // 设置重复状态控件
  private JCheckBox checkRepeat = new JCheckBox("是否重复", false);

  // 设置重复类型控件
  private NewLabel selectRepeatTypeText = new NewLabel("选择重复类型");
  private JComboBox<String> selectRepeatTypeBox = new JComboBox<String>();
  private JPanel panSelectRepeatType = new JPanel();

  private NewInput inputDuration = new NewInput("输入重复间隔(天)");

  private NewLabel selectOrderText = new NewLabel("输入优先级");
  private JComboBox<String> selectOrderBox = new JComboBox<String>();
  private JPanel panSelectOrder = new JPanel();

  // 提交按钮控件
  private JButton submitBtn = new JButton("提交");
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

  public CreateScheduleFrame() {
    timeStamp = DateCalculator.get0clockTimeStamp(Context.year, Context.month, Context.solarDate);

    // 窗口相关属性设置
    this.setTitle("新建日程");
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
    this.selectRepeatTypeBox.setEnabled(false);
    this.panSelectRepeatType.add(selectRepeatTypeText);
    this.panSelectRepeatType.add(selectRepeatTypeBox);
    this.add(panSelectRepeatType);
    // 渲染日程重复间隔
    this.inputDuration.setLayout(new FlowLayout(FlowLayout.LEFT));
    this.inputDuration.setAccess(false);
    this.add(inputDuration);
    // 渲染提交按钮
    this.panButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
    this.panButtons.add(submitBtn);
    this.add(panButtons);

    // 监听事件绑定
    this.selectRepeatTypeBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        @SuppressWarnings("unchecked")
        JComboBox<String> cb = (JComboBox<String>) e.getSource();
        CreateScheduleFrame frame = (CreateScheduleFrame) cb.getRootPane().getParent();
        if (cb.getSelectedIndex() == 3)
          frame.inputDuration.setAccess(true);
        else {
          frame.inputDuration.setAccess(false);
          frame.inputDuration.setContent("");
        }
        if (cb.getSelectedIndex() == 4)
          frame.selectOrderBox.setEnabled(false);
      }
    });
    this.selectOrderBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        @SuppressWarnings("unchecked")
        JComboBox<String> cb = (JComboBox<String>) e.getSource();
        CreateScheduleFrame frame = (CreateScheduleFrame) cb.getRootPane().getParent();
        if (cb.getSelectedIndex() == 4) {
          frame.checkRepeat.setSelected(true);
          frame.checkRepeat.setEnabled(false);
          frame.selectRepeatTypeBox.setSelectedIndex(0);
          frame.selectRepeatTypeBox.setEnabled(false);
        } else {
          frame.checkRepeat.setEnabled(true);
          frame.selectRepeatTypeBox.setEnabled(true);
        }
      }
    });
    this.checkRepeat.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        JCheckBox cb = (JCheckBox) e.getSource();
        CreateScheduleFrame frame = (CreateScheduleFrame) cb.getRootPane().getParent();
        frame.selectRepeatTypeBox.setEnabled(cb.isSelected());
        frame.inputDuration.setAccess(false);
        frame.inputDuration.setContent("");
      }
    });
    this.submitBtn.addActionListener(
        new HandleClickSubmitBtn(this));

    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

}
