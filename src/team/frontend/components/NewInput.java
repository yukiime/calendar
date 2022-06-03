package team.frontend.components;

import javax.swing.*;

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

    public void setAccess(boolean state) {
        this.input.setEnabled(state);
    }

}
