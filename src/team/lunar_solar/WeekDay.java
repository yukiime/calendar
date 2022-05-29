package team.lunar_solar;

public class WeekDay {
    /**
     *
     * @param year
     * @return  centry
     */
    public  static  int  compute_c(int  year) {
        int  c=year;
        c=c/100;
        return  c;
    }
    /**
     *
     * @param year
     * @return    year的后两位
     */
    public static int  compute_y(int  year) {
        int  y=year;
        y=y%100;
        return   y;
    }
    /**
     *
     * @param month
     * @return   month的转换结果（某年的1，2月要看作上一年的13，14月）
     */
    public  static  int  compute_m(int  month) {
        int  m=month;
        if(m>=3&&m<=12) {
            m=m;
        }
        if(m==1||m==2) {
            m=m+12;
        }
        return  m;
    }
    /**
     *
     * @param day
     * @return    day
     */
    public  static  int  compute_d(int  day) {
        int  d=day;
        return  d;
    }
    /**
     *
     * @param year
     * @param month
     * @param day
     * @return    星期的转换结果
     */
    public  static  String  compute_weekday(int  year,int  month,int  day) {
        int  w=0;
        int  y=compute_y(year);
        int  c=compute_c(year);
        int  m=compute_m(month);
        int  d=compute_d(day);
        String  weekday=" ";
        w=y+y/4+c/4-2*c+26*(m+1)/10+d-1;
        switch(w%7) {
            case  0:weekday="星期日";break;
            case  1:weekday="星期一";break;
            case  2:weekday="星期二";break;
            case  3:weekday="星期三";break;
            case  4:weekday="星期四";break;
            case  5:weekday="星期五";break;
            case  6:weekday="星期六";break;
            default:weekday="输入数据有误";break;
        }
        return  weekday;
    }

		/*
	public static void main(String[] args) {
		System.out.println(compute_weekday(2049,10,1));
		System.out.println(compute_weekday(2022,5,24));
		System.out.println(compute_weekday(2022,4,20));
		System.out.println(compute_weekday(2003,4,22));
		System.out.println(compute_weekday(2011,10,11));

	}*/

}
