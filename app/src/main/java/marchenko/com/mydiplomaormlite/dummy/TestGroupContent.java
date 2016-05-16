package marchenko.com.mydiplomaormlite.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class TestGroupContent {
    public static String[] groupNames = new String[]{"Зеленые", "Красные", "Синие", "Желтые", "Черные"};
    public static String[] greenDetail = new String[]{"Коба", "Марченко", "Воробьева", "Ильин", "Никитина"};
    public static String[] redDetail = new String[]{"Погудина", "Иванов", "Гвичиани", "Власов", "Деревянко"};
    public static String[] blueDetail = new String[]{"Дружинин", "Пьяных", "Разницина", "Слынько", "Василенко"};
    public static String[] yellowDetail = new String[]{"Овсянник", "Хижнякова", "Румянцев", "Галанов", "Копылаш"};
    public static String[] blackDetail = new String[]{"Каратанов", "Чебан", "Хлопин", "Понижан", "Чухляд"};
    public static String[][] allDetail = {greenDetail, redDetail, blueDetail, yellowDetail, blackDetail};
    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = groupNames.length;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {

        return new DummyItem(String.valueOf(position), "Группа: " + groupNames[position-1], makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Состав группы: ").append(groupNames[position-1]);
        for (int i = 0; i < COUNT; i++) {
             builder.append("\n"+allDetail[position-1][i]+";");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
