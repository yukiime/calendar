# 详细设计

## 概要设计

- 程序主要采用 MVC 框架来实现前后端数据交互。
在 View 和 Model 之间预埋了 Controllers，如类`CreateSth`，`DeleteSth`，来实现业务逻辑。
- 在面对不断累积的用户日程数据，检索当日的日程变得困难。
我们使用**树状数组**和**二分查找**来维护数据，提高程序运行效率。
- 有完整的异常处理系统。
`Alert`类用于处理异常，以弹窗形式，中文汉字给予用户友好的提示。
同时封装5个异常类，同时使用大量`try-catch`语句，旨在减少程序崩溃可能性。
- 设计`Context`上下文类，便于不同组件读取和改变全局数据。
- 灵活使用二次封装和泛型技术达到高效开发目的。

## 记事信息操作设计

在数据类中创建 2 个存储了 `Schedule` 元素的按`creatTime`大小排序的`ArrayList`

1. 存储周期不重复的`Schedule`
2. 存储周期重复的`Schedule`

### 记事信息的创建

调用`create`类方法（如
`createSchedule(long createTime, String content, boolean isRepeat, short repeatCode, short order)`）
可以按传递的数据创建一个新的实例对象，并进行插入操作。

其中设计`createTime`：只精确到天的创建项目的当日的时间戳

设计`id`：对应实例的唯一的身份认证信息，通过

```java
private static int len;
public static int getLen() {
    return len;
}
public static void addLenOne() {
    ScheduleData.len += 1;
}
```

来维护长度

**插入操作**会根据传入的`isRepeat`值判断需要插入的`list`
随后调用相应的`find方法(long createTime)`
方法，根据二分寻找到合适的插入位置，规定这个插入位置使得在插入时永远插入到
`list`中具有相同`createTime`的元素的末尾，即插入位置的后一个元素的
`createTime`属性永远大于插入的元素的`createTime`属性。

调用`find方法(long createTime)`
来寻找到插入位置后会返回该插入位置的下标，该方法使用二分搜索。

首先进行一次 if 语句构成的特判

```java
if (list为空 || 末尾元素的创建时间小于等于需要插入的元素的创建时间 ) {
    return 末尾下标;
}
else if ( 首元素的创建时间大于需要插入的元素的创建时间 ) {
    return 0;
}
```

随后进行二分搜索

```java
while (start <= end) {
    mid = (start + end) / 2; // 取中间值
    if (寻找到了与传入数据相同的元素) {
        // 显然其下标为mid，以该下标为起点,向后搜索,
        for (int i = mid; i <= end; i++) {
            if (向后搜索,直到出现拥有不同数据的元素)
                return 该下标;
            else if (查询下标的数据比传入数据大) {
                // 下一个搜索区段的尾下标更改为该下标-1
            }
                // 在队列中寻找到了与传入数据相同的元素,其下标为mid,无须记录
            else if (查询下标的数据比传入数据小) {
                // 下一个搜索区段的首下标更改为该下标+1
            }
            // 此时队列中不存在与传入数据相同的元素,故进行插入
            return end + 1;
        }
    }
}
```

### 记事信息的删除

调用`delete方法（int id, long createTime, boolean isRepeat）`来将需要删除的项目进行删除

**删除操作**会根据传入的`isRepeat`值判断需要进行寻找的`list`随后进行搜索

该搜索同样使用二分搜索

首先为了效率和安全性进行两次`if语句`的特判

```java
if (list为空) {
    throw new 异常("ArrayList已经为空,不能再进行删除操作");
}
if ( 末尾元素的创建时间小于需要删除的元素的创建时间 ) {
    throw new 异常("传入的createTime超出当前队列的最大值,不存在该数据");
}
else if (首元素的创建时间大于需要插入的元素的创建时间 ) {
    throw new 异常("传入的createTime小于当前队列的最小值,不存在该数据");
}
```

随后进行二分搜索

```java
while (start <= end) {
    mid = (start + end) / 2; // 取中间值
    if (寻找到了与传入数据相同的元素) {
        // 记录下标 向前向后搜索
        // 要注意下标不越界
        while (向后搜索) {
            if (id值相同) {
                // 删除该元素; 并返回；
            }
        }
        // 要注意下标不越界
        while (向前搜索) {
            if (id值相同) {
                // 删除该元素; 并返回；
            }
        }
    }
    else if (查询下标的数据比传入数据大) {
        // 下一个搜索区段的尾下标更改为该下标-1
    }
    else  if (查询下标的数据比传入数据小) {
        // 下一个搜索区段的首下标更改为该下标+1
    }
}
```

如果到此还没有返回，即是搜索后没有发现list中有和需要删除项目具有相同特征属性的元素，返回list中不存在需要删除的元素的提示信息

### 记事信息的修改

先调用`delete方法`将该项目删除，随后根据修改信息调用`create方法`创建一个新的项目

## 节日/纪念日设计

在数据类中创建 1 个存储了 `CommemorationDay` 和`Festival`元素，并按`month`和`day`大小排序的`ArrayList`

该`ArrayList`的月份节点通过数据类中的**树状数组**`int[] monthIndex`维护和问询。

维护操作如下:

```java
int lowBit(int k) {
    return k & (-k);
}

void treeAdd(int x, int a) {
    while (数组长度) {
        // 对于父节点都进行添加
        // lowbit操作寻找父节点
    }
}
```

询问操作如下:

```java
int sum(int x) {
    preSum = 0;
    while (x > 0) {
        // 逐个添加子节点的值
    }
    return preSum;
}

int segSum(int l, int r) {
    // 两次前缀和相减
    return segSum;
}
```

### 节日/纪念日的创建

调用`create`类方法（如
`createCommemorationDay(long createTime, String content)`）
可以按传递的数据创建一个新的实例对象，并进行插入操作。

其中设计的`createTime`、`id`与记事信息类相同。

插入操作中会对重复类型进行判断

1. 对于年重复类型的实例对象会直接按照传入的`time`数据根据`Calendar类`
的方法获取月份数据，并根据树状数组`monthIndex`的节点问询操作索引到`list`中的下标。

    ```java
    start = 树状数组.sum(新建实例.getMonth() - 1); // 该月份的第一天
    end = 树状数组.sum(新建实例.getMonth()) - 1; // 该月份的最后一天
    ```

    随后在这个区间内进行二分搜索，寻找到与`day`值相符合的插入位置

    规定此插入位置一定处在具有相同`day`值的元素的最末尾

    ```java
    while (start <= end) {
        mid = (start + end) / 2; // 取中间值
        if (寻找到了与传入数据相同的元素) {
            // 显然其下标为mid，以该下标为起点,向后搜索,
            for (int i = mid; i <= end; i++) {
                if (向后搜索,直到出现拥有不同数据的元素) {
                    return 该下标;
                }
            }
        }
        else if (查询下标的数据比传入数据大) {
            // 下一个搜索区段的尾下标更改为该下标-1
        }
        // 在队列中寻找到了与传入数据相同的元素,其下标为mid,无须记录
        else if (查询下标的数据比传入数据小) {
            // 下一个搜索区段的首下标更改为该下标+1
        }
        // 此时队列中不存在与传入数据相同的元素,故进行插入
        return end + 1;
    }
    ```

2. 对于月重复类型的实例对象会直接按照传入的`time`数据根据`Calendar类`
的方法获取`day`数据，并直接获取树状数组`monthIndex`的“13 月”节点，
在`list`的末尾对一个月重复元素的区间段进行二分搜索，该过程与上同

### 节日/纪念日的删除

调用`delet`类的方法，通过二分搜索或遍历查询寻找到元素并删除

### 节日/纪念日的修改

先调用`delete方法`将该项目删除，随后根据修改信息调用`create方法`创建一个新的项目

## 二次封装

考虑到万年历中组件的复杂性，我们决定二次封装常用的组件来减少代码复用，提高代码可读性，同时方便同步组员合作时的代码规范

- `NewInput`

1. 将输入框前的提示标签`NewLable`和输入框`JTextField`打包在一个`Jpanel`中，使得输入组件在窗体中有较好的兼容性，维护性。
2. 封装后暴露出一些方法来获取以及操纵输入框中的内容，以及控制可编辑性。

- `NewLabel`

1. 在`JLabel`之上增加富文本支持，将富文本格式信息参数化，能快速简洁地定制独特的标签内容

    ```java
    // 添加 h1 大小的文字
    setContent("h1", String.valueOf(solarDate));
    // 添加普通文本，高亮类型为 danger
    setContent("text danger", "这个日子已经过去 " + -num + " 天了");
    ```

2. 具体富文本格式（以字号大小为例）我们参照了 Ant Design Typography

```java
switch (props[0]) {
 case "h1":
     setFont(new Font("黑体", Font.BOLD, 38));
     break;
 case "h2":
     setFont(new Font("黑体", Font.BOLD, 30));
     break;
 case "h3":
     setFont(new Font("黑体", Font.BOLD, 24));
     break;
 case "h4":
     setFont(new Font("黑体", Font.BOLD, 20));
     break;
 case "text":
     setFont(new Font("黑体", Font.PLAIN, 14));
     break;
 default:
     setFont(new Font("黑体", Font.PLAIN, 14));
```

## 泛型

在日程列表中，数据源有三种类`Schedule` ，`Festival`，`commemorationDay`，为了区分渲染，构造了两种视图类`ScheduleWrapper`，`CommemorationDayWrapper`，两者都继承于`ItemWrapper`。在监听操作，和在新窗口中修改已有日程传递参数时，考虑方法重载和具体实现，决定传递数据源对象的引用，于是使用到了泛型技术。

```java
class ScheduleWrapper extends ItemWrapper<Schedule> {
    public ScheduleWrapper(Schedule ref) {
        super(ref.getContent());
        this.ref = ref;
        this.addMouseListener(new ms<ScheduleWrapper, Schedule>());
        this.setStyle(ref.getOrder());
    }
}

class ms<T1 extends ItemWrapper<T2>, T2> implements MouseListener {
    public void mouseReleased(MouseEvent e) {
        @SuppressWarnings("unchecked")
        T1 wrapper = (T1) e.getSource();
        if (!(wrapper.getRef() instanceof Festival)) {
            ModifyFrame<T2> modifyFrame = new ModifyFrame<T2>(wrapper.getRef());
        }
    }
}
```

## 业务逻辑算法

### 阴阳历法转换

```java
创建私有数组常量 LUNAR_INFO = {}
/**
 * 用来表示1900年到2099年间农历年份的相关信息，共24位bit的16进制表示，其中：
 * 1. 前4位表示该年闰哪个月；
 * 2. 5-17位表示农历年份13个月的大小月分布，0表示小，1表示大（大月30天，小月29天）；
 * 3. 最后7位表示农历年首（正月初一）对应的公历日期。
 * 倒数第7位到倒数第6位表示对应公历年的月份（范围1-3），最后五位表示对应公历年的月份(1-31)
 * 以2014年的数据0x955ABF为例说明：
 * 1001 0101 0101 1010 1011 1111
 * 闰九月 1月31号
 */

 /**
  * 将农历日期转换为公历日期
  *
  * @param year                               农历年份
  * @param month                              农历月
  * @param monthDay                           农历日
  * @param isLeapMonth                        该月是否是闰月
  * @return 返回农历日期对应的公历日期，year0, month1, day2.
  */
public static final int[] lunarSolar(){
    dayoffset：用来储存被计算日期距离year年的阳历1月1日的天数;
    创建整型变量leapMonth;
    创建整型变量i;
    if（year或month或monthDay超过上下限）{
        抛出不合法参数异常并提示用户正确的输入范围;
    }
    dayoffset <- 农历year年正月初一对应的公历日并 -1;
    //由于日期跨度不能将首尾两天都计入，所以要-1
    //说明一月的天数尚未被计入dayoffset
    if (农历正月初一对应的公历日期是在2月) {
        //将1月的31天计入dayoffset
        dayOffset <- dayOffset + 1 ;
    }

    //遍历，取出每个月的天数，直到要计算的月的前一个月（不包括用户输入的那个月
    for (i = 1 to month - 1 by 1)
        if(农历是小月)
            dayOffset <- dayOffset + 29;
        else
            //取出的那个月是大月
            dayOffset <- dayOffset + 30;
    //加上用户输入的那个月的天数
    dayOffset <- dayOffset + monthDay;
    leapMonth <- 该年闰月月份，若无闰月，则为0;
    if (这一年有闰月) {
        //若当年有闰月，那么前面遍历计数是会少算最后一个整月的天数，所以此处用来加上最后一个整月
        if(month是闰月或是闰月的后一个月) {
            if(month是小月)
                dayOffset <- dayOffset+ 29 ;
        }
        else {
            dayOffset <- dayOffset+ 30 ;
        }
    }

    //说明阴历year年month月monthDay距离阳历year年的阳历1月1日已经有了超过一年的时间（这种情况出现在农历年末，阳历年初）
    if(dayOffset超过366或者在阳历非闰年超过365){
        //此时对应的阳历年份比阴历大1
        year -> year + 1 ;
        if (year-1年是阳历闰年) {
            dayOffset -> dayOffset - 366 ;
        }
        else {
            // year-1年不是阳历闰年
            dayOffset -> dayOffset - 365 ;
        }
    }

    创建solarInfo整型数组，分别用来保存转换后的年份，月份，日期
    for(i = 1 to  12  by  1) {
        iPos <- 公历i月前的天数;
        if (year年为阳历闰年且i为2月之后的月份) {
            //因为公历闰年二月有29天
            iPos  <- iPos+1;
        }
        if (当天为公历闰年2月29日) {
            //特殊情况特殊考虑，可直接得出月份和日期
            solarInfo[1] <- i;
            //当月就是2月
            solarInfo[2] <- dayOffset - 31;
            //当天日期即为距离阳历1月1日的日期减去1月的31天
            结束当前循环;
        }
        if (iPos >= dayOffest) {
            //该月总前的天数大于等于该日距阳历1月1日的日期，说明i月即为当前月
            solarInfo[1] <- i;(i为遍历到的月份数)
            iPos <- 记录当前月的前几个月的天数;（不包括当前月）
            if (当年为闰年且该月为阳历2月之后的月) {
                //iPos重新赋值，所以对于是否是阳历闰年的判断要重新进行
                iPos <- iPos+1;
                //当前月的前几个月天数要加一天
            }
            if (dayOffset > iPos) {
                //说明该日距阳历1月1日的日期大于iPos
                solarInfo[2] <- 距离阳历1月1日的天数—当前月的前几个月的天数之和;
            } else if (dayOffset == iPos) {
                if (处于阳历闰年2月)
                    solarInfo[2] <- 3月前的天数-2月前的天数+1;
                else
                    //非阳历闰年2月
                    solarInfo[2] <- 3月前的天数-2月前的天数;
            } else {
            //dayoffset < iPos
            solarInfo[2] <- dayOffset;
            }
            结束当前循环
        }
    }
    //转换后得到的阳历年份
    solarInfo[0] <- year  ;
    return solarInfo数组;
}


/**
 * 将公历日期转换为农历日期，且标识是否是闰月
 *
 * @param year
 * @param month
 * @param monthDay
 * @return 返回公历日期对应的农历日期，year0，month1，day2，leap3
 */
public static final int[] solarToLunar(year, month, monthDay)
    创建拥有4个整型变量的lunarDate类数组; // 用来储存转换后的农历日期信息
    // lunarDate[0]:用来存储阴历年
    // lunarDate[1]:用来存储阴历月
    // lunarDate[2]:用来存储阴历日
    // lunarDate[3]:用来存储此阴历月是否是闰月
    创建Date类对象baseDate <- 阳历1900年1月31日;
    创建Date类对象objDate <- 用户输入的阳历年,月,日;
    // getTime()获得时间对象对应的日期对象，返回一个Date类的对象
    // GregprianCalender(年，月，日)
    // JAVA中月份从0开始，即0表示1月

    offset <- 用户输入的阳历日期距离阳历1900年1月31日有多少天;
    //86400000L是一天的毫秒数，除以它后即使两个时间之间隔了多少天

    iYear, dayOfYear <- 0  ;
    // iYear最终结果是农历的年份

    for (iYear MIN_YEAR to MAX_YEAR 并且 offset > 0 by 1) {
        daysOfYear <- iYear年的天数 ;
        offset <- offset - daysOfYear ;
        //遍历，每历过一年，offset就减去该农历年的天数
    }

    if(offset < 0) {
        //表示遍历时已经历到过所在那年，且for循环中会将年份多加1
        offset <- daysOfyear;
        iYear <- iYear -1;
        //此时得到的就是所在农历年份
    }

    // 所在阴历年份
    lunarDate[0] <- iYear;
    //闰月范围1—12，若阴历iYear年无闰月，则为0
    leapMonth <- 阴历iYear年的闰月月份;
    //最终用来记录对应的阴历月是否是闰月
    isLeap <- false;
    //最终用来记录对应阴历月
    iMonth <- 0;
    //最终用来记录对应的阴历日
    daysOfMonth <- 0;

    for (iMonth 1 to 13 并且 offset > 0 by 1) {
        // 用当年的天数offset,逐个减去每月（农历）的天数，求出当天是本月的第几天
        daysOfMonth <-  iYear年的iMonth 月的天数;
        offset <- offset - daysOfMonth  ;
    }

    if (iMonth - 2 为闰月) {
        //对是否是闰月的初步判断,符合条件的iMonth可能为闰月，也可能为闰月后面的月份
        isLeap <- true;
    }
    if (当年有闰月并且iMonth月超过(闰月月份+1)) {
        iMonth <-iMonth-1;
        if(iMonth是当年闰月) {
            isLeap <-  true;
        }
    }
    //说明此时遍历过头了，需要往前退一个月
    if(offset < 0) {
        offset <- offset  + daysMonth  ;
        iMonth <- iMonth-1 ;
    }
    //对是否是闰月的二次判断
    if(iMonth不等于闰月月份) {
        isLeap <- false  ;
    }
    将转换结果存入 lunarDate[]
    return lunarDate数组;
}

/**
 * 传回农历 year年的总天数
 *
 * @param year 将要计算的年份
 * @return 返回传入农历年份的总天数
 */
private static int daysInLunarYear() {
    //i为随意赋值，目的只是初始化变量i
    i <- 348 ;
    //348为12个小月的天数和
    sum <- 348 ;
    if (year年有闰月) {
        sum <- 377 ;
        //377为13个小月的天数
    }
    monthInfo <- year年每个月大小月的信息 ;
    for(i是monthInfo里面的每一位) {
        //读取每个月的大小月信息
        if(i不等于0)
            //当前读到的月是一个大月，天数+1
            sum <-  sum +1 ;
    }
    return 该年的天数;
}

/**
 * 传回农历 year年month月的一个月天数，总共有13个月包括闰月
 *
 * @param year  将要计算的年份
 * @param month 将要计算的月份
 * @return 传回农历 year年 month 月的天数
 */
public int daysInLunarMonth(year, month) {
    if(month月是大月)
        返回 29;
    else
        返回 30 ;
}

/**
 * 传回农历 year年闰哪个月 1-12 , 没闰传回 0
 *
 * @param year 将要计算的年份
 * @return 传回农历 year年闰哪个月1-12, 没闰传回 0
 */
public static int leapMonth(year) {
    返回农历 year 年闰哪个月1-12, 没闰传回 0  ;
}
```

### 节气查询

> 节气具体计算算法引用自：[java实现二十四节气计算 - 百度文库](https://wenku.baidu.com/view/bf834b055a0102020740be1e650e52ea5518ce36.html)

主要由两个函数`FindSolarTerm`，`daySolarTerm`完成

1. 获取当日的时间，返回当日的节气，若无则返回空
2. 获取当年的时间,寻找到当年所有的节气并返回

```java
public class FindSolarTerm {

    private static final double D = 0.2422; // 是公式的一个参数
    private final static Map<String,Integer[]> INCREASE_OFFSETMAP
        = new HashMap<String, Integer[]>(); // +1偏移
    private final static Map<String,Integer[]> DECREASE_OFFSETMAP
        = new HashMap<String, Integer[]>(); // -1偏移
    /**24节气**/
    public static enum SolarTermsEnum {
        // 24节气英文的枚举;
    }
    static {
        // 在 JVM 初始化时期，就在HashMap中将节气名称与对应信息联系起来
    }

    /**
     * 定义一个二维数组
     * 第一维数组存储的是20世纪的节气C值，
     * 第二维数组存储的是21世纪的节气C值,0到23个
     * 依次代表立春、雨水...大寒节气的C值
     */
    private static final [][]CENTURY_ARRAY = {{20世纪的C值}, {21世纪的C值}};

    public static int getSolarTermNum(年份,节气的名称) {
        centuryValue <- 0 ; //节气的世纪值，每个节气的每个世纪值都不同
        name <- name.trim().toUpperCase();
        ordinal <- SolarTermsEnum.valueOf(name).ordinal();
        centuryIndex <- -1;
        if(year是20世纪)
            centuryIndex <- 0;
        else if(year是21世纪)
            centuryIndex <- 1;
        else {
            抛出RuntimeException异常
        }
        centuryValue <- CENTURY_ARRAY[][];
        dateNum <- 0;
    }
    /**
    * 计算 num =[Y*D+C]-L这是传说中的寿星通用公式
    * 公式解读：年数的后2位乘0.2422加C(即：centuryValue)取整数后，减闰年数
    */
    y <- year % 100; // 步骤1:取年分的后两位数
    if (year年为闰年) {
        if (ordinal 等于 SolarTermsEnum.XIAOHAN.ordinal() 或者ordinal 等于 SolarTermsEnum.DAHAN.ordinal() 或者 ordinal 等于 SolarTermsEnum.LICHUN.ordinal() 或者 ordinal 等于 SolarTermsEnum.YUSHUI.ordinal()) {
            y <- y - 1;
            /**
             * 注意：凡闰年3月1日前闰年数要减一，即：L=[(Y-1)/4]
             * 因为小寒、大寒、立春、雨水这两个节气都小于3月1日
             * 所以 y = y - 1
             */
        }
    }
    //步骤3，使用公式[Y*D+C]-L计算
    dateNum <- (int) (y * D + centuryValue) - (int) (y / 4);
    //步骤4，加上特殊的年分的节气偏移量
    dateNum <- specialYearOffset(year, name);
    return  dateNum ;
}
```

```java
// 特例,特殊的年分的节气偏移量,由于公式并不完善，所以算出的个别节气的第几天数并不准确，在此返回其偏移量
public static int specialYearOffset(年份，节气名称){
    offset <- 0 ;
    offset <- offset + DECREASE_OFFSETMAP偏移量;
    offset <- offset + INCREASE_OFFSETMAP偏移量 ;
    return  offset ;
}

/**
* @param Map<String,Integer[]> map
* @param year 年份
* @param name 节气名称
* @param offset
* @return 返回off
*/
public static int getOffset(偏移量映射，年份，节气名称){
    off <- 0 ;
    years[] <- map.get(name);
    if (year 非空)
        for(i 0 to year by 1) {
            if(i 等于 year) {
                off <- offset ;
                break;
            }
        }
    return off;
}
```

```java
// 获取当日的时间，返回当日的节气，若无则返回空
public static String daySolarTerm(当天的时间戳){
    calendar <- 当前时间，使用Calendar类获取;
    calendar.setTimeInMillis(time);
    //根据给定参数，得到待查询的一个时间点
    year <- 该时间点对应的年份 ;
    month <-该时间点对应的月份;
    day <- 该时间点对应的日;

    // 获取当前年所有的节气
    arrarList <- yearSolarTerm(year)
    for(arrayList里面的每一个SolarTerm对象)
        if (solarTerm数组中的节气的年月日与输入时间点的年月日相同)
            return 该节气的名称;

    // 运行至此找不到该日节气，返回null
}
```

```java
// 获取当年的时间,寻找到当年所有的节气并返回
public static yearSolarTerm(当年年份) {
    // 创建ArrayList类型的数组用于转存数据;
    // 创建SolarTerm类型数组用于零时存放当年的全部节气数据

    // 寻找到这一年的节气，添加到solarterm里面
    solarterm[i] <- 当年的第 i 个节气年月日以及名称
    // ... 一共 24个节气

    for(i 0 to 23 by 1)
        arrayList[i] <- solarterm[i] ;
        //将solarterm里面的数据转到arrayList里面

    return  存储当年所有节气的list;
}
```

### 生肖查询

```java
public class Animal_year {
    public static String queryAnimal(阴历日期或者阳历日期) {
        // 若传入阳历日期，将阳历日期转换成银历日期
        // Lunardate[] = LunarSolar.solarToLunar(阳历日期)
        // 通过阴历日期计算生肖年
        Animals <- 鼠，牛，虎，兔，龙，蛇，马，羊，猴，鸡，狗，猪;
        // 年份减去4再对12取余后得到的余数即为对应生肖数组的索引
        return 当天对应的生肖;
    }
}
```

### 车辆尾号限行查询

```java
public static String computeVehicle(int y, int m, int d) {
    // 根据传入的年月日信息计算出星期几
    w = y + y / 4 + c / 4 - 2 * c + 26 * (m + 1) / 10 + d - 1;
    // 此步根据公式所得
    switch(用于表示星期的数字 w) {
        // 根据星期几和本地法规得出具体尾号限行信息
    }
    return 限行提示;
    // 该日杭州限行尾号为5和0
}
```
