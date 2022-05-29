package team.Item.ItemsWork;
import team.Projectexception.*;
import team.Item.ItemSchedule.SolarTerm;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * 两个函数
 * 1.获取当日的时间，返回当日的节气，若无则返回空
 * 2.获取当年的时间,寻找到当年所有的节气并返回
 */
public class FindSolarTerm
{
    /**
     * 获取当日的时间，返回当日的节气，若无则返回空
     * @param time 当天的时间戳
     * @return 当天的节日信息
     */
    public static String daySolarTerm(long time) throws ArrayException
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int year = calendar.getWeekYear(); //年份
        int month = calendar.get(Calendar.MONTH) + 1; //月份
        int day = calendar.get(Calendar.DATE); //月中的第几号

        ArrayList<SolarTerm> arrayList = yearSolarTerm(year); //存储了当年所有节气的list

        if (arrayList.size() == 0)
        {
            throw new ArrayException("节气列表为空");
        }
        else
        {
            for(SolarTerm solarTerm : arrayList)
            {
                if(solarTerm.getYear() == year && solarTerm.getMonth() == month && solarTerm.getDay() == day)
                {
                    return solarTerm.getContent();
                }
            }

            return "null";
        }
    }

    /**
     * 获取当年的时间,寻找到当年所有的节气并返回
     * @param year 年份
     * @return arrayList 存储了当年所有节气的list
     */
    public static ArrayList<SolarTerm> yearSolarTerm(int year)
    {
        ArrayList<SolarTerm> arrayList = new ArrayList<>();

        /**
         * 寻找到这一年的节气
         * 添加到arraylist里面
         */

        return arrayList;
    }
}
