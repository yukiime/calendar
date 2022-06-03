package team.utils;

import javax.swing.JOptionPane;

public class Alert {
    public static void show(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static void warn(String msg) {
        JOptionPane.showMessageDialog(null, msg);
        System.err.println(msg);
    }

}
