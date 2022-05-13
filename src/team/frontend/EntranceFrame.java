package team.frontend;

import javax.swing.*;
import java.awt.*;

public class EntranceFrame extends JFrame {

  static MenuBar tb = new MenuBar();

  public EntranceFrame(String title) {
    super(title);
    setSize(800, 500);
    this.setJMenuBar(tb);
    centerWindow();
  }

  public void centerWindow() {
    java.awt.Toolkit tk = getToolkit();
    Dimension dm = tk.getScreenSize();
    setLocation((int) (dm.getWidth() - getWidth()) / 2, (int) (dm.getHeight() - getHeight()) / 2);
  }

  public static void main(String[] args) {
    EntranceFrame entrance = new EntranceFrame("Calendar");
    entrance.setVisible(true);
  }
}
