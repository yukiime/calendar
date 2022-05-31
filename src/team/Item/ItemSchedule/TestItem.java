package team.Item.ItemSchedule;

import team.Data.FestivalData;
import team.Item.ItemsWork.*;
import team.Projectexception.ArrayException;
import team.Projectexception.ValueException;
import team.utils.FindSolarTerm;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 用途:
 * 个人测试使用
 */
public class TestItem {

  public static void Test1(String[] args) throws ArrayException, ValueException {

    FestivalData.FestivalDataBase();
    ArrayList<CommemorationDay> arrayList = FestivalData.commemorationDays_festival;

    System.out.println(arrayList.size());

    for (CommemorationDay commemorationDay : arrayList) {
      System.out.println(commemorationDay.getId() + " " + commemorationDay.getContent());
    }

    System.out.println("--------------------------------------");

    Calendar calendar = Calendar.getInstance();
    calendar.set(2022, Calendar.JUNE, 1);
    long time = calendar.getTimeInMillis();

    // 删除
    DeleteSth.deleteCommemorationDays_festival(29);
    // 创建6.1号
    CreateSth.createCommemorationDay(time, "小明节");

    // 创建1号
    CreateSth.createCommemorationDay(time, "测试1号", (short) 2);
    // 创建2号
    CreateSth.createCommemorationDay((time + 86400000L), "测试2号", (short) 2);
    CreateSth.createCommemorationDay((time + 86400000L), "测试2-1号", (short) 2);
    CreateSth.createCommemorationDay((time + 86400000L), "测试2-2号", (short) 2);
    CreateSth.createCommemorationDay((time + 86400000L), "测试2-3号", (short) 2);
    CreateSth.createCommemorationDay((time + 86400000L), "测试2-4号", (short) 2);
    CreateSth.createCommemorationDay((time + 2 * 86400000L), "测试3-1号", (short) 2);
    CreateSth.createCommemorationDay((time + 86400000L), "测试2-5号", (short) 2);
    CreateSth.createCommemorationDay((time + 15 * 86400000L), "测试16-1号", (short) 2);
    CreateSth.createCommemorationDay((time + 8 * 86400000L), "测试9-1号", (short) 2);
    CreateSth.createCommemorationDay((time + 14 * 86400000L), "测试15-1号", (short) 2);
    CreateSth.createCommemorationDay((time + 14 * 86400000L), "测试15-2号", (short) 2);
    CreateSth.createCommemorationDay((time + 14 * 86400000L), "测试15-3号", (short) 2);
    CreateSth.createCommemorationDay((time + 14 * 86400000L), "测试15-4号", (short) 1);
    CreateSth.createCommemorationDay((time + 14 * 86400000L), "测试15-5号", (short) 1);

    for (CommemorationDay commemorationDay : arrayList) {
      System.out.println(
          arrayList.indexOf(commemorationDay) + " " + commemorationDay.getId() + " " + commemorationDay.getContent());
    }

    System.out.println("-----------------------------");

    ArrayList<CommemorationDay> arrayList1 = FindDaySth.findCommemorationDays_festival(time + 14 * 86400000L);

    for (CommemorationDay commemorationDay : arrayList1) {
      System.out.println(
          arrayList1.indexOf(commemorationDay) + " " + commemorationDay.getId() + " " + commemorationDay.getContent());
    }

    System.out.println("-----------------------------");

    DeleteSth.deleteCommemorationDays_festival(69);
    DeleteSth.deleteCommemorationDays_festival(66, time + 14 * 86400000L, (short) 2);
    DeleteSth.deleteCommemorationDays_festival(68, time + 14 * 86400000L, (short) 1);

    for (CommemorationDay commemorationDay : FestivalData.commemorationDays_festival) {
      System.out.println(FestivalData.commemorationDays_festival.indexOf(commemorationDay) + " "
          + commemorationDay.getId() + " " + commemorationDay.getContent());
    }

    System.out.println("-----------------------------");

    DeleteSth.deleteCommemorationDays_festival(61, time + 2 * 86400000L);

    for (CommemorationDay commemorationDay : FestivalData.commemorationDays_festival) {
      System.out.println(FestivalData.commemorationDays_festival.indexOf(commemorationDay) + " "
          + commemorationDay.getId() + " " + commemorationDay.getContent());
    }

  }

  public static void siderTest(long time) throws ArrayException, ValueException {

    CreateSthRepeat.createSchedule(time, "测试0-1", true, (short) 1);
    CreateSthRepeat.createSchedule(time, "测试0-2", true, (short) 1);
    CreateSthRepeat.createSchedule(time, "测试0-3", true, (short) 1);
    CreateSthRepeat.createSchedule(time, "测试0-4", true, (short) 1);
    CreateSthRepeat.createSchedule(time, "测试0-5", true, (short) 1);
  }
}
