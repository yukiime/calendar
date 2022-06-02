package team.frontend;

import javax.swing.*;

import team.lunar_solar.LS;
import team.utils.NewLabel;
import team.utils.StaticEvent;

import java.awt.*;
import java.awt.event.*;

class HandleParseLunar2Solar implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        QueryFrame.rp.setResult("lun2sor");
        String tmp = Lunar2SolarPane.lunarInput.getText();
        try {
            int year = Integer.parseInt(tmp.split("年")[0]);
            int month;
            boolean isLeapMonth = tmp.indexOf("闰") == -1 ? false : true;
            if (!isLeapMonth) {
                month = Context.MonthChar.valueOf((tmp.split("年")[1].split("月")[0]) + "月").ordinal() + 1;
            } else {
                month = Context.MonthChar.valueOf((tmp.split("闰")[1].split("月")[0]) + "月").ordinal() + 1;
            }
            int monthDay = Context.LunarChar.valueOf(tmp.split("月")[1]).ordinal() + 1;
            int res[] = LS.lunarToSolar(year, month, monthDay, isLeapMonth);
            QueryFrame.rp.setResult("阳历 " + res[0] + "年 " + res[1] + "月 " + res[2] + "日");
        } catch (Exception err) {
            System.err.println("Exception: " + err.getMessage());
            QueryFrame.rp.setResult("转换失败");
        }
    }
}

class HandleParseSolar2Lunar implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        QueryFrame.rp.setResult("sor2lun");
        String tmp = Solar2LunarPane.solarInput.getText();
        try {
            int year = Integer.parseInt(tmp.split("年")[0]);
            int month = Integer.parseInt(tmp.split("年")[1].split("月")[0]);
            int monthDay = Integer.parseInt(tmp.split("年")[1].split("月")[1].split("日")[0]);

            int[] res = LS.solarToLunar(year, month, monthDay);
            QueryFrame.rp
                    .setResult(
                            "农历 " + res[0] + " 年 " + (res[3] == 1 ? "闰 " : " ")
                                    + Context.MonthChar.values()[res[1] - 1].toString()
                                    + " "
                                    + Context.LunarChar.values()[res[2] - 1]);
        } catch (Exception err) {
            System.err.println("Exception: " + err.getMessage());
            QueryFrame.rp.setResult("转换失败");
        }
    }
}

class Lunar2SolarPane extends JPanel {
    public static JTextField lunarInput = new JTextField(12);
    public static JButton btn = new JButton("转换成阳历");

    Lunar2SolarPane() {
        this.setLayout(new FlowLayout());
        this.add(new NewLabel("h4", "阴历日期："));
        this.add(lunarInput);
        btn.addActionListener(new HandleParseLunar2Solar());
        this.add(btn);
    }
}

class Solar2LunarPane extends JPanel {
    public static JTextField solarInput = new JTextField(12);
    public static JButton btn = new JButton("转换成阴历");

    Solar2LunarPane() {
        this.setLayout(new FlowLayout());
        this.add(new NewLabel("h4", "阳历日期："));
        this.add(solarInput);
        btn.addActionListener(new HandleParseSolar2Lunar());
        this.add(btn);

    }
}

class ResultPane extends JPanel {
    private NewLabel result = new NewLabel("h4", "等待输入...");

    ResultPane() {
        this.add(new NewLabel("结果："));
        this.add(result);
    }

    public void setResult(String res) {
        result.setText(res);
    }
}

public class QueryFrame extends JFrame {
    public static Solar2LunarPane s2lp = new Solar2LunarPane();
    public static Lunar2SolarPane l2sp = new Lunar2SolarPane();
    public static ResultPane rp = new ResultPane();

    public QueryFrame() {
        super("日历转换");
        setSize(400, 200);

        this.setLayout(new FlowLayout());
        this.add(s2lp);
        this.add(l2sp);
        this.add(rp);

        StaticEvent.centerWindow(this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

}
