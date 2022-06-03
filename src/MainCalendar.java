import team.Data.FestivalData;
import team.Data.ItemsRead;
import team.Data.ItemsWriter;
import team.frontend.Context;
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
        Context.initialContext();
        ItemsRead.readAllItems();
        FestivalData.FestivalDataBase();
    }
}
