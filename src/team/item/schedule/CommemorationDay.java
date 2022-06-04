package team.item.schedule;

import team.item.controller.ScheduleWork;
import team.safe.ValueException;

public class CommemorationDay extends Note {
  short repeatCode = 1; // 1代表年重复,2代表月重复.

  int month;
  int day;

  CommemorationDay() {
  }

  /**
   * 默认设置为年重复
   */
  public CommemorationDay(int id, long time, String content) {
    super(id, time, content);
    // 默认为年重复
    setDate();
  }

  /**
   * 用户自定义重复类型,但只允许年重复和月重复
   */
  public CommemorationDay(int id, long time, String content, short repeatCode) throws ValueException {
    super(id, time, content);
    setRepeatCode(repeatCode);
    setDate();
  }

  /**
   * 设置合法的repeatCode
   * 对于不合法的传入参数抛出异常
   */
  private void setRepeatCode(short repeatCode) throws ValueException {
    if (repeatCode >= 1 && repeatCode <= 2) {
      this.repeatCode = repeatCode;
    } else {
      throw new ValueException("CommemorationDay设置repeatCode时超出范围");
    }
  }

  public void setDate() {
    int[] array = ScheduleWork.findDate(createTime);
    this.month = array[0];
    this.day = array[1];
  }

  public short getRepeatCode() {
    return repeatCode;
  }

  public int getMonth() {
    return month;
  }

  public int getDay() {
    return day;
  }
}
