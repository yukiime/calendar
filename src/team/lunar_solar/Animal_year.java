package team.lunar_solar;

public class Animal_year {
    /**
     * 支持转换的最小农历年份
     */
    public static final int MIN_YEAR = 1900;

    /**
     * 支持转换的最大农历年份
     */
    public static final int MAX_YEAR = 2099;
    /**
     *
     * @param year
     * @param month
     * @param monthDay
     * @return    传回阳历year年month月monthDay日的生肖
     */

    static  public String  Solar_Animal(int year,int month,int monthDay){
        if (year < MIN_YEAR || year > MAX_YEAR || month < 1 || month > 12
                || monthDay < 1 || monthDay > 30) {
            throw new IllegalArgumentException(//若输入的参数超过上下限，则抛出不合法参数异常
                    "Illegal lunar date, must be like that:\n\t" +
                            "year : 1900~2099\n\t" +
                            "month : 1~12\n\t" +
                            "day : 1~30");
        }

        int[] lunardate=LS.solarToLunar(year,month,monthDay);
        return  Lunar_Animal(lunardate[0],lunardate[1],lunardate[2]);
    }
    /**
     *
     * @param year
     * @param month
     * @param monthDay
     * @return     传回阴历year年month月monthDay的生肖
     */
    static  public  String  Lunar_Animal(int year,int month,int monthDay) {
        final String[] Animals = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
        return Animals[(year - 4) % 12];
    }


	/*public static void main(String[] args) {
		System.out.println(Solar_Animal(2000, 1, 9));
		System.out.println(Lunar_Animal(2111,6,3));

	}*/

}
