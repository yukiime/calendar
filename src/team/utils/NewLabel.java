package team.utils;

import java.awt.Font;

import javax.swing.JLabel;

public class NewLabel extends JLabel {
  public NewLabel() {
    setStyle("text");
  }

  public NewLabel(String content) {
    setText(content);
    setStyle("text");
  }

  public NewLabel(String type, String content) {
    setStyle(type);
    this.setText(content);
  }

  public void setStyle(String type) {
    if (type.equals("h1"))
      setFont(new Font("黑体", Font.BOLD, 16));
    else if (type.equals("h2"))
      setFont(new Font("黑体", Font.BOLD, 14));
    else if (type.equals("h3"))
      setFont(new Font("黑体", Font.BOLD, 12));
    else if (type.equals("text"))
      setFont(new Font("宋体", Font.PLAIN, 10));
    else
      setFont(new Font("宋体", Font.PLAIN, 10));
  }

}
