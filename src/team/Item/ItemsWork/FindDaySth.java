package team.Item.ItemsWork;

import team.Data.FestivalData;
import team.Data.ScheduleData;
import team.Item.ItemSchedule.CommemorationDay;
import team.Item.ItemSchedule.Schedule;
import team.Projectexception.ValueException;
import java.util.ArrayList;
import java.util.Random;

/**
 * 用途:
 * 用于获取当天的Schedule
 * 以ArrayList形式传回
 * 以order从大到小排序
 */

public class FindDaySth {
  private static final int MILLISECOND_DAY = 86400000;

  /**
   * 用于获取当天的Schedule
   * dayTime为当日的时间
   * 以long的Millis形式传入
   * 以ArrayList形式传回
   * list中已经按order从大到小排序
   * 
   * @param dayTime 当日的时间戳
   * @return scheduleList
   */
  public static ArrayList<Schedule> findAllSchedule(long dayTime) throws ValueException {
    ArrayList<Schedule> scheduleList = findScheduleArray(dayTime);
    ArrayList<Schedule> scheduleList2 = findScheduleArrayRepeat(dayTime);
    scheduleList.addAll(scheduleList2);

    if (scheduleList.size() >= 2) {
      return ScheduleWork.OrderSort(scheduleList);
    } else {
      return scheduleList;
    }
  }

  /**
   * 获取当天的没有重复的Schedule
   * 即在当天创建的Schedule
   * 
   * @param dayTime 当日的时间戳
   * @return scheduleList
   */
  public static ArrayList<Schedule> findScheduleArray(long dayTime) {
    ArrayList<Schedule> arrayList = ScheduleData.getScheduleArrayNotRepeat();
    ArrayList<Schedule> scheduleList = new ArrayList<>();

    int end = arrayList.size() - 1; // 队列的末尾元素的index
    int start = 0; // 队首
    int mid; // 用于取下标中间值

    // 判断是否为空
    if (end == -1 || (arrayList.get(end).getCreateTime() < dayTime) || (arrayList.get(0).getCreateTime() > dayTime)) {
      return scheduleList; // 不会产生空指针
    }
    // 在队列中二分搜索
    else {
      while (start <= end) {
        mid = (start + end) / 2; // 取中间值

        // 查询下标的数据比传入数据小,下一个搜索区段的首下标更改为该下标+1
        if (arrayList.get(mid).getCreateTime() < dayTime) {
          start = mid + 1;
        }
        // 查询下标的数据比传入数据大,下一个搜索区段的尾下标更改为该下标-1
        else if (arrayList.get(mid).getCreateTime() > dayTime) {
          end = mid - 1;
        }
        // 在队列中寻找到了与传入数据相同的元素,其下标为mid,无须记录
        else if (arrayList.get(mid).getCreateTime() == dayTime) {
          int anchor = mid + 1;// 记录当前的点的下一位,便于向后搜索

          // 先向前搜索
          while (arrayList.get(mid).getCreateTime() == dayTime) {
            scheduleList.add(arrayList.get(mid)); // 将符合的list元素存入新的list的末尾
            mid--;
          }

          // 向后搜索
          while (anchor < arrayList.size() && arrayList.get(anchor).getCreateTime() == dayTime) {
            scheduleList.add(arrayList.get(anchor)); // 将符合的list元素存入新的list的末尾
            anchor++;
          }

          break;
        }
      }
    }
    return scheduleList;
  }

  /**
   * 获取符合的重复的Schedule
   * 以list的形式返回
   * 
   * @param dayTime 当日的时间戳
   * @return checkRepeat(dayTime,scheduleList)
   */
  public static ArrayList<Schedule> findScheduleArrayRepeat(long dayTime) throws ValueException {

    ArrayList<Schedule> arrayList = ScheduleData.getScheduleArrayRepeat();
    ArrayList<Schedule> scheduleList = new ArrayList<>();

    int end = arrayList.size() - 1; // 队列的末尾元素的index
    int start = 0; // 队首
    int mid; // 用于取下标中间值

    // 判断是否为空或是否都创建在当天之后
    if (end == -1 || (arrayList.get(0).getCreateTime() > dayTime)) {
      return scheduleList; // 防止空指针
    }
    // 判断是否都创建在当天之前,完成第一次筛查
    else if (arrayList.get(end).getCreateTime() <= dayTime) {
      scheduleList.addAll(arrayList);
    }
    // 在队列中二分搜索,完成第一次筛查
    else {
      while (start <= end) {

        mid = (start + end) / 2; // 取中间值

        // 查询下标的数据比传入数据小,下一个搜索区段的首下标更改为该下标+1
        if (arrayList.get(mid).getCreateTime() < dayTime) {
          start = mid + 1;
        }
        // 查询下标的数据比传入数据大,下一个搜索区段的尾下标更改为该下标-1
        else if (arrayList.get(mid).getCreateTime() > dayTime) {
          end = mid - 1;
        }
        // 在队列中寻找到了与传入数据相同的元素,其下标为mid,无须记录
        else if (arrayList.get(mid).getCreateTime() == dayTime) {

          while (arrayList.get(mid).getCreateTime() == dayTime) {
            mid++;
          }

          end = mid - 1; // 方便下面赋值
          break;
        }
      }

      // 从0到end的元素都插入到schedule的末尾
      for (int i = 0; i <= end; i++) {
        scheduleList.add(arrayList.get(i));
      }
    }

    // 返回第二次筛选的list
    return checkRepeat(dayTime, scheduleList);
  }

  /**
   * 进行第二次筛选
   * 返回重复日程list中会在当天重复的日程list
   * 返回以list的形式返回
   * 
   * @param dayTime   当日的时间戳
   * @param arrayList 是重复的日程列表,还需要进一步判断是否符合当天
   * @return repeatList
   * @throws ValueException 值错误
   */
  public static ArrayList<Schedule> checkRepeat(long dayTime, ArrayList<Schedule> arrayList) throws ValueException {

    ArrayList<Schedule> repeatList = new ArrayList<>();

    // 检查当天的月份,日期,星期数
    int[] date = ScheduleWork.findDate(dayTime);

    for (Schedule schedule : arrayList) {

      // 如果重复日程刚好在当天创建
      if (schedule.getCreateTime() == dayTime) {
        repeatList.add(schedule);
      }
      // 寻找当天是否是重复的那一天
      else {
        // 记录重复类型
        short code = schedule.getRepeatCode();

        switch (code) {
          case 1:
            // 是年重复
            if (date[0] == schedule.getMonth() && date[1] == schedule.getDay()) {
              repeatList.add(schedule);
            }
            break;

          case 2:
            // 是月重复
            if (date[1] == schedule.getDay()) {
              repeatList.add(schedule);
            }
            break;

          case 3:
            // 是星期重复
            if (date[2] == schedule.getWeek()) {
              repeatList.add(schedule);
            }
            break;

          case 4:
            // 符合自定义重复的区间
            long duration = (long) schedule.getRepeatDuration() * MILLISECOND_DAY;
            if ((dayTime - schedule.getCreateTime()) % duration == 0) {
              repeatList.add(schedule);
            }
            break;
          default:
            // 检查异常
            throw new ValueException("检查到scheduleArrayRepeat中有越界的repeatCode");

        }
      }
    }
    return repeatList;
  }

  /**
   * 按照年重复排在月重复之前的顺序 (可以要求修改顺序)
   * 返回当日的纪念日/节日列表
   * 
   * @param time 当日的时间戳
   * @return todayList
   */
  public static ArrayList<CommemorationDay> findCommemorationDays_festival(long time) {
    ArrayList<CommemorationDay> arrayList = FestivalData.commemorationDays_festival;
    ArrayList<CommemorationDay> todayList = new ArrayList<>();

    // 获取Time这个时间点的月份,日期
    int[] array = ScheduleWork.findDate(time);
    // 下标
    int index;

    int start = FestivalData.sum(array[0] - 1); // 该月份的第一天
    int end = FestivalData.sum(array[0]) - 1; // 该月份的最后一天

    for (index = start; index <= end; index++) {
      if (arrayList.get(index).getDay() == array[1]) {
        todayList.add(arrayList.get(index));
      } else if (arrayList.get(index).getDay() > array[1]) {
        break;
      }
    }

    // 开始在13月中 寻找月重复的纪念日
    // 13月的第一个元素的下标
    start = FestivalData.sum(12);
    // 最后一个元素的下标
    end = FestivalData.commemorationDays_festival.size() - 1;

    for (index = start; index <= end; index++) {
      if (arrayList.get(index).getDay() == array[1]) {
        todayList.add(arrayList.get(index));
      } else if (arrayList.get(index).getDay() > array[1]) {
        break;
      }
    }

    return todayList;
  }

}
