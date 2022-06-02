package team.Item.ItemsWork;

import team.Data.ScheduleData;
import team.Item.ItemSchedule.Schedule;
import team.Projectexception.ValueException;
import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleWork {
    /**
     * 找出createTime这个时间的日期
     * 返回记录了 月 日 星期 的数组
     *
     * @param createTime 当日的时间戳
     * @return array
     */
    public static int[] findDate(long createTime) {
        int[] array = new int[3]; // 存储 月、日、星期数
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(createTime); // 设置当前时间
        array[0] = calendar.get(Calendar.MONTH) + 1; // 月份
        array[1] = calendar.get(Calendar.DATE);
        array[2] = ScheduleData.WEEK[calendar.get(Calendar.DAY_OF_WEEK) - 1]; // 星期数,7的是代表星期天

        return array;
    }

    /**
     * 并集排序
     *
     * @param arrayList 需要排序的list
     * @return order_3
     * @throws ValueException 值错误
     */
    public static ArrayList<Schedule> OrderSort(ArrayList<Schedule> arrayList) throws ValueException {

        ArrayList<Schedule> order_0 = new ArrayList<>();
        ArrayList<Schedule> order_1 = new ArrayList<>();
        ArrayList<Schedule> order_2 = new ArrayList<>();
        ArrayList<Schedule> order_3 = new ArrayList<>();

        for (Schedule schedule : arrayList) {
            switch (schedule.getOrder()) {

                case 0:
                    order_0.add(schedule);
                    break;

                case 3:
                    order_3.add(schedule);
                    break;

                case 2:
                    order_2.add(schedule);
                    break;

                case 1:
                    order_1.add(schedule);
                    break;

                default:
                    // 检查异常
                    throw new ValueException("检查到有越界的order");
            }
        }
        order_1.addAll(order_0);
        order_2.addAll(order_1);
        order_3.addAll(order_2);

        return order_3;
    }

}
