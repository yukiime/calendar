package team.lunar_solar;

public class Vehicle_Limit {
    /**
     *
     * @param year
     * @param month
     * @param day
     * @return 限行尾号
     */
    public static String computeVehicle(int year, int month, int day) {

        int w = 0;
        int y = WeekDay.compute_y(year);
        int c = WeekDay.compute_c(year);
        int m = WeekDay.compute_m(month);
        int d = WeekDay.compute_d(day);
        String vehicle = " ";
        w = y + y / 4 + c / 4 - 2 * c + 26 * (m + 1) / 10 + d - 1;
        switch (w % 7) {
            case 0:
                vehicle = "该日杭州车辆不限行";
                break;// 星期日
            case 1:
                vehicle = "该日杭州限行尾号为1和9";
                break;// 星期一
            case 2:
                vehicle = "该日杭州限行尾号为2和8";
                break;// 星期二
            case 3:
                vehicle = "该日杭州限行尾号为3和7";
                break;// 星期三
            case 4:
                vehicle = "该日杭州限行尾号为4和6";
                break;// 星期四
            case 5:
                vehicle = "该日杭州限行尾号为5和0";
                break;// 星期五
            case 6:
                vehicle = "该日杭州车辆不限行";
                break;// 星期六
            default:
                vehicle = "输入数据有误";
                break;
        }

        return vehicle;

    }

}
