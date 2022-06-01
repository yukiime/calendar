package team.frontend;

import javax.swing.JFrame;
import java.awt.*;

import team.utils.NewLabel;
import team.utils.StaticEvent;

public class ModifyFrame<T> extends JFrame {

    public ModifyFrame(T ref) {
        System.out.println(ref.getClass());
        this.add(new NewLabel("h4", "修改日程"));
        this.setSize(300, 250);
        this.setLayout(new GridLayout(7, 1, 5, 5));

        this.setVisible(true);
        StaticEvent.centerWindow(this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
