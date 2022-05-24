package team.Item.ItemSchedule;

public class Festival extends CommemorationDay
{
  final short repeatCode = 1; //代表年重复

  public Festival(int id,int month,int day,String content)
  {
    this.id = id;
    this.month = month;
    this.day = day;
    this.content = content;
    this.createTime = 0L; //节日会本地进行初始化,默认为0
  }


}
