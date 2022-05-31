package team.frontend;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import team.Item.ItemsWork.CreateSthRepeat;
import team.Item.ItemsWork.FindDaySth;
import team.frontend.components.Sider;
import team.utils.*;

class HandleClickSubmitBtn implements ActionListener {
  private CreateScheduleFrame frame;

  /**
   * 
   * @param frame 窗口的引用，用于关闭窗口
   */
  HandleClickSubmitBtn(CreateScheduleFrame frame) {
    this.frame = frame;
    // System.out.println(this.frame.getA());
  }

  public void actionPerformed(ActionEvent e) {
    String content = this.frame.getContent();
    long timeStamp = this.frame.getTimeStamp();
    boolean isRepeat = this.frame.getIsRepeat();
    short repeatType = this.frame.getRepeatType();
    int duration = this.frame.getDuration();

    try {
      // TODO: 根据重复类型使用对应的函数
      CreateSthRepeat.createSchedule(timeStamp, content, isRepeat, repeatType);
      System.out.println(FindDaySth.findAllSchedule(timeStamp).size());
      Sider.scheduleList.renderScheduleList(timeStamp);
    } catch (Exception err) {
      System.err.println(err.getMessage());
    } finally {
      this.frame.dispose();
    }
  }
}

public class CreateScheduleFrame extends JFrame {
  private long timeStamp;

  // 日程内容控件
  private NewInput inputContent = new NewInput("输入内容");

  // 设置重复状态控件
  private JCheckBox checkRepeat = new JCheckBox("是否重复", false);

  // 设置重复类型控件
  private NewInput inputRepeatType = new NewInput("输入重复类型");

  private NewInput inputDuration = new NewInput("输入重复天数");

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
      return Short.valueOf(this.inputRepeatType.getContent());
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return -1; // 转换失败
    }
  }

  public int getDuration() {
    try {
      return Integer.valueOf(this.inputDuration.getContent());
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return -1;
    }
  }

  public CreateScheduleFrame() {
    timeStamp = DateCalculator.get0clockTimeStamp(Context.year, Context.month, Context.solarDate);

    // 窗口相关属性设置
    this.setTitle("新建日程");
    this.setSize(300, 250);
    this.setLayout(new GridLayout(7, 1, 5, 5));
    StaticEvent.centerWindow(this);

    // 渲染当前选中日期
    this.add(new NewLabel("h4", Context.year + "年 " + Context.month + "月 " + Context.solarDate + "日"));
    // 渲染日程内容输入框
    this.add(inputContent);
    // 渲染设置重复状态
    this.add(checkRepeat);
    // 渲染输入重复类型套件
    this.add(inputRepeatType);
    this.add(new NewLabel("提示：输入值为1..."));
    // TODO: repeat prompt text
    // 渲染日程重复间隔
    this.add(inputDuration);
    // 渲染提交按钮
    this.panButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
    this.panButtons.add(submitBtn);
    this.add(panButtons);

    // 监听事件绑定
    this.submitBtn.addActionListener(
        new HandleClickSubmitBtn(this));

    this.setVisible(true);
  }

}
