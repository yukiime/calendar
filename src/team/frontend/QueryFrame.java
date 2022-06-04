package team.frontend;

import javax.swing.*;

import team.Projectexception.InvaildQueryException;
import team.frontend.components.NewInput;
import team.frontend.components.NewLabel;
import team.lunar_solar.LS;
import team.utils.Alert;
import team.utils.DateCalculator;
import team.utils.StaticEvent;

import java.awt.*;
import java.awt.event.*;

class HandleParseLunar2Solar implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String tmp = Lunar2SolarPanel.lunarInput.getContent();
        QueryFrame.restoreDistance();
        try {
            int year = Integer.parseInt(tmp.split("年")[0]);
            int month;
            boolean isLeapMonth = tmp.indexOf("闰") == -1 ? false : true;
            if (!isLeapMonth) {
                month = Context.MonthChar.valueOf((tmp.split("年")[1].split("月")[0]) + "月").ordinal() + 1;
            } else {
                month = Context.MonthChar.valueOf((tmp.split("闰")[1].split("月")[0]) + "月").ordinal() + 1;
            }
            int lunarDate = Context.LunarChar.valueOf(tmp.split("月")[1]).ordinal() + 1;
            int res[] = LS.lunarToSolar(year, month, lunarDate, isLeapMonth);
            QueryFrame.setDistance(DateCalculator.distanceOfToday(res[0], res[1], res[2]));
            QueryFrame.rp.setResult("阳历 " + res[0] + "年" + res[1] + "月" + res[2] + "日 "
                    + Context.DayOfWeekChar.values()[DateCalculator.dayOfWeek(res[0], res[1], res[2])]);

        } catch (Exception err) {
            Alert.warn("输入的日期不符合规范");
            QueryFrame.rp.setResult("转换失败");
        }
    }
}

class HandleParseSolar2Lunar implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String tmp = Solar2LunarPanel.solarInput.getContent();
        QueryFrame.restoreDistance();
        try {
            int year = Integer.parseInt(tmp.split("年")[0]);
            int month = Integer.parseInt(tmp.split("年")[1].split("月")[0]);
            int solarDate = Integer.parseInt(tmp.split("年")[1].split("月")[1].split("日")[0]);
            if (!DateCalculator.checkQueryVaild(year, month, solarDate))
                throw new InvaildQueryException();
            int[] res = LS.solarToLunar(year, month, solarDate);
            QueryFrame.setDistance(DateCalculator.distanceOfToday(year, month, solarDate));
            QueryFrame.rp
                    .setResult(
                            "农历 " + res[0] + "年" + (res[3] == 1 ? " 闰" : " ")
                                    + Context.MonthChar.values()[res[1] - 1].toString()
                                    + Context.LunarChar.values()[res[2] - 1] + " "
                                    + Context.DayOfWeekChar.values()[DateCalculator.dayOfWeek(year, month, solarDate)]);
        } catch (InvaildQueryException err) {
            Alert.warn(err.getMessage());
            QueryFrame.rp.setResult("转换失败");
        } catch (ArrayIndexOutOfBoundsException err) {
            Alert.warn("输入格式错误");
            QueryFrame.rp.setResult("转换失败");
        } catch (Exception err) {
            Alert.warn(err.getMessage());
            QueryFrame.rp.setResult("转换失败");
        }
    }
}

class Lunar2SolarPanel extends JPanel {
    public static JButton btn = new JButton("转换成阳历");
    public static NewInput lunarInput = new NewInput("阴历日期");

    Lunar2SolarPanel() {
        this.setLayout(new FlowLayout());
        this.add(lunarInput);
        btn.addActionListener(new HandleParseLunar2Solar());
        this.add(btn);
    }
}

class Solar2LunarPanel extends JPanel {
    public static JButton btn = new JButton("转换成阴历");
    public static NewInput solarInput = new NewInput("阳历日期");

    Solar2LunarPanel() {
        this.setLayout(new FlowLayout());
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
        result.setContent("text", res);
    }
}

public class QueryFrame extends JFrame {
    public static Solar2LunarPanel s2lp = new Solar2LunarPanel();
    public static Lunar2SolarPanel l2sp = new Lunar2SolarPanel();
    public static ResultPane rp = new ResultPane();
    public static NewLabel distance = new NewLabel();

    public QueryFrame() {
        super("日历转换");
        setSize(400, 210);

        this.setLayout(new FlowLayout());
        this.add(s2lp);
        this.add(l2sp);
        this.add(rp);
        this.add(distance);

        StaticEvent.centerWindow(this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    static void setDistance(int num) {
        if (num < 0)
            distance.setContent("text danger", "这个日子已经过去 " + -num + " 天了");
        else if (num > 0)
            distance.setContent("text", "距离这个日子还有 " + num + " 天");
        else
            distance.setContent("text", "就是今天!");
    }

    static void restoreDistance() {
        distance.setContent("text", "");
    }

}
