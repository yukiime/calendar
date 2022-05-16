package team.Item.ItemSchedule;
/**
 * 用途:
 * 用于日程
 * 包含：
 * 父类成员变量:id,创建日期,文本内容,是否重复
 * 子类成员变量:重复类型,优先级,自定义重复的周期
 */
public class Schedule extends Note
{
    private short repeatCode; //1代表年重复,2代表月重复,3代表星期重复,4代表自定义重复
    private short order=0; //默认是0,即无优先级。1-3代表优先级逐渐增加
    private int repeatDuration = 0; //自定义重复的周期

    //空构造器
    public Schedule()
    {
    }

    // 有参构造器,构造不重复的日程
    // 对于没有传入isRepeat的日程,直接调用父构造器,isRepeat默认为false
    public Schedule(int id,long createTime,String content)
    {
        super(id,createTime,content);
        super.isRepeat = false;
    }

    // 有参构造器,构造默认order的日程
    // 除isRepeat的父类成员变量调用父构造器赋值,随后赋值isRepeat和repeatCode
    public Schedule(int id,long createTime,String content,boolean isRepeat,short repeatCode)
    {
        super(id,createTime,content);
        super.isRepeat = isRepeat;
        this.repeatCode = repeatCode;
    }

    // 有参构造器,构造不是重复日程,有优先级的日程
    // 除isRepeat的父类成员变量调用父构造器赋值,随后赋值order,isRepeat默认为false
    public Schedule(int id,long createTime,String content,short order)
    {
        super(id,createTime,content);
        super.isRepeat = false;
        this.order = order;
    }

    // 有参构造器,构造既是重复日程又有优先级的日程
    // 除isRepeat的父类成员变量调用父构造器赋值,随后赋值子类成员变量
    public Schedule(int id,long createTime,String content,boolean isRepeat,short repeatCode,short order)
    {
        super(id,createTime,content);
        super.isRepeat = isRepeat;
        this.repeatCode = repeatCode;
        this.order = order;
    }

    //设置自定义重复的周期
    public void setRepeatDuration(int time)
    {
        this.repeatDuration = time;
    }
}
