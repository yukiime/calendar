package team.utils;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

class ms implements MouseListener {

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    public void mouseExited(java.awt.event.MouseEvent e) {
    }

}

public class NewButton extends JPanel {

    public NewButton(String text) {
        this.add(new NewLabel(text));
        this.addMouseListener(new ms());
    }

}
