package team.safe;

public class InvaildQueryException extends Exception {
  public String getMessage() {
    return "输入的日期不符合规范";
  }
}
