package team.Item.ItemSchedule;
import team.Data.FestivalData;
import team.Data.ScheduleData;
import team.Item.ItemsWork.CreateSth;
import team.Item.ItemsWork.DeleteSth;
import team.Item.ItemsWork.FindDaySth;
import team.Projectexception.ArrayException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 * 用途:
 * 个人测试使用
 */
public class TestItem
{
    public static void main(String[] args) throws ParseException, ArrayException {

        /**
        SimpleDateFormat("yyyy年MM月dd日E");
        String format = simpleDateFormat.format(calendar1.getTime());
        System.out.println(format);

        calendar1.set(10002,11,36);
        System.out.println(calendar1.getTimeInMillis());

         for (int i = 0; i < 12; i++)
         {
         System.out.println(arrayList.get(a[i]).getMonth());

         }
         */


        CreateSth.createSchedule(56562626262323131L,"测试1");
        CreateSth.createSchedule(5656263131L,"测试2");
        CreateSth.createSchedule(5656263131L,"测试3");
        CreateSth.createSchedule(4646222L,"测试4");
        CreateSth.createSchedule(111L,"测试5");
        CreateSth.createSchedule(999999999L,"测试6");

        FestivalData.FestivalDataBase();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2022,5,1);
        long time = calendar.getTimeInMillis();

        CreateSth.createCommemorationDay(time,"小明节");

        ArrayList<CommemorationDay> arrayList = FindDaySth.findCommemorationDays_festival(time);

        System.out.println(arrayList.size());

        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i).getContent());
        }






    }
}
