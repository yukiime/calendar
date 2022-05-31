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

  public void setContent(String type, String content) {
    setStyle(type);
    this.setText(content);
  }

  public void setStyle(String type) {
    if (type.equals("h1"))
      setFont(new Font("黑体", Font.BOLD, 38));
    else if (type.equals("h2"))
      setFont(new Font("黑体", Font.BOLD, 30));
    else if (type.equals("h3"))
      setFont(new Font("黑体", Font.BOLD, 24));
    else if (type.equals("h4"))
      setFont(new Font("黑体", Font.BOLD, 20));
    else if (type.equals("text"))
      setFont(new Font("黑体", Font.PLAIN, 14));
    else
      setFont(new Font("黑体", Font.PLAIN, 14));
  }

}
