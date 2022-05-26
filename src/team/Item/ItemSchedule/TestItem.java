package team.Item.ItemSchedule;

import team.Data.FestivalData;
import team.Data.ScheduleData;
import team.Item.ItemsWork.*;
import team.Projectexception.ArrayException;
import team.Projectexception.ValueException;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 用途:
 * 个人测试使用
 */
public class TestItem {
  public static void main(String[] args) throws ArrayException, ValueException {

    /**
     * SimpleDateFormat("yyyy年MM月dd日E");
     * String format = simpleDateFormat.format(calendar1.getTime());
     * System.out.println(format);
     * 
     * calendar1.set(10002,11,36);
     * System.out.println(calendar1.getTimeInMillis());
     * 
     * for (int i = 0; i < 12; i++)
     * {
     * System.out.println(arrayList.get(a[i]).getMonth());
     * 
     * }
     */

    CreateSth.createSchedule(56562626262323131L, "测试1");
    CreateSth.createSchedule(5656263131L, "测试2");
    CreateSth.createSchedule(5656263131L, "测试3");
    CreateSth.createSchedule(4646222L, "测试4");
    CreateSth.createSchedule(111L, "测试5");
    CreateSth.createSchedule(999999999L, "测试6");

    FestivalData.FestivalDataBase();

    Calendar calendar = Calendar.getInstance();
    calendar.set(2022, 5, 1);
    long time = calendar.getTimeInMillis();

    CreateSth.createCommemorationDay(time, "小明节");

    ArrayList<CommemorationDay> arrayList = FindDaySth.findCommemorationDays_festival(time);

    ArrayList<Schedule> arrayList1 = ScheduleData.getScheduleArrayRepeat();

    CreateSthRepeat.createSchedule(1653494400000L, "测试0-1", true, (short) 1);
    CreateSthRepeat.createSchedule(1653494400000L, "测试0-2", true, (short) 1);
    CreateSthRepeat.createSchedule(1653494400000L, "测试0-3", true, (short) 1);
    CreateSthRepeat.createSchedule(1653494400000L, "测试0-4", true, (short) 1);
    CreateSthRepeat.createSchedule(1653494400000L, "测试0-5", true, (short) 1);

    for (Schedule schedule : arrayList1) {
      System.out.println(schedule.getId() + " " + schedule.getCreateTime() + " " + schedule.getContent());
    }

    ArrayList<Schedule> arrayList3 = ScheduleData.getScheduleArrayNotRepeat();

    for (Schedule schedule : arrayList3) {
      System.out.println(schedule.getContent());
    }

    ArrayList<Schedule> arrayList2 = FindDaySth.findAllSchedule(56562626262323131L);

    System.out.println(arrayList2.size());

    for (Schedule schedule : arrayList2) {
      System.out.println(schedule.getId() + " " + schedule.getCreateTime() + " " + schedule.getContent());
    }

    System.out.println("-------------------------------------------------------");

    DeleteSth.deleteSchedule(7, 56562626262323131L, true);

    ArrayList<Schedule> arrayList4 = ScheduleData.getScheduleArrayRepeat();

    for (Schedule schedule : arrayList4) {
      System.out.println(schedule.getContent());
    }

    System.out.println("-------------------------------------------------------");

    ArrayList<CommemorationDay> arrayList5 = FestivalData.commemorationDays_festival;

    for (CommemorationDay commemorationDay : arrayList5) {
      System.out.println(commemorationDay.getId() + " " + commemorationDay.getContent());
    }

    DeleteSth.deleteCommemorationDays_festival(25);

    for (CommemorationDay commemorationDay : arrayList5) {
      System.out.println(commemorationDay.getId() + " " + commemorationDay.getContent());
    }
  }
}
