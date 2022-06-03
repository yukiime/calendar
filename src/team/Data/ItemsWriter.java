package team.Data;

import team.Item.ItemSchedule.CommemorationDay;
import team.Item.ItemSchedule.Schedule;
import team.Item.ItemsWork.CreateSth;
import team.Projectexception.ArrayException;

import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class ItemsWriter {
    /**
     * 将所有需要存储的数据分类写入文件中
     * @return null
     */
    public static ActionListener writerAllItems() {
        try {
            // 节日/纪念日
            //writerCommemorationDays_festival();

            // 非重复类型的日程
            writerScheduleNotRepeat();
            // 重复类型的日程
            writerScheduleRepeat();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将 FestivalData.commemorationDays_festival
     * 中的 节日/纪念日 实例写入文件存储
     *
     * @throws IOException IO流异常
     */
    private static void writerCommemorationDays_festival() throws IOException {
        // 文件路径
        File file = new File("save\\commemorationDays_festival");

        // 序列化流
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));

        // 当前的节日/纪念日list
        ArrayList<CommemorationDay> commemorationDays_festivalList = FestivalData.commemorationDays_festival;

        // 获取长度,方便以后读取
        int len = commemorationDays_festivalList.size();

        // 告知实例长度
        os.write(len);

        // 将list中的实例写入文件中
        for (int i = 0; i < len; i++) {
            // 获取每个实例
            Object c = commemorationDays_festivalList.get(i);
            os.writeObject(c);
        }

        // 释放
        os.close();
    }

    /**
     * 将 ScheduleData.scheduleArrayNotRepeat
     * 中的 非重复类型的日程 实例写入文件存储
     *
     * @throws IOException IO流异常
     */
    private static void writerScheduleNotRepeat() throws IOException {
        // 文件路径
        File file = new File("save\\scheduleNotRepeat");

        // 序列化流
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));

        // 当前的非重复日程的list
        ArrayList<Schedule> ScheduleNotRepeatList = ScheduleData.getScheduleArrayNotRepeat();

        // 获取长度,方便以后读取
        int len = ScheduleNotRepeatList.size();
        int idLen = ScheduleData.getLen();

        // 告知实例长度
        os.write(len);

        // 告知id长度
        os.write(idLen);

        // 将list中的实例写入文件中
        for (Object c : ScheduleNotRepeatList) {
            // 获取每个实例
            os.writeObject(c);
        }

        // 释放
        os.close();
    }

    /**
     * 将 ScheduleData.scheduleArrayRepeat
     * 中的 重复类型的日程 实例写入文件存储
     *
     * @throws IOException IO流异常
     */
    private static void writerScheduleRepeat() throws IOException {
        // 文件路径
        File file = new File("save\\scheduleRepeat");

        // 序列化流
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));

        // 当前的重复日程的list
        ArrayList<Schedule> ScheduleRepeatList = ScheduleData.getScheduleArrayRepeat();

        // 获取长度,方便以后读取
        int len = ScheduleRepeatList.size();

        // 告知实例长度
        os.write(len);

        // 将list中的实例写入文件中
        for (Object c : ScheduleRepeatList) {
            // 获取每个实例
            os.writeObject(c);
        }

        // 释放
        os.close();
    }
}
