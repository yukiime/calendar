package team.safe;

public class DateRangeException extends Exception {
  public String getMessage() {
    return "目前只支持1902年到2100年的时间范围";
  }
}
