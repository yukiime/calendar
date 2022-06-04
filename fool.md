##**记事信息操作设计：**
在数据类中创建2个存储了 `Schedule` 元素的按`creatTime`大小排序的`ArrayList`

1.存储周期不重复的`Schedule`  2.存储周期重复的`Schedule` 

### **记事信息的创建：**
调用`create`类方法（如
`
createSchedule(long createTime, String content, boolean isRepeat, short repeatCode, short order)
`）
可以按传递的数据创建一个新的实例对象，并进行插入操作。

其中设计`createTime`：只精确到天的创建项目的当日的时间戳

设计`id`：对应实例的唯一的身份认证信息，通过

    private static int len;

    public static int getLen() 
    {
        return len;
    }

    public static void addLenOne() 
    {
        ScheduleData.len += 1;
    }
来维护长度

**插入操作**会根据传入的`isRepeat`值判断需要插入的`list`
随后调用相应的`find方法(long createTime)`
方法，根据二分寻找到合适的插入位置，规定这个插入位置使得在插入时永远插入到
`list`中具有相同`createTime`的元素的末尾，即插入位置的后一个元素的
`createTime`属性永远大于插入的元素的`createTime`属性。

调用`find方法(long createTime)`
来寻找到插入位置后会返回该插入位置的下标，该方法使用二分搜索。

首先进行一次if语句构成的特判

        if (list为空 || 末尾元素的创建时间小于等于需要插入的元素的创建时间 ) 
        {
            return 末尾下标;
        }
        else if ( 首元素的创建时间大于需要插入的元素的创建时间 ) 
        {
            return 0;
        }

随后进行二分搜索

         while (start <= end) 
        {
                mid = (start + end) / 2; // 取中间值

                if (寻找到了与传入数据相同的元素) 
                {    
                    //显然其下标为mid，以该下标为起点,向后搜索,
                    for (int i = mid; i <= end; i++) 
                    {
                        if (向后搜索,直到出现拥有不同数据的元素) 
                        {
                            return 该下标;
                        }
                    }
                }
                else if (查询下标的数据比传入数据大) 
                {
                    下一个搜索区段的尾下标更改为该下标-1
                }
                // 在队列中寻找到了与传入数据相同的元素,其下标为mid,无须记录
                else  if (查询下标的数据比传入数据小) 
                { 
                                                                                          
                    下一个搜索区段的首下标更改为该下标+1
                }

            // 此时队列中不存在与传入数据相同的元素,故进行插入
            return end + 1;
        }

### **记事信息的删除：**

调用`delete方法（int id, long createTime, boolean isRepeat）`来将需要删除的项目进行删除

**删除操作**会根据传入的`isRepeat`值判断需要进行寻找的`list`随后进行搜索

该搜索同样使用二分搜索

首先为了效率和安全性进行两次`if语句`的特判
       
         if (list为空) 
        {
            throw new 异常("ArrayList已经为空,不能再进行删除操作");
        }

        if ( 末尾元素的创建时间小于需要删除的元素的创建时间 ) 
        {
            throw new 异常("传入的createTime超出当前队列的最大值,不存在该数据");
        } 
        else if (首元素的创建时间大于需要插入的元素的创建时间 )
        {
            throw new 异常("传入的createTime小于当前队列的最小值,不存在该数据");
        }

随后进行二分搜索

        while (start <= end) 
        {
                mid = (start + end) / 2; // 取中间值

                if (寻找到了与传入数据相同的元素) 
                {    
                    //记录下标 向前向后搜索
                    
                    //要注意下标不越界
                    while (向后搜索) 
                    {
                        if (id值相同) 
                        {
                            删除该元素;
                            并返回；
                        }
                    }
                    
                    //要注意下标不越界
                    while (向前搜索) 
                    {
                        if (id值相同) 
                        {
                            删除该元素;
                            并返回；
                        }
                    }
                }
                else if (查询下标的数据比传入数据大) 
                {
                    下一个搜索区段的尾下标更改为该下标-1
                }
                // 在队列中寻找到了与传入数据相同的元素,其下标为mid,无须记录
                else  if (查询下标的数据比传入数据小) 
                { 
                                                                                          
                    下一个搜索区段的首下标更改为该下标+1
                }
        }
        
        如果到此还没有返回，即是搜索后没有发现list中有和需要删除项目具有相同特征属性的元素
        返回list中不存在需要删除的元素的提示信息

### **记事信息的修改：**

先调用`delete方法`将该项目删除，随后根据修改信息调用`create方法`创建一个新的项目

##**节日/纪念日设计：**

在数据类中创建1个存储了 `CommemorationDay` 和`Festival`元素，并按`month`和`day`大小排序的`ArrayList`

该`ArrayList`的月份节点通过数据类中的树状数组`int[] monthIndex`维护和问询。

维护操作如下:

    int lowBit(int k) 
    {
        return k & (-k);
    }

    void treeAdd(int x, int a) 
    {
        while (数组长度) 
        {
            对于父节点都进行添加
            lowbit操作寻找父节点
        }
    }

询问操作如下:

    int sum(int x) 
    {
        preSum = 0;
    
            while (x > 0) 
            {
                逐个添加子节点的值
            }
    
            return preSum;
        }

    int segSum(int l, int r)
    {
        两次前缀和相减
        return segSum;
    }
    

### **节日/纪念日的创建：**

调用`create`类方法（如
`
createCommemorationDay(long createTime, String content)
`）
可以按传递的数据创建一个新的实例对象，并进行插入操作。

其中设计的`createTime`、`id`与记事信息类相同。

插入操作中会对重复类型进行判断

1.对于年重复类型的实例对象会直接按照传入的`time`数据根据`Calendar类`
的方法获取月份数据，并根据树状数组`monthIndex`的节点问询操作索引到`list`中的下标。

    start = 树状数组.sum(新建实例.getMonth() - 1); // 该月份的第一天
    end = 树状数组.sum(新建实例.getMonth()) - 1; // 该月份的最后一天

随后在这个区间内进行二分搜索，寻找到与`day`值相符合的插入位置

规定此插入位置一定处在具有相同`day`值的元素的最末尾


        while (start <= end)
        {
                mid = (start + end) / 2; // 取中间值

                if (寻找到了与传入数据相同的元素) 
                {    
                    //显然其下标为mid，以该下标为起点,向后搜索,
                    for (int i = mid; i <= end; i++) 
                    {
                        if (向后搜索,直到出现拥有不同数据的元素) 
                        {
                            return 该下标;
                        }
                    }
                }
                else if (查询下标的数据比传入数据大) 
                {
                    下一个搜索区段的尾下标更改为该下标-1
                }
                // 在队列中寻找到了与传入数据相同的元素,其下标为mid,无须记录
                else  if (查询下标的数据比传入数据小) 
                { 
                                                                                          
                    下一个搜索区段的首下标更改为该下标+1
                }

            // 此时队列中不存在与传入数据相同的元素,故进行插入
            return end + 1;
        }

2.对于月重复类型的实例对象会直接按照传入的`time`数据根据`Calendar类`
的方法获取`day`数据，并直接获取树状数组`monthIndex`的“13月”节点，
在`list`的末尾对一个月重复元素的区间段进行二分搜索，该过程与上同

### **节日/纪念日的删除：**

调用`delet`类的方法，通过二分搜索或遍历查询寻找到元素并删除

### **节日/纪念日的修改：**
先调用`delete方法`将该项目删除，随后根据修改信息调用`create方法`创建一个新的项目
