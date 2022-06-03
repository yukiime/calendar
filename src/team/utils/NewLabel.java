package team.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NewLabel extends JLabel {
    public NewLabel() {
        setStyle("text");
    }

    public NewLabel(String content) {
        setText(content);
        setStyle("text");
    }

    public NewLabel(String type, String content) {
        setStyle(type);
        this.setText(content);
    }

    public void setContent(String type, String content) {
        setStyle(type);
        this.setText(content);
    }

    public void setStyle(String type) {

        setHorizontalAlignment(SwingConstants.LEFT);
        String[] props = type.split(" ");
        switch (props[0]) {
            case "h1":
                setFont(new Font("黑体", Font.BOLD, 38));
                break;
            case "h2":
                setFont(new Font("黑体", Font.BOLD, 30));
                break;
            case "h3":
                setFont(new Font("黑体", Font.BOLD, 24));
                break;
            case "h4":
                setFont(new Font("黑体", Font.BOLD, 20));
                break;
            case "text":
                setFont(new Font("黑体", Font.PLAIN, 14));
                break;
            default:
                setFont(new Font("黑体", Font.PLAIN, 14));
        }
        if (props.length == 2)
            switch (props[1]) {
                case "weak":
                    setForeground(Color.lightGray);
                    break;
                case "danger":
                    setForeground(Color.RED);
                    break;
                default:
            }
    }

}
