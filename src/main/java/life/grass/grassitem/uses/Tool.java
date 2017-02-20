package life.grass.grassitem.uses;

import java.util.stream.Stream;

/**
 * Created by ecila on 2017/02/19.
 */
public enum Tool implements UsesType{
    PICK_AXE("pick_axe"),
    AXE("axe"),
    FISHING_ROD("fishing_rod");

    private String key;

    Tool(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public static Tool getType(String key) {
        return Stream.of(Tool.values()).filter(t -> t.getKey().equals(key)).findFirst().orElse(null);
    }

    public static String getLabel() {
        return "tool_type";
    }
}
