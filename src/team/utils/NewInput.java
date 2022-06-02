package team.utils;

import javax.swing.*;
import java.awt.*;

public class NewInput extends JPanel {
    private NewLabel title;

    private JTextField input = new JTextField(12);

    public NewInput(String title) {
        this.title = new NewLabel(title + ": ");
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(this.title);
        this.add(input);

    }

    public JTextField getInputInstance() {
        return this.input;
    }

    public String getContent() {
        return this.input.getText();
    }

    public void setContent(String content) {
        this.input.setText(content);
    }

    public void setAccess(boolean f) {
        this.input.setEnabled(f);
    }

}
