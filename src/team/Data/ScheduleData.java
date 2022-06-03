package team.Data;

import team.Item.ItemSchedule.Schedule;
import java.util.ArrayList;

public class ScheduleData {
    public static final short[] WEEK = { 7, 1, 2, 3, 4, 5, 6 };

    // len长度的维护模块
    // 开始

    // 存储至今为止总共创建了多少个日程,并作为日程的身份识别的唯一id
    private static int len = 0;

    // 用于获取len
    public static int getLen() {
        return len;
    }

    // 用于更新len
    public static void addLenOne() {
        ScheduleData.len += 1;
    }

    // 用于本地读取时设置len
    public static void setLen(int len) {
        ScheduleData.len = len;
    }

    // len长度的维护模块
    // 结束

    // 无重复日程队列的调用函数
    // 开始

    // 创建无重复的日程的ArrayList
    private static ArrayList<Schedule> scheduleArrayNotRepeat = new ArrayList<Schedule>();

    // 用于获取无重复的日程的ArrayList
    public static ArrayList<Schedule> getScheduleArrayNotRepeat() {
        return scheduleArrayNotRepeat;
    }

    // 返回scheduleArrayNotRepeat的index号元素
    public static Schedule getIndexSchedule(int index) {
        return scheduleArrayNotRepeat.get(index);
    }

    // 将newSchedule插入到scheduleArrayNotRepeat的index位置
    public static void addIndexScheduleArrayNotRepeat(int index, Schedule newSchedule) {
        ScheduleData.scheduleArrayNotRepeat.add(index, newSchedule);
    }

    // scheduleArrayNotRepeat更新为从本地文件更新的arrayList
    public static void updateScheduleArrayNotRepeat(ArrayList<Schedule> arrayList) {
        scheduleArrayNotRepeat.clear();
        scheduleArrayNotRepeat = arrayList;
    }

    // 无重复日程队列的调用函数
    // 结束

    // 重复日程队列的调用函数
    // 开始

    // 创建重复的日程的ArrayList
    private static ArrayList<Schedule> scheduleArrayRepeat = new ArrayList<Schedule>();

    // 用于获取无重复的日程的ArrayList
    public static ArrayList<Schedule> getScheduleArrayRepeat() {
        return scheduleArrayRepeat;
    }

    // 返回scheduleArrayRepeat的index号元素
    public static Schedule getIndexRepeatSchedule(int index) {
        return scheduleArrayRepeat.get(index);
    }

    // 将newSchedule插入到scheduleArrayRepeat的index位置
    public static void addIndexScheduleArrayRepeat(int index, Schedule newSchedule) {
        ScheduleData.scheduleArrayRepeat.add(index, newSchedule);
    }

    // scheduleArrayRepeat更新为从本地文件更新的arrayList
    public static void updateScheduleArrayRepeat(ArrayList<Schedule> arrayList) {
        scheduleArrayRepeat.clear();
        scheduleArrayRepeat = arrayList;
    }

    // 重复日程队列的调用函数
    // 结束

}
