package team.frontend;

import java.awt.*;

public class Context {
  public static int year = 2022;
  public static int month = 4;

  public static Color[] goldColors = {
      new Color(255, 251, 230),
      new Color(255, 241, 184),
      new Color(255, 229, 143),
      new Color(255, 214, 102),
      new Color(255, 197, 61),
      new Color(250, 173, 20),
  };

  public static int selectedNum = 0;

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

}
