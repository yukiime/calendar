package team.frontend.components;

import javax.swing.*;

import team.Data.ItemsWriter;
import team.frontend.QueryFrame;

import java.awt.event.*;

class HandleClickQueryDate implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        QueryFrame qfrm = new QueryFrame();
    }
}

//存储，调用序列化方法
class SaveDate implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        ItemsWriter.writerAllItems();
    }
}

class FgMenu extends JMenu {

    private JMenuItem mQuery = new JMenuItem("Query Date");
    private JMenuItem saveItems = new JMenuItem("Save"); //存储按钮

    FgMenu(String title) {
        super(title);
        this.add(mQuery);
        mQuery.addActionListener(new HandleClickQueryDate());
        this.add(saveItems);
        saveItems.addActionListener(new SaveDate());
    }
}

public class TopMenu extends JMenuBar {
    static FgMenu mTools = new FgMenu("Tools");

    public TopMenu() {
        this.add(mTools);
    }
}
