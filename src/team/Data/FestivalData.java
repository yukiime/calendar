package team.Data;
import team.Item.ItemSchedule.CommemorationDay;
import team.Item.ItemSchedule.Festival;

import java.util.ArrayList;

public class FestivalData
{
    //存储festival和commemorationDay
    public static ArrayList<CommemorationDay> commemorationDays_festival =new ArrayList<CommemorationDay>();
    //用树状数组维护,第i位代表i+1月份的第一个日期
    public static int[] monthIndex = new int[17];
    //只增不长 维护长度
    public static int len = 53;

    //将一个二进制数的所有高位一都去掉，只留下最低位的1
    public static int lowBit(int k)
    {
        return k & (-k);
    }

    //给x下标元素添加a
    public static void treeAdd(int x, int a)
    {
        while (x <= 12) //小于原来数组的总个数
        {
            monthIndex[x] = monthIndex[x] + a;   //都加上a
            x = x + lowBit(x); //找他的父节点
        }
    }

    //询问前缀和
    public static int sum(int x)
    {
        int preSum = 0; //前缀和

        while (x > 0)
        {
            preSum += monthIndex[x];
            x = x - lowBit(x);
        }

        return preSum;
    }

    //询问区间和
    public static int segSum(int l, int r)
    {
        int segSum;
        segSum = sum(r) - sum(l);
        return segSum;
    }

    /**
     * 初始化节日
     * 也可以当做清空纪念日的操作
     */
    public static void FestivalDataBase()
    {
        Festival festival = new Festival(1,1,1,"元旦节"); //0
        commemorationDays_festival.add(festival);
        festival = new Festival(2,2,7,"二七纪念日"); //1
        commemorationDays_festival.add(festival);
        festival = new Festival(3,3,8,"三八妇女节"); //2
        commemorationDays_festival.add(festival);
        festival = new Festival(4,3,12,"植树节");
        commemorationDays_festival.add(festival);
        festival = new Festival(5,3,14,"国际数学日");
        commemorationDays_festival.add(festival);
        festival = new Festival(6,3,21,"世界森林日");
        commemorationDays_festival.add(festival);
        festival = new Festival(7,3,22,"世界水日");
        commemorationDays_festival.add(festival);
        festival = new Festival(8,4,2,"国际儿童图书节"); //7
        commemorationDays_festival.add(festival);
        festival = new Festival(9,4,7,"世界卫生日");
        commemorationDays_festival.add(festival);
        festival = new Festival(10,4,22,"世界地球日");
        commemorationDays_festival.add(festival);
        festival = new Festival(11,4,23,"世界读书日");
        commemorationDays_festival.add(festival);
        festival = new Festival(12,4,24,"中国航天日");
        commemorationDays_festival.add(festival);
        festival = new Festival(13,4,25,"全国儿童预防接种宣传日");
        commemorationDays_festival.add(festival);
        festival = new Festival(14,5,1,"劳动节"); //13
        commemorationDays_festival.add(festival);
        festival = new Festival(15,5,4,"青年节");
        commemorationDays_festival.add(festival);
        festival = new Festival(16,5,9,"Calendar立项日");
        commemorationDays_festival.add(festival);
        festival = new Festival(17,5,12,"全国防灾减灾日");
        commemorationDays_festival.add(festival);
        festival = new Festival(18,5,16,"国际和平共处日");
        commemorationDays_festival.add(festival);
        festival = new Festival(19,5,19,"中国旅游日");
        commemorationDays_festival.add(festival);
        festival = new Festival(20,5,30,"五卅纪念日");
        commemorationDays_festival.add(festival);
        festival = new Festival(21,6,1,"儿童节"); //20
        commemorationDays_festival.add(festival);
        festival = new Festival(22,6,4,"受侵略戕害的无辜儿童国际日");
        commemorationDays_festival.add(festival);
        festival = new Festival(23,6,5,"环境日");
        commemorationDays_festival.add(festival);
        festival = new Festival(24,6,6,"全国爱眼日");
        commemorationDays_festival.add(festival);
        festival = new Festival(25,6,8,"全国海洋宣传日");
        commemorationDays_festival.add(festival);
        festival = new Festival(26,6,17,"世界防治荒漠化和干旱日");
        commemorationDays_festival.add(festival);
        festival = new Festival(27,6,22,"中国儿童慈善活动日");
        commemorationDays_festival.add(festival);
        festival = new Festival(28,6,23,"国际奥林匹克日");
        commemorationDays_festival.add(festival);
        festival = new Festival(29,6,25,"全国土地日");
        commemorationDays_festival.add(festival);
        festival = new Festival(30,7,1,"建党节"); //29
        commemorationDays_festival.add(festival);
        festival = new Festival(31,7,11,"世界人口日");
        commemorationDays_festival.add(festival);
        festival = new Festival(32,7,7,"七七抗战纪念日");
        commemorationDays_festival.add(festival);
        festival = new Festival(33,7,20,"全国特奥日");
        commemorationDays_festival.add(festival);
        festival = new Festival(34,7,28,"世界肝炎日");
        commemorationDays_festival.add(festival);
        festival = new Festival(35,7,30,"国际友谊日");
        commemorationDays_festival.add(festival);
        festival = new Festival(36,8,1,"建军节"); //35
        commemorationDays_festival.add(festival);
        festival = new Festival(37,8,12,"国际青年节");
        commemorationDays_festival.add(festival);
        festival = new Festival(38,8,15,"日本无条件投降日");
        commemorationDays_festival.add(festival);
        festival = new Festival(39,8,16,"国际和平共处日");
        commemorationDays_festival.add(festival);
        festival = new Festival(40,8,26,"全国律师咨询日");
        commemorationDays_festival.add(festival);
        festival = new Festival(41,9,3,"世界反法西斯战争胜利纪念日"); //40
        commemorationDays_festival.add(festival);
        festival = new Festival(42,9,5,"中华慈善日");
        commemorationDays_festival.add(festival);
        festival = new Festival(43,9,10,"教师节");
        commemorationDays_festival.add(festival);
        festival = new Festival(44,9,18,"九一八纪念日");
        commemorationDays_festival.add(festival);
        festival = new Festival(45,9,30,"烈士纪念日");
        commemorationDays_festival.add(festival);
        festival = new Festival(46,10,1,"国庆节"); //45
        commemorationDays_festival.add(festival);
        festival = new Festival(47,10,17,"国家扶贫日");
        commemorationDays_festival.add(festival);
        festival = new Festival(48,11,8,"世界记者日"); //47
        commemorationDays_festival.add(festival);
        festival = new Festival(49,11,16,"国际宽容日");
        commemorationDays_festival.add(festival);
        festival = new Festival(50,12,1,"世界艾滋病日"); //49
        commemorationDays_festival.add(festival);
        festival = new Festival(51,12,4,"国家宪法日");
        commemorationDays_festival.add(festival);
        festival = new Festival(52,12,13,"南京大屠杀死难者国家公祭日");
        commemorationDays_festival.add(festival);
        festival = new Festival(53,12,26,"国际残疾人日");
        commemorationDays_festival.add(festival);

        //monthIndex = new int[]{0, 1, 2, 7, 13, 20, 29, 35, 40, 45, 47, 49};

        treeAdd(1,1);
        treeAdd(2,1);
        treeAdd(3,5);
        treeAdd(4,6);
        treeAdd(5,7);
        treeAdd(6,9);
        treeAdd(7,6);
        treeAdd(8,5);
        treeAdd(9,5);
        treeAdd(10,2);
        treeAdd(11,2);
        treeAdd(12,4);
    }

}
