import team.Data.FestivalData;
import team.frontend.EntranceFrame;

public class MainCalendar {

    /**
     * 用途:
     * 主方法
     */
    public static void main(String[] args) {
        initialData();
        EntranceFrame entrance = new EntranceFrame("Calendar");
        entrance.setVisible(true);
    }

    private static void initialData() {
        FestivalData.FestivalDataBase();
    }
}
