package team.frontend;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.StyledEditorKit.BoldAction;

import java.awt.event.*;
import java.nio.channels.DatagramChannel;

import team.Item.ItemsWork.CreateSthRepeat;
import team.frontend.components.ScheduleList;
import team.frontend.components.Sider;
import team.utils.DateCalculator;
import team.utils.NewLabel;
import team.utils.StaticEvent;

class HandleClickSubmitBtn implements ActionListener {
  private String content;
  private long timeStamp;
  private boolean isRepeat;

  HandleClickSubmitBtn(String content, long timeStamp, boolean isRepeat) {
    this.content = content;
    this.timeStamp = timeStamp;
    this.isRepeat = isRepeat;
  }

  public void actionPerformed(ActionEvent e) {
    try {
      CreateSthRepeat.createSchedule(timeStamp, content, isRepeat, (short) 1);
      Sider.scheduleList.renderScheduleList(timeStamp);
    } catch (Exception err) {
      System.err.println(err.getMessage());
    }
    // TODO:
  }
}

public class CreateScheduleFrame extends JFrame {
  private String content;
  private long timeStamp;
  private boolean isRepeat;

  private JTextField inputContent = new JTextField();
  private JCheckBox checkRepeat = new JCheckBox();
  private JButton submitBtn = new JButton("提交");

  // Context.date

  public CreateScheduleFrame(int solarDate) {
    this.timeStamp = DateCalculator.get0clockTimeStamp(Context.year, Context.month, solarDate);

    this.add(new NewLabel("h1", "创建日程"));
    this.setSize(300, 300);
    this.setLayout(new FlowLayout());
    // this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    StaticEvent.centerWindow(this);

    // this.add(new NewInput("输入内容"));
    this.add(new NewLabel("h2", "输入内容"));
    this.add(inputContent);
    this.add(new NewLabel("h2", "是否重复"));
    this.add(checkRepeat);
    this.submitBtn.addActionListener(new HandleClickSubmitBtn(content, timeStamp, isRepeat));

    this.add(submitBtn);
    this.setVisible(true);
  }

  /*
   * public void submit() {
   * 
   * }
   */
}
