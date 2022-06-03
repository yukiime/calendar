package team.utils;

import javax.swing.*;
import java.awt.*;

public class StaticEvent {

    public static void centerWindow(JFrame frame) {
        Toolkit tk = frame.getToolkit();
        Dimension dm = tk.getScreenSize();
        frame.setLocation((int) (dm.getWidth() - frame.getWidth()) / 2, (int) (dm.getHeight() - frame.getHeight()) / 2);
    }
}
