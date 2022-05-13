package team.frontend;

import javax.swing.*;
import java.awt.event.*;

class HandleClickQueryDate implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    QueryFrame qfrm = new QueryFrame();
  }
}

class FgMenu extends JMenu {

  private JMenuItem mQuery = new JMenuItem("Query Date");

  FgMenu(String title) {
    super(title);
    this.add(mQuery);
    mQuery.addActionListener(new HandleClickQueryDate());
  }
}

public class MenuBar extends JMenuBar {
  static FgMenu mTools = new FgMenu("Tools");

  public MenuBar() {
    this.add(mTools);
  }
}
