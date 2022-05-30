package team.utils;

import javax.swing.*;
import java.awt.*;

public class NewInput extends JPanel {
  private NewLabel title;

  private JTextField input = new JTextField(12);

  public NewInput(String title) {
    this.title = new NewLabel("h2", title + ": ");
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    this.add(this.title);
    this.add(input);

  }

  public JTextField getInputInstance() {
    return this.input;
  }

  public String getContent() {
    System.out.println(this.input.getText());
    return this.input.getText();
  }
}
