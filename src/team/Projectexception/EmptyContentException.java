package team.Projectexception;

public class EmptyContentException extends Exception {
    public String getMessage() {
        return "请输入内容！";
    }
}
