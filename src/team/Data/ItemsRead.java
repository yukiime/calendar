package team.Data;

import team.Item.ItemSchedule.CommemorationDay;
import team.Item.ItemSchedule.Schedule;

import java.io.*;
import java.util.ArrayList;

public class ItemsRead {

    public static void main(String[] args) {
        Test1();
        readAllItems();
        Test1();
    }

    public static void Test1() {
        System.out.println(FestivalData.commemorationDays_festival.size());
        for (CommemorationDay c : FestivalData.commemorationDays_festival) {
            System.out.println(c.getId() + " " + c.getContent() + " " + c.getClass());
        }
    }

    public static void readAllItems() {
        try {
            readCommemorationDays_festival();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 commemorationDays_festival.txt
     * 中的 节日/纪念日 实例读取并
     * 赋值给 FestivalData.commemorationDays_festival
     *
     * @throws IOException IO流异常
     */
    private static void readCommemorationDays_festival() throws IOException, ClassNotFoundException {
        // 文件路径
        File file = new File("save\\commemorationDays_festival.txt");

        // 反序列化流
        ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));

        // 清空
        FestivalData.commemorationDays_festival.clear();

        // 获取长度
        int len = os.read();

        // 将读取的数据给实例并将实例添加到list中
        for (int i = 0; i < len; i++) {
            Object object = os.readObject();
            FestivalData.commemorationDays_festival.add((CommemorationDay) object);
        }
    }

    /**
     * 将 scheduleNotRepeat.txt
     * 中的 非重复类型的日程 实例读取并
     * 赋值给 ScheduleData.scheduleArrayNotRepeat
     *
     * @throws IOException IO流异常
     */
    private static void readScheduleNotRepeat() throws IOException {
        // 文件路径
        File file = new File("save\\scheduleNotRepeat.txt");

        // 反序列化流
        ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));

        // 存储读取数据的list
        ArrayList<Schedule> arrayList = ScheduleData.getScheduleArrayNotRepeat();

    }

    /**
     * 将 scheduleRepeat.txt
     * 中的 重复类型的日程 实例读取并
     * 赋值给 ScheduleData.scheduleArrayRepeat
     *
     * @throws IOException IO流异常
     */
    private static void readScheduleRepeat() throws IOException {
        // 文件路径
        File file = new File("save\\scheduleRepeat.txt");

        // 反序列化流
        ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));

        // 存储读取数据的list
        ArrayList<Schedule> arrayList = ScheduleData.getScheduleArrayRepeat();
    }
}
