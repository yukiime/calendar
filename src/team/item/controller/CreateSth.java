package team.item.controller;

import team.safe.ArrayException;
import team.safe.ValueException;
import team.data.*;
import team.item.schedule.CommemorationDay;
import team.item.schedule.Schedule;

public class CreateSth {

    public static void createSchedule(long createTime, String content) throws ArrayException {
        // 总共创建的日程数+1
        ScheduleData.addLenOne();

        // 获取总共创建的日程数
        int len = ScheduleData.getLen();

        // 创建一个新的日程
        Schedule schedule = new Schedule(len, createTime, content);

        // 寻找到应该插入的位置
        int index = findLastTimeMatchSchedule(createTime);

        // 将新日程添加到未重复的数组的队尾
        ScheduleData.addIndexScheduleArrayNotRepeat(index, schedule);
    }

    // 构造不是重复日程,有优先级的日程
    // 除isRepeat的父类成员变量调用父构造器赋值,随后赋值order,isRepeat默认为false
    public static void createSchedule(long createTime, String content, short order)
            throws ValueException, ArrayException {
        // 确保order不越界
        if (order >= 0 && order < 4) {
            // 总共创建的日程数+1
            ScheduleData.addLenOne();

            // 获取总共创建的日程数
            int len = ScheduleData.getLen();

            // 创建一个新的日程
            Schedule schedule = new Schedule(len, createTime, content, order);

            // 寻找到应该插入的位置
            int index = findLastTimeMatchSchedule(createTime);

            // 将新日程添加到未重复的数组的队尾
            ScheduleData.addIndexScheduleArrayNotRepeat(index, schedule);
        } else {
            throw new ValueException("创建Schedule时,order超出范围");
        }
    }

    // 寻找到同一天的日程组的队尾在队列中的位置
    private static int findLastTimeMatchSchedule(long createTime) throws ArrayException {

        // 获取无重复的日程的ArrayList的队尾和队首
        int end = ScheduleData.getScheduleArrayNotRepeat().size() - 1; // 队列的末尾元素的index
        int start = 0; // 队首
        int mid; // 用于取下标中间值

        // 队列中无元素 或 队尾元素也比传入数据小,直接插入队尾
        if (end == -1 || createTime >= ScheduleData.getIndexSchedule(end).getCreateTime()) {
            return end + 1;
        }
        // 队首元素也比传入数据大,直接插入队首
        else if (createTime < ScheduleData.getIndexSchedule(0).getCreateTime()) {
            return 0;
        }
        // 在队列中二分搜索
        else {

            while (start <= end) {

                mid = (start + end) / 2; // 取中间值
                // 查询下标的数据比传入数据小,下一个搜索区段的首下标更改为该下标+1
                if (ScheduleData.getIndexSchedule(mid).getCreateTime() <= createTime) {
                    start = mid + 1;
                }
                // 查询下标的数据比传入数据大,下一个搜索区段的尾下标更改为该下标-1
                else if (ScheduleData.getIndexSchedule(mid).getCreateTime() > createTime) {
                    end = mid - 1;
                }
                // 在队列中寻找到了与传入数据相同的元素,其下标为mid,无须记录
                else if (ScheduleData.getIndexSchedule(mid).getCreateTime() == createTime) {

                    // 以该下标为起点,向后搜索,直到出现拥有不同数据的元素,向后搜索的最大长度为上一搜索区间段的尾
                    for (int i = mid; i <= end; i++) {

                        if (ScheduleData.getIndexSchedule(i).getCreateTime() > createTime) {
                            return i;
                        }

                        // 队列中可能存在排序错误,给出错误提示
                        else {
                            throw new ArrayException("findLastTimeMatchSchedule的for循环error,或是队列中排序错误");
                        }
                    }
                }
            }

            // 此时队列中不存在与传入数据相同的元素,故进行插入
            return end + 1;
        }
    }

    /**
     * 创建默认年重复的纪念日
     *
     * @param createTime 创建的时间
     * @param content    文本
     */
    public static void createCommemorationDay(long createTime, String content) {
        int len = FestivalData.getIdOfLen();

        CommemorationDay commemorationDay = new CommemorationDay(len, createTime, content);

        int start = FestivalData.sum(commemorationDay.getMonth() - 1); // 该月份的第一天
        int end = FestivalData.sum(commemorationDay.getMonth()) - 1; // 该月份的最后一天

        if (end < start) // 即当月和下一月都没有元素,start实际上指向上一个有元素的月之后某个有元素的月的第一个元素,end实际指向上一个有元素的月的最后一个元素
        {
            FestivalData.commemorationDays_festival.add(start, commemorationDay);
        } else if (FestivalData.commemorationDays_festival.get(end).getDay() <= commemorationDay.getDay()) {
            FestivalData.commemorationDays_festival.add(end + 1, commemorationDay);
        } else if (FestivalData.commemorationDays_festival.get(start).getDay() > commemorationDay.getDay()) {
            FestivalData.commemorationDays_festival.add(start, commemorationDay);
        } else {
            int mid;
            boolean flag = false;

            while (start <= end) {
                mid = (start + end) / 2;

                // 有找到日期相同的元素,将其插入到相同日期的元素的末尾
                if (FestivalData.commemorationDays_festival.get(mid).getDay() == commemorationDay.getDay()) {
                    for (int i = mid; i <= end; i++) {
                        if (FestivalData.commemorationDays_festival.get(i).getDay() > commemorationDay.getDay()) {
                            FestivalData.commemorationDays_festival.add(i, commemorationDay);
                            flag = true; // 已经完成插入操作
                            break;
                        }
                    }
                    break;
                } else if (FestivalData.commemorationDays_festival.get(mid).getDay() < commemorationDay.getDay()) {
                    start = mid + 1;
                } else if (FestivalData.commemorationDays_festival.get(mid).getDay() > commemorationDay.getDay()) {
                    end = mid - 1;
                }
            }

            // 没有找到相同日期的元素
            if (!flag) {
                FestivalData.commemorationDays_festival.add(end + 1, commemorationDay);
            }
        }

        // 维护monthIndex
        FestivalData.treeAdd(commemorationDay.getMonth(), 1);
    }

    /**
     * 没有完成测试 不建议调用
     *
     * @param createTime 创建时间
     * @param content    文本
     * @param repeatCode 重复类型
     * @throws ValueException repeatCode传递错误
     */
    public static void createCommemorationDay(long createTime, String content, short repeatCode) throws ValueException {

        if (repeatCode == 1) {
            createCommemorationDay(createTime, content);
        } else if (repeatCode == 2) {
            int len = FestivalData.getIdOfLen();

            CommemorationDay commemorationDay = new CommemorationDay(len, createTime, content, repeatCode);

            // 最后一个元素的下标
            int end = FestivalData.commemorationDays_festival.size() - 1;

            // 如果此时还没有13月的元素
            if (FestivalData.commemorationDays_festival.get(end).getRepeatCode() == 1) {
                FestivalData.commemorationDays_festival.add(commemorationDay);
                return;
            }

            // 13月的第一个元素的下标
            int start = FestivalData.sum(12);

            // 进行插入操作
            if (FestivalData.commemorationDays_festival.get(end).getDay() <= commemorationDay.getDay()) {
                FestivalData.commemorationDays_festival.add(end + 1, commemorationDay);
            } else if (FestivalData.commemorationDays_festival.get(start).getDay() > commemorationDay.getDay()) {
                // 当天的day of month 比 13月第一个元素的day of month 要小
                FestivalData.commemorationDays_festival.add(start, commemorationDay);
            } else {
                int mid;
                boolean flag = false;

                while (start <= end) {
                    mid = (start + end) / 2;

                    if (FestivalData.commemorationDays_festival.get(mid).getDay() == commemorationDay.getDay()) {
                        for (int i = mid; i <= end; i++) {
                            if (FestivalData.commemorationDays_festival.get(i).getDay() > commemorationDay.getDay()) {
                                FestivalData.commemorationDays_festival.add(i, commemorationDay);
                                flag = true;
                                break;
                            }
                        }
                        break;
                    } else if (FestivalData.commemorationDays_festival.get(mid).getDay() < commemorationDay.getDay()) {
                        start = mid + 1;
                    } else if (FestivalData.commemorationDays_festival.get(mid).getDay() > commemorationDay.getDay()) {
                        end = mid - 1;
                    }
                }

                if (!flag) {
                    FestivalData.commemorationDays_festival.add(end + 1, commemorationDay);
                }
            }

            // 维护monthIndex
            FestivalData.treeAdd(13, 1);
        } else {
            throw new ValueException("创建commemorationDay时repeatCode传入超出范围");
        }
    }
}
