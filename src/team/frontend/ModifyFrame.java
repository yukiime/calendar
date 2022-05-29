package team.frontend;

import javax.swing.JFrame;
import java.awt.*;

import team.utils.NewLabel;
import team.utils.StaticEvent;

public class ModifyFrame extends JFrame {

  public ModifyFrame() {
    this.add(new NewLabel("h1", "修改日程"));
    this.setSize(300, 300);
    this.setLayout(new FlowLayout());
    this.setVisible(true);
    StaticEvent.centerWindow(this);
  }
}