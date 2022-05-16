package team.ItemSchedule;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 用途:
 * 个人测试使用
 */
public class TestItem
{
    public static void main(String[] args) throws ParseException
    {

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2002,11,28);
        System.out.println(calendar1.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日E");
        String format = simpleDateFormat.format(calendar1.getTime());
        System.out.println(format);

        calendar1.set(10002,11,36);
        System.out.println(calendar1.getTimeInMillis());
    }
}
