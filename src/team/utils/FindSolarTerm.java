package team.utils;

import team.Item.ItemSchedule.SolarTerm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 两个函数
 * 1.获取当日的时间，返回当日的节气，若无则返回空
 * 2.获取当年的时间,寻找到当年所有的节气并返回
 */
public class FindSolarTerm {
    private static final double D = 0.2422;
    private final static Map<String, Integer[]> INCREASE_OFFSETMAP = new HashMap<String, Integer[]>();// +1偏移
    private final static Map<String, Integer[]> DECREASE_OFFSETMAP = new HashMap<String, Integer[]>();// -1偏移

    /** 24节气 **/
    private static enum SolarTermsEnum {
        LICHUN, // 立春
        YUSHUI, // 雨水
        JINGZHE, // 惊蛰
        CHUNFEN, // 春分
        QINGMING, // 清明
        GUYU, // 谷雨
        LIXIA, // 立夏
        XIAOMAN, // 小满
        MANGZHONG, // 芒种
        XIAZHI, // 夏至
        XIAOSHU, // 小暑
        DASHU, // 大暑
        LIQIU, // 立秋
        CHUSHU, // 处暑
        BAILU, // 白露
        QIUFEN, // 秋分
        HANLU, // 寒露
        SHUANGJIANG, // 霜降
        LIDONG, // 立冬
        XIAOXUE, // 小雪
        DAXUE, // 大雪
        DONGZHI, // 冬至
        XIAOHAN, // 小寒
        DAHAN;// 大寒
    }

    static {
        DECREASE_OFFSETMAP.put(SolarTermsEnum.YUSHUI.name(), new Integer[] { 2026 });// 雨水
        INCREASE_OFFSETMAP.put(SolarTermsEnum.CHUNFEN.name(), new Integer[] { 2084 });// 春分
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOMAN.name(), new Integer[] { 2008 });// 小满
        INCREASE_OFFSETMAP.put(SolarTermsEnum.MANGZHONG.name(), new Integer[] { 1902 });// 芒种
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAZHI.name(), new Integer[] { 1928 });// 夏至
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOSHU.name(), new Integer[] { 1925, 2016 });// 小暑
        INCREASE_OFFSETMAP.put(SolarTermsEnum.DASHU.name(), new Integer[] { 1922 });// 大暑
        INCREASE_OFFSETMAP.put(SolarTermsEnum.LIQIU.name(), new Integer[] { 2002 });// 立秋
        INCREASE_OFFSETMAP.put(SolarTermsEnum.BAILU.name(), new Integer[] { 1927 });// 白露
        INCREASE_OFFSETMAP.put(SolarTermsEnum.QIUFEN.name(), new Integer[] { 1942 });// 秋分
        INCREASE_OFFSETMAP.put(SolarTermsEnum.SHUANGJIANG.name(), new Integer[] { 2089 });// 霜降
        INCREASE_OFFSETMAP.put(SolarTermsEnum.LIDONG.name(), new Integer[] { 2089 });// 立冬
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOXUE.name(), new Integer[] { 1978 });// 小雪
        INCREASE_OFFSETMAP.put(SolarTermsEnum.DAXUE.name(), new Integer[] { 1954 });// 大雪
        DECREASE_OFFSETMAP.put(SolarTermsEnum.DONGZHI.name(), new Integer[] { 1918, 2021 });// 冬至

        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOHAN.name(), new Integer[] { 1982 });// 小寒
        DECREASE_OFFSETMAP.put(SolarTermsEnum.XIAOHAN.name(), new Integer[] { 2019 });// 小寒

        INCREASE_OFFSETMAP.put(SolarTermsEnum.DAHAN.name(), new Integer[] { 2082 });// 大寒
    }
    // 定义一个二维数组，第一维数组存储的是20世纪的节气C值，第二维数组存储的是21世纪的节气C值,0到23个，依次代表立春、雨水...大寒节气的C值
    private static final double[][] CENTURY_ARRAY = {
            { 4.6295, 19.4599, 6.3826, 21.4155, 5.59, 20.888, 6.318, 21.86, 6.5, 22.2, 7.928, 23.65, 8.35,
                    23.95, 8.44, 23.822, 9.098, 24.218, 8.218, 23.08, 7.9, 22.6, 6.11, 20.84 },
            { 3.87, 18.73, 5.63, 20.646, 4.81, 20.1, 5.52, 21.04, 5.678, 21.37, 7.108, 22.83,
                    7.5, 23.13, 7.646, 23.042, 8.318, 23.438, 7.438, 22.36, 7.18, 21.94, 5.4055, 20.12 } };

    /**
     *
     * @param year 年份
     * @param name 节气的名称
     * @return 返回节气是相应月份的第几天
     */
    public static int getSolarTermNum(int year, String name) {

        double centuryValue = 0;// 节气的世纪值，每个节气的每个世纪值都不同
        name = name.trim().toUpperCase();
        int ordinal = SolarTermsEnum.valueOf(name).ordinal();

        int centuryIndex = -1;
        try {
            if (year >= 1901 && year <= 2000) {// 20世纪
                centuryIndex = 0;
            } else if (year >= 2001 && year <= 2100) {// 21世纪
                centuryIndex = 1;
            } else {
                throw new Exception("不支持此年份：" + year + "，目前只支持1901年到2100年的时间范围");
            }
        } catch (Exception err) {
            Alert.warn(err.getMessage());
        }
        centuryValue = CENTURY_ARRAY[centuryIndex][ordinal];
        int dateNum = 0;
        /**
         * 计算 num =[Y*D+C]-L这是传说中的寿星通用公式
         * 公式解读：年数的后2位乘0.2422加C(即：centuryValue)取整数后，减闰年数
         */
        int y = year % 100;// 步骤1:取年分的后两位数
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {// 闰年
            if (ordinal == SolarTermsEnum.XIAOHAN.ordinal() || ordinal == SolarTermsEnum.DAHAN.ordinal()
                    || ordinal == SolarTermsEnum.LICHUN.ordinal() || ordinal == SolarTermsEnum.YUSHUI.ordinal()) {
                // 注意：凡闰年3月1日前闰年数要减一，即：L=[(Y-1)/4],因为小寒、大寒、立春、雨水这两个节气都小于3月1日,所以 y = y-1
                y = y - 1;// 步骤2
            }
        }
        dateNum = (int) (y * D + centuryValue) - (int) (y / 4);// 步骤3，使用公式[Y*D+C]-L计算
        dateNum += specialYearOffset(year, name);// 步骤4，加上特殊的年分的节气偏移量
        return dateNum;
    }

    /**
     * 特例,特殊的年分的节气偏移量,由于公式并不完善，所以算出的个别节气的第几天数并不准确，在此返回其偏移量
     *
     * @param year 年份
     * @param name 节气名称
     * @return 返回其偏移量
     */
    public static int specialYearOffset(int year, String name) {
        int offset = 0;
        offset += getOffset(DECREASE_OFFSETMAP, year, name, -1);
        offset += getOffset(INCREASE_OFFSETMAP, year, name, 1);

        return offset;
    }

    public static int getOffset(Map<String, Integer[]> map, int year, String name, int offset) {
        int off = 0;
        Integer[] years = map.get(name);
        if (null != years) {
            for (int i : years) {
                if (i == year) {
                    off = offset;
                    break;
                }
            }
        }
        return off;
    }

    public static String daySolarTerm(int year, int month, int day) {

        ArrayList<SolarTerm> arrayList = yearSolarTerm(year); // 存储了当年所有节气的list

        for (SolarTerm solarTerm : arrayList) {
            if (solarTerm.getYear() == year && solarTerm.getMonth() == month && solarTerm.getDay() == day) {
                return solarTerm.getContent();
            }
        }

        return null;
    }

    /**
     * 获取当年的时间,寻找到当年所有的节气并返回
     *
     * @param year 年份
     * @return arrayList 存储了当年所有节气的list
     */
    public static ArrayList<SolarTerm> yearSolarTerm(int year)// 将ArrayList<SolarTerm>改成了SolarTerm[]
    {

        ArrayList<SolarTerm> arrayList = new ArrayList<>();
        // 创建一个SolarTerm类型的数组，用来暂时存放当年的全部节气数据
        SolarTerm[] solarterm = new SolarTerm[24];

        /**
         * 寻找到这一年的节气
         * 添加到solarterm里面
         */
        solarterm[0] = new SolarTerm(year, 2, getSolarTermNum(year, SolarTermsEnum.LICHUN.name()), "立春");
        solarterm[1] = new SolarTerm(year, 2, getSolarTermNum(year, SolarTermsEnum.YUSHUI.name()), "雨水");
        solarterm[2] = new SolarTerm(year, 3, getSolarTermNum(year, SolarTermsEnum.JINGZHE.name()), "惊蛰");
        solarterm[3] = new SolarTerm(year, 3, getSolarTermNum(year, SolarTermsEnum.CHUNFEN.name()), "春分");
        solarterm[4] = new SolarTerm(year, 4, getSolarTermNum(year, SolarTermsEnum.QINGMING.name()), "清明");
        solarterm[5] = new SolarTerm(year, 4, getSolarTermNum(year, SolarTermsEnum.GUYU.name()), "谷雨");
        solarterm[6] = new SolarTerm(year, 5, getSolarTermNum(year, SolarTermsEnum.LIXIA.name()), "立夏");
        solarterm[7] = new SolarTerm(year, 5, getSolarTermNum(year, SolarTermsEnum.XIAOMAN.name()), "小满");
        solarterm[8] = new SolarTerm(year, 6, getSolarTermNum(year, SolarTermsEnum.MANGZHONG.name()), "芒种");
        solarterm[9] = new SolarTerm(year, 6, getSolarTermNum(year, SolarTermsEnum.XIAZHI.name()), "夏至");
        solarterm[10] = new SolarTerm(year, 7, getSolarTermNum(year, SolarTermsEnum.XIAOSHU.name()), "小暑");
        solarterm[11] = new SolarTerm(year, 7, getSolarTermNum(year, SolarTermsEnum.DASHU.name()), "大暑");
        solarterm[12] = new SolarTerm(year, 8, getSolarTermNum(year, SolarTermsEnum.LIQIU.name()), "立秋");
        solarterm[13] = new SolarTerm(year, 8, getSolarTermNum(year, SolarTermsEnum.CHUSHU.name()), "处暑");
        solarterm[14] = new SolarTerm(year, 9, getSolarTermNum(year, SolarTermsEnum.BAILU.name()), "白露");
        solarterm[15] = new SolarTerm(year, 9, getSolarTermNum(year, SolarTermsEnum.QIUFEN.name()), "秋分");
        solarterm[16] = new SolarTerm(year, 10, getSolarTermNum(year, SolarTermsEnum.HANLU.name()), "寒露");
        solarterm[17] = new SolarTerm(year, 10, getSolarTermNum(year, SolarTermsEnum.SHUANGJIANG.name()), "霜降");
        solarterm[18] = new SolarTerm(year, 11, getSolarTermNum(year, SolarTermsEnum.LIDONG.name()), "立冬");
        solarterm[19] = new SolarTerm(year, 11, getSolarTermNum(year, SolarTermsEnum.XIAOXUE.name()), "小雪");
        solarterm[20] = new SolarTerm(year, 12, getSolarTermNum(year, SolarTermsEnum.DAXUE.name()), "大雪");
        solarterm[21] = new SolarTerm(year, 12, getSolarTermNum(year, SolarTermsEnum.DONGZHI.name()), "冬至");
        solarterm[22] = new SolarTerm(year, 1, getSolarTermNum(year, SolarTermsEnum.XIAOHAN.name()), "小寒");
        solarterm[23] = new SolarTerm(year, 1, getSolarTermNum(year, SolarTermsEnum.DAHAN.name()), "大寒");
        // 将solarterm里面的数据转到arrayList里面
        for (int i = 0; i < 24; i++) {
            arrayList.add(solarterm[i]);
        }
        return arrayList;
    }
}
