package team.frontend.components;

import java.awt.*;

import javax.swing.*;

import team.frontend.Context;
import team.utils.NewLabel;

public class DayOfWeekBar extends JPanel {
    public DayOfWeekBar() {
        this.setLayout(new GridLayout(1, 7));
        this.setBackground(new Color(255, 197, 61));
        for (int i = 0; i < 7; i++) {
            this.add(new NewLabel(Context.DayOfWeekChar.values()[i].toString()));
        }
    }
}
