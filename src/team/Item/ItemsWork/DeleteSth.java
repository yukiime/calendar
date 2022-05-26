package team.Item.ItemsWork;
import team.Data.FestivalData;
import team.Data.ScheduleData;
import team.Item.ItemSchedule.CommemorationDay;
import team.Item.ItemSchedule.Schedule;
import team.Projectexception.ArrayException;
import team.Projectexception.ValueException;

import java.util.ArrayList;
import java.util.Calendar;

public class DeleteSth
{
    /**
     * 用于删除某个日程
     * @param id 用于身份验证的id
     * @param createTime 时间戳
     * @param isRepeat 是否重复
     * @throws ArrayException 队列异常
     */
    public static void deleteSchedule(int id,long createTime,boolean isRepeat) throws ArrayException {

        ArrayList<Schedule> arrayList = null;

        // 判断是有重复的还是无重复的日程
        if(!isRepeat){
            arrayList = ScheduleData.getScheduleArrayNotRepeat();
        }
        else if (isRepeat){
            arrayList = ScheduleData.getScheduleArrayRepeat();
        }

        int end = arrayList.size() - 1; //队列的末尾元素的index
        int start = 0; //队首
        int mid ; //用于取下标中间值
        int END = end;//记录结尾

        //判断是否为空
        if ( end == -1 )
        {
            throw new ArrayException("ArrayList已经为空,不能再进行删除操作");
        }

        if(arrayList.get(end).getCreateTime() < createTime)
        {
            throw new ArrayException("传入的createTime超出当前队列的最大值,不存在该数据");
        }
        else if(arrayList.get(start).getCreateTime() > createTime)
        {
            throw new ArrayException("传入的createTime小于当前队列的最小值,不存在该数据");
        }
        //在队列中二分搜索
        else
        {
            while (start <= end)
            {
                mid = (start + end) / 2;                //取中间值

                //查询下标的数据比传入数据小,下一个搜索区段的首下标更改为该下标+1
                if (arrayList.get(mid).getCreateTime() < createTime)
                {
                    start = mid + 1;
                }
                //查询下标的数据比传入数据大,下一个搜索区段的尾下标更改为该下标-1
                else if (arrayList.get(mid).getCreateTime() > createTime)
                {
                    end = mid - 1;
                }
                //在队列中寻找到了与传入数据相同的元素,其下标为mid,无须记录
                else if (arrayList.get(mid).getCreateTime() == createTime)
                {
                    if(arrayList.get(mid).getId() == id)
                    {
                        arrayList.remove(mid);
                        return;
                    }
                    else
                    {
                        int index1 = mid - 1;
                        int index2 = mid;

                        while (index1 >= 0 || index2 <= END)
                        {
                            //向前搜索
                            if (index1 >= 0)
                            {
                                if (arrayList.get(index1).getId() == id)
                                {
                                    arrayList.remove(index1);
                                    return;
                                }
                                index1--;
                            }
                            //向后搜索
                            if (index2 <= END)
                            {
                                if (arrayList.get(index2).getId() == id)
                                {
                                    arrayList.remove(index2);
                                    return;
                                }
                                index2++;
                            }
                        }
                    }

                    //抛出异常,防止程序死循环
                    throw new ArrayException("ArrayList中不存在此元素,应该是repeat或id或Time出错,请按优先级顺序检查");
                }
            }

            if(start == end + 1)
            {
                throw new ArrayException("ArrayList中不存在此元素,应该是repeat或Time出错,请按优先级顺序检查");
            }
        }
    }

    /**
     * 用于删除某个日程
     * 直接传入schedule参数
     * @param schedule 某个日程实例
     */
    public static void deleteSchedule(Schedule schedule)
    {

        ArrayList<Schedule> arrayList;

        // 判断是有重复的还是无重复的日程
        if(schedule.isRepeat())
        {
            arrayList = ScheduleData.getScheduleArrayRepeat();
            arrayList.remove(schedule);

        }
        else
        {
            arrayList = ScheduleData.getScheduleArrayNotRepeat();
            arrayList.remove(schedule);

        }
    }

    /**
     * 用于删除某个节日/纪念日
     * @param id 识别id
     * @param time 当天时间
     * @param code 重复类型
     * @throws ArrayException list出错
     * @throws ValueException 值出错
     */
    public static void deleteCommemorationDays_festival(int id,long time,short code) throws ArrayException, ValueException
    {
        if(code == 2)
        {
            //list的最后一个元素的下标
            int end = FestivalData.commemorationDays_festival.size() - 1;

            //检查13月中有没有元素
            if(FestivalData.commemorationDays_festival.get(end).getRepeatCode() == 2)
            {
                //获取13月的第一个元素的下标
                int start = FestivalData.sum(12);

                //检查月重复的元素是否只有1个
                if(start == end)
                {
                    if (FestivalData.commemorationDays_festival.get(start).getId() == id)
                    {
                        FestivalData.commemorationDays_festival.remove(start);
                        FestivalData.treeAdd(13,-1);
                    }
                    else
                    {
                        throw new ArrayException("list中没有此元素");
                    }
                }
                else
                {
                    int mid;

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(time);
                    int day = calendar.get(Calendar.DAY_OF_MONTH); //当天是几号

                    while (start <= end )
                    {
                        mid = (start + end) / 2;                //取中间值
                        CommemorationDay findDay = FestivalData.commemorationDays_festival.get(mid);

                        if (findDay.getDay() == day)
                        {
                            if (findDay.getId() == id)
                            {
                                FestivalData.commemorationDays_festival.remove(mid);
                                FestivalData.treeAdd(13,-1);
                                return;
                            }
                            else
                            {
                                int index1 = mid - 1;
                                int index2 = mid;

                                while (index1 >= 0 || index2 <= end)
                                {
                                    //向前搜索
                                    if (index1 >= 0)
                                    {
                                        if (FestivalData.commemorationDays_festival.get(index1).getId() == id)
                                        {
                                            FestivalData.commemorationDays_festival.remove(index1);
                                            FestivalData.treeAdd(13,-1);
                                            return;
                                        }
                                        index1--;
                                    }
                                    //向后搜索
                                    if (index2 <= end)
                                    {
                                        if (FestivalData.commemorationDays_festival.get(index2).getId() == id)
                                        {
                                            FestivalData.commemorationDays_festival.remove(index2);
                                            FestivalData.treeAdd(13,-1);
                                            return;
                                        }
                                        index2++;
                                    }
                                }

                                throw new ArrayException("没有在list中找到相符的元素");
                            }
                        }
                        //查询下标的数据比传入数据大,下一个搜索区段的尾下标更改为该下标-1
                        else if (findDay.getDay() > day)
                        {
                            end = mid - 1;
                        }
                        else if (findDay.getDay() < day)
                        {
                            start = mid + 1;
                        }
                    }
                    throw new ArrayException("没有在list中找到日期相同的元素");
                }
            }
            //13月中没有元素
            else
            {
                throw new ArrayException("当前没有月重复的元素");
            }
        }
        else if(code == 1)
        {
            //获取Time这个时间点的月份,日期
            int[] array = ScheduleWork.findDate(time);
            int start = FestivalData.sum(array[0] - 1); //该月份的第一天
            int end = FestivalData.sum(array[0]) - 1; //该月份的最后一天
            int mid;

            int day = array[1]; //当天是几号

            while (start <= end )
            {
                mid = (start + end) / 2;                //取中间值
                CommemorationDay findDay = FestivalData.commemorationDays_festival.get(mid);

                if (findDay.getDay() == day)
                {
                    if (findDay.getId() == id)
                    {
                        FestivalData.commemorationDays_festival.remove(mid);
                        FestivalData.treeAdd(array[0],-1);
                        return;
                    }
                    else
                    {
                        start = mid + 1;
                    }
                }
                else if(findDay.getDay() < day)
                {
                    start = mid + 1;
                }
                else if (findDay.getDay() > day)
                {
                    end = mid - 1;
                }
            }
            throw new ArrayException("没有在list中找到相符的元素");
        }
        else
        {
            throw new ValueException("传递的repeatCode越界,只能为1或2");
        }
    }

    /**
     * 删除某个节日/纪念日
     * @param id 识别id
     * @param time 当天时间
     * @throws ArrayException list中没有该元素
     */
    public static void deleteCommemorationDays_festival(int id,long time) throws ArrayException
    {
        int[] array = ScheduleWork.findDate(time);
        int month = array[0];
        int day = array[1]; //当天是几号

        //list的最后一个元素的下标
        int end = FestivalData.commemorationDays_festival.size() - 1;
        int mid;

        if(FestivalData.commemorationDays_festival.get(end).getRepeatCode() == 2)
        {
            int start = FestivalData.sum(12); //该月份的第一天

            //检查月重复的元素是否只有1个
            if (start == end)
            {
                if (FestivalData.commemorationDays_festival.get(start).getId() == id)
                {
                    FestivalData.commemorationDays_festival.remove(start);
                    FestivalData.treeAdd(13, -1);
                }
            }
            else
            {
                while (start <= end)
                {
                    mid = (start + end) / 2;                //取中间值
                    CommemorationDay findDay = FestivalData.commemorationDays_festival.get(mid);

                    if (findDay.getDay() == day)
                    {
                        if (findDay.getId() == id)
                        {
                            FestivalData.commemorationDays_festival.remove(mid);
                            FestivalData.treeAdd(13, -1);
                            return;
                        }
                        else
                        {
                            start = mid + 1;
                        }
                    }
                    else if (findDay.getDay() < day)
                    {
                        start = mid + 1;
                    }
                    else if (findDay.getDay() > day)
                    {
                            end = mid + 1;
                    }

                }
            }
        }

        int start = FestivalData.sum(month - 1); //该月份的第一天
        end = FestivalData.sum(month) - 1; //该月份的最后一天

        while (start <= end )
        {
            mid = (start + end) / 2;                //取中间值
            CommemorationDay findDay = FestivalData.commemorationDays_festival.get(mid);

            if (findDay.getDay() == day)
            {
                if (findDay.getId() == id)
                {
                    FestivalData.commemorationDays_festival.remove(mid);
                    FestivalData.treeAdd(month, -1);
                    return;
                }
                else
                {
                    start = mid + 1;
                }
            }
            else if (findDay.getDay() < day)
            {
                start = mid + 1;
            }
            else if (findDay.getDay() > day)
            {
                end = mid - 1;
            }
        }

        throw new ArrayException("list中没有符合的元素");
    }


    /**
     * 用于删除某个节日/纪念日
     * @param  id 识别id
     */
    public static void deleteCommemorationDays_festival(int id) throws ArrayException {
        for (int i = 0; i < FestivalData.commemorationDays_festival.size(); i++)
        {
            if(FestivalData.commemorationDays_festival.get(i).getId() == id)
            {
                FestivalData.treeAdd(FestivalData.commemorationDays_festival.get(i).getMonth(),-1);
                FestivalData.commemorationDays_festival.remove(i);
                return;
            }
        }
        throw new ArrayException("没在队列中发现id符合的元素");
    }
}
