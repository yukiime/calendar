package team.Item.ItemsWork;
import team.Data.ScheduleData;
import team.Item.ItemSchedule.Schedule;
import team.Projectexception.ArrayException;
import java.util.ArrayList;

public class DeleteSth
{
    /**
     * 用于删除某个日程
     * @param id
     * @param createTime
     * @param isRepeat
     * @throws ArrayException
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
                        int index1 = mid;
                        int index2 = mid;

                        while (index1 >= 0 || index2 <= END)
                        {
                            //向前搜索
                            if (index1 > 0)
                            {
                                index1--;
                                if (arrayList.get(index1).getId() == id)
                                {
                                    arrayList.remove(index1);
                                    return;
                                }
                            }
                            //向后搜索
                            if (index2 < END)
                            {
                                index2++;
                                if (arrayList.get(index2).getId() == id)
                                {
                                    arrayList.remove(index2);
                                    return;
                                }
                            }
                        }
                    }
                }
            }

            if(start == end + 1)
            {
                throw new ArrayException("ArrayList中不存在此元素");
            }
        }
    }

    /**
     * 用于删除某个日程
     * 直接传入schedule参数
     * @param schedule
     */
    public static void deleteSchedule(Schedule schedule)
    {

        ArrayList<Schedule> arrayList = null;

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

}