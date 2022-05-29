package team.frontend;

import java.awt.*;

import javax.swing.*;

import team.utils.NewLabel;
import team.utils.StaticEvent;

public class CreateScheduleFrame extends JFrame {
  private String content;
  private long timeStamp;
  private boolean isRepeat;

  private JTextField inputContent = new JTextField();
  private JCheckBox checkRepeat = new JCheckBox();

  // Context.date

  public CreateScheduleFrame() {
    this.add(new NewLabel("h1", "创建日程"));
    this.setSize(300, 300);
    this.setLayout(new FlowLayout());
    StaticEvent.centerWindow(this);

    this.add(new NewLabel("h2", "输入内容"));
    this.add(inputContent);
    this.add(new NewLabel("h2", "是否重复"));
    this.add(checkRepeat);

    this.add(new JButton("提交"));
    this.setVisible(true);
  }

  public void submit() {

  }
}
