package team.Item.ItemSchedule;

import team.Item.ItemsWork.ScheduleWork;
import team.Projectexception.ValueException;

/**
 * 用途:
 * 用于日程
 * 包含：
 * 父类成员变量:id,创建日期,文本内容,是否重复
 * 子类成员变量:重复类型,优先级,自定义重复的周期
 */
public class Schedule extends Note {
    private short repeatCode; // 1代表年重复,2代表月重复,3代表星期重复,4代表自定义重复
    private short order = 0; // 默认是0,即无优先级。1-3代表优先级逐渐增加
    private int repeatDuration = 0; // 自定义重复的周期,指从当天算起到待办当天的时间间隔,包括尾不包括头,单位为天

    // 重复日程的月,周,星期数
    private int month = -1; // 从1-12分别代表1-12月
    private int day = -1; // 从1-31分别代表1-31号
    private int week = -1; // 7代表星期天,1-6分别代表星期1-6

    // 空构造器
    public Schedule() {
    }

    // 有参构造器,构造不重复的日程
    // 对于没有传入isRepeat的日程,直接调用父构造器,isRepeat默认为false
    public Schedule(int id, long createTime, String content) {
        super(id, createTime, content);
        super.isRepeat = false;
    }

    // 有参构造器,构造非自定义重复的日程
    // 除isRepeat的父类成员变量调用父构造器赋值,随后赋值isRepeat和repeatCode
    public Schedule(int id, long createTime, String content, boolean isRepeat, short repeatCode) {
        super(id, createTime, content);
        super.isRepeat = isRepeat;
        this.repeatCode = repeatCode;
    }

    // 有参构造器,构造不是重复日程,有优先级的日程
    // 除isRepeat的父类成员变量调用父构造器赋值,随后赋值order,isRepeat默认为false
    public Schedule(int id, long createTime, String content, short order) {
        super(id, createTime, content);
        super.isRepeat = false;
        this.order = order;
    }

    // 有参构造器,构造既是重复日程又有优先级的日程
    // 除isRepeat的父类成员变量调用父构造器赋值,随后赋值子类成员变量
    public Schedule(int id, long createTime, String content, boolean isRepeat, short repeatCode, short order) {
        super(id, createTime, content);
        super.isRepeat = isRepeat;
        this.repeatCode = repeatCode;
        this.order = order;
    }

    // 有参构造器,构造自定义重复的日程
    // 优先级为默认的0
    // 父类成员变量调用父构造器赋值,随后赋值子类成员变量
    public Schedule(int id, long createTime, int repeatDuration, String content) {
        super(id, createTime, content);
        super.isRepeat = true;
        this.repeatCode = 4;
        this.repeatDuration = repeatDuration;
    }

    // 根据repeatCode赋值
    // 防止出现赋值错乱
    public void setAutoRepeatDuration() {

        // 获取存储了createTime这个日期的月份,日期,星期数
        int[] date = ScheduleWork.findDate(createTime);

        if (repeatCode == 1) {
            month = date[0];
            day = date[1];
        } else if (repeatCode == 2) {
            day = date[1];
        } else if (repeatCode == 3) {
            week = date[2]; // 7代表星期天,1-6分别代表星期1-6
        }
    }

    public void setOrder(short order) throws ValueException {
        if (order >= 0 && order < 4) {
            this.order = order;
        } else {
            throw new ValueException("设置order时,order超出范围");
        }
    }

    // 获取优先级
    public short getOrder() {
        return order;
    }

    // 获取重复类型
    public short getRepeatCode() {
        return repeatCode;
    }

    // 获取月份
    public int getMonth() {
        return month;
    }

    // 获取是月中的第几日
    public int getDay() {
        return day;
    }

    // 获取星期数
    public int getWeek() {
        return week;
    }

    // 获取自定义重复日期的周期
    public int getRepeatDuration() {
        return repeatDuration;
    }
}
