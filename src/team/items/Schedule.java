package team.items;

/*
 * 用途:
 * 作为节假日,纪念日,日程,记事信息的父类
 * 包含：
 * 父类成员变量:日期,文本内容,是否重复
 * 子类成员变量:重复类型,优先级
 */
public class Schedule extends Note {
  int repeatCode; // 1代表年重复,2代表月重复,3代表星期重复,4代表自定义重复
  int order = 0;// 默认是0,即无优先级。1-3优先级逐渐增加
}