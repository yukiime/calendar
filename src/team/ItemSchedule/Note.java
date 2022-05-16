package team.ItemSchedule;
/**
 * 用途:
 * 作为节假日,纪念日,日程的父类
 * 包含：
 * 成员变量:id,创建日期,文本内容,是否重复
 */
public class Note
{

    protected int id; //每个日程拥有唯一的一个id,用于身份识别
    protected long createTime; //日程创建时的时间,
    public String content; //日程的文本内容
    protected boolean isRepeat; //这个日程是否会重复

    //空构造器
    public Note()
    {
    }

    //有参构造器
    public Note(int id,long createTime,String content)
    {
        this.id = id;
        this.createTime = createTime;
        this.content = content;
    }

    //返回id
    public int getId()
    {
        return id;
    }

    //返回createTime
    public long getCreateTime()
    {
        return createTime;
    }

    //返回content
    public String getContent()
    {
        return content;
    }

    //返回isRepeat
    public boolean isRepeat()
    {
        return isRepeat;
    }
}
