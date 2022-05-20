package team.Item.ItemsWork;
import team.Data.ScheduleData;
import team.Item.ItemSchedule.Schedule;
import team.Projectexception.ArrayException;
import team.Projectexception.ValueException;

public class CreateSthRepeat
{

    /**
     * 仅限于 年重复,月重复,星期重复
     * 构造默认order的有重复的日程
     * @param createTime
     * @param content
     * @param isRepeat
     * @param repeatCode
     * @throws ArrayException
     * @throws ValueException
     */
    public static void createSchedule(long createTime,String content,boolean isRepeat,short repeatCode) throws ArrayException, ValueException {
        //如果不是重复的日程,进行无重复日程的创建
        if(!isRepeat)
        {
            CreateSth.createSchedule(createTime,content);
        }
        else if(isRepeat)
        {
            if (repeatCode >= 1 && repeatCode <= 3) {
                //总共创建的日程数+1
                ScheduleData.addLenOne();

                //获取总共创建的日程数
                int len = ScheduleData.getLen();

                //创建一个新的日程
                Schedule schedule = new Schedule(len, createTime, content, isRepeat, repeatCode);

                //设置重复日期
                schedule.setAutoRepeatDuration();

                //寻找到应该插入的位置
                int index = findLastTimeMatchSchedule(createTime);

                //将新日程添加到未重复的数组的队尾
                ScheduleData.addIndexScheduleArrayRepeat(index, schedule);
            }
            else if (repeatCode == 4) {
                throw new ValueException("创建自定义周期Schedule时,未传入重复周期");
            }
            else {
                throw new ValueException("创建Schedule时,order超出范围");
            }
        }
    }

    /**
     * 仅限于 自定义重复
     * 构造默认order的有重复的日程
     * @param createTime
     * @param content
     * @param isRepeat
     * @param repeatDuration
     * @param repeatCode
     * @throws ArrayException
     * @throws ValueException
     */
    public static void createSchedule(long createTime,String content,boolean isRepeat,int repeatDuration,short repeatCode) throws ArrayException, ValueException {
        if(isRepeat)
        {
            if (repeatCode == 4) {

                if(repeatDuration > 0)
                {
                    //总共创建的日程数+1
                    ScheduleData.addLenOne();

                    //获取总共创建的日程数
                    int len = ScheduleData.getLen();

                    //创建一个新的日程
                    Schedule schedule = new Schedule(len, createTime, repeatDuration, content);

                    //寻找到应该插入的位置
                    int index = findLastTimeMatchSchedule(createTime);

                    //将新日程添加到未重复的数组的队尾
                    ScheduleData.addIndexScheduleArrayRepeat(index, schedule);

                }
                else
                {
                    throw new ValueException("repeatDuration不可小于等于0");
                }

            } else {
                throw new ValueException("创建自定义重复的Schedule,repeatCode传入错误");
            }
        }
        //如果不是重复的日程,进行无重复日程的创建
        else
        {
            CreateSth.createSchedule(createTime,content);
        }
    }

    /**
     * 仅限于 年重复,月重复,星期重复
     * 构造有优先级的日程
     * @param createTime
     * @param content
     * @param isRepeat
     * @param repeatCode
     * @param order
     * @throws ValueException
     * @throws ArrayException
     */
    public static void createSchedule(long createTime,String content,boolean isRepeat,short repeatCode,short order) throws ValueException, ArrayException {

        //如果不是重复的日程,进行无重复日程的创建
        if(!isRepeat)
        {
            CreateSth.createSchedule(createTime,content,order);
        }
        else if(isRepeat)
        {
            //确保order不越界
            if(order >= 0 && order < 4)
            {
                if (repeatCode >= 1 && repeatCode <= 3) {
                    //总共创建的日程数+1
                    ScheduleData.addLenOne();

                    //获取总共创建的日程数
                    int len = ScheduleData.getLen();

                    //创建一个新的日程
                    Schedule schedule = new Schedule(len, createTime, content, isRepeat, repeatCode, order);

                    //设置重复日期
                    schedule.setAutoRepeatDuration();

                    //寻找到应该插入的位置
                    int index = findLastTimeMatchSchedule(createTime);

                    //将新日程添加到未重复的数组的队尾
                    ScheduleData.addIndexScheduleArrayRepeat(index, schedule);
                }
                else if (repeatCode == 4)
                {
                    throw new ValueException("创建自定义周期Schedule时,未输入重复周期");
                }
                else
                {
                    throw new ValueException("创建Schedule时,repeatCode超出范围");
                }
            }
            else //越界抛出异常
            {
                throw new ValueException("创建Schedule时,order超出范围");
            }
        }
    }

    /**
     * 仅限于自定义重复
     * 构造有优先级的日程
     * @param createTime
     * @param content
     * @param isRepeat
     * @param repeatDuration
     * @param repeatCode
     * @param order
     * @throws ArrayException
     * @throws ValueException
     */
    public static void createSchedule(long createTime,String content,boolean isRepeat,int repeatDuration,short repeatCode,short order) throws ArrayException, ValueException {
        if(isRepeat)
        {
            if(order >= 0 && order < 4)
            {
                if (repeatCode == 4)
                {
                    if (repeatDuration > 0)
                    {
                        //总共创建的日程数+1
                        ScheduleData.addLenOne();

                        //获取总共创建的日程数
                        int len = ScheduleData.getLen();

                        //创建一个新的日程
                        Schedule schedule = new Schedule(len, createTime, repeatDuration, content);
                        
                        //设置order
                        schedule.setOrder(order);

                        //寻找到应该插入的位置
                        int index = findLastTimeMatchSchedule(createTime);

                        //将新日程添加到未重复的数组的队尾
                        ScheduleData.addIndexScheduleArrayRepeat(index, schedule);

                    }
                    else
                    {
                        throw new ValueException("repeatDuration不可小于等于0");
                    }
                }
                else
                {
                throw new ValueException("创建自定义重复的Schedule,repeatCode传入错误");
                }
            }
            else
            {
                throw new ValueException("创建Schedule时,repeatCode超出范围");
            }

        }
        //如果不是重复的日程,进行无重复日程的创建
        else
        {
            CreateSth.createSchedule(createTime,content);
        }
    }

    //还没写好,要替换名称
    /**
     * 寻找到同一天的日程组的队尾在队列中的位置
     * @param createTime
     * @return
     * @throws ArrayException
     */
    private static int findLastTimeMatchSchedule(long createTime) throws ArrayException{

        //获取重复的日程的ArrayList的队尾和队首
        int end = ScheduleData.getScheduleArrayRepeat().size() - 1; //队列的末尾元素的index
        int start = 0; //队首
        int mid ; //用于取下标中间值

        //队列中无元素 或 队尾元素也比传入数据小,直接插入队尾
        if ( end == -1 || createTime >= ScheduleData.getIndexRepeatSchedule(end).getCreateTime() ) {
            return end + 1;
        }
        //队首元素也比传入数据大,直接插入队首
        else if (createTime < ScheduleData.getIndexRepeatSchedule(0).getCreateTime()) {
            return 0;
        }
        //在队列中二分搜索
        else {

            while (start <= end) {

                mid = (start + end) / 2;                //取中间值

                //查询下标的数据比传入数据小,下一个搜索区段的首下标更改为该下标+1
                if (ScheduleData.getIndexRepeatSchedule(mid).getCreateTime() < createTime) {
                    start = mid + 1;
                }
                //查询下标的数据比传入数据大,下一个搜索区段的尾下标更改为该下标-1
                else if (ScheduleData.getIndexRepeatSchedule(mid).getCreateTime() > createTime) {
                    end = mid - 1;
                }
                //在队列中寻找到了与传入数据相同的元素,其下标为mid,无须记录
                else if (ScheduleData.getIndexRepeatSchedule(mid).getCreateTime() == createTime) {

                    //以该下标为起点,向后搜索,直到出现拥有不同数据的元素,向后搜索的最大长度为上一搜索区间段的尾
                    for (int i = mid; i <= end; i++) {

                        if (ScheduleData.getIndexRepeatSchedule(i).getCreateTime() > createTime) {
                            return i;
                        }
                        //队列中可能存在排序错误,给出错误提示
                        else {
                            throw new ArrayException("findLastTimeMatchSchedule的for循环error,或是队列中排序错误");
                        }
                    }
                }
            }

            //此时队列中不存在与传入数据相同的元素,故进行插入
            return end + 1;
        }
    }
}
