package team.frontend;

import java.awt.*;
import java.util.Calendar;

public class Context {
    public static Calendar date = Calendar.getInstance();
    public static int year = 2022;
    public static int month = 6;
    public static int solarDate = 5;
    public static int MILLISECOND_DAY = 86400000;
    // TODO: initialize data
    // TODO: improve visiblity

    public static Color[] goldColors = {
            new Color(255, 251, 230),
            new Color(255, 241, 184),
            new Color(255, 229, 143),
            new Color(255, 214, 102),
            new Color(255, 197, 61),
            new Color(250, 173, 20),
    };
    public static Color[] orderColors = {
            new Color(245, 245, 245), // none
            new Color(186, 231, 255), // low
            new Color(255, 255, 184), // mid
            new Color(255, 216, 191), // high
            new Color(255, 163, 158), // super
    };
    public static Color[] orderBorderColors = {
            new Color(217, 217, 217),
            new Color(64, 169, 255),
            new Color(255, 236, 61),
            new Color(255, 122, 69),
            new Color(255, 77, 79),
    };

    public static int selectedNum = 0; // 选中第几个格子，与日期无关

    public static enum DayOfWeekChar {
        星期日, 星期一, 星期二, 星期三,
        星期四, 星期五, 星期六,
    }

    public static enum MonthChar {
        正月, 二月, 三月, 四月,
        五月, 六月, 七月, 八月,
        九月, 十月, 十一月, 十二月,
    }

    public static enum LunarChar {
        初一, 初二, 初三, 初四, 初五,
        初六, 初七, 初八, 初九, 初十,
        十一, 十二, 十三, 十四, 十五,
        十六, 十七, 十八, 十九, 廿十,
        廿一, 廿二, 廿三, 廿四, 廿五,
        廿六, 廿七, 廿八, 廿九, 三十,
    }

    public static enum SolarChar {
        一, 二, 三, 四, 五,
        六, 七, 八, 九, 十,
        十一, 十二, 十三, 十四, 十五,
        十六, 十七, 十八, 十九, 二十,
        二十一, 二十二, 二十三, 二十四, 二十五,
        二十六, 二十七, 二十八, 二十九, 三十,
        三十一
    }

    public static void toggleFullDateInContext(int year, int month, int solarDate) {
        Context.year = year == -1 ? Context.year : year;
        Context.month = month == -1 ? Context.month : month;
        Context.solarDate = solarDate;
        date.set(Context.year, Context.month - 1, solarDate);
        EntranceFrame.cg.renderBox(date);
    }
}
