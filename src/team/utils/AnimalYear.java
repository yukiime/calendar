package team.utils;

public class AnimalYear {
    /**
     * 支持转换的最小农历年份
     */
    public static final int MIN_YEAR = 1900;

    /**
     * 支持转换的最大农历年份
     */
    public static final int MAX_YEAR = 2099;

    /**
     *
     * @param year
     * @param month
     * @param monthDay
     * @return 传回阳历year年month月monthDay日的生肖
     */

    static public String Solar_Animal(int year, int month, int monthDay) {

        int[] lunardate = LunarSolar.solarToLunar(year, month, monthDay);
        return Lunar_Animal(lunardate[0], lunardate[1], lunardate[2]);
    }

    /**
     *
     * @param year
     * @param month
     * @param monthDay
     * @return 传回阴历year年month月monthDay的生肖
     */
    static public String Lunar_Animal(int year, int month, int monthDay) {
        final String[] Animals = new String[] { "子鼠", "丑牛", "寅虎", "卯兔", "辰龙", "巳蛇", "午马", "未羊", "申猴", "酉鸡", "戌狗",
                "亥猪" };
        return Animals[(year - 4) % 12];
    }

}
