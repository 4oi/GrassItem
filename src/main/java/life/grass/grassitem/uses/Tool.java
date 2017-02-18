package life.grass.grassitem.uses;

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
        for(Tool tool: Tool.values()) {
            if(tool.getKey().equals(key)) {
                return tool;
            }
        }
        return null;
    }

    public static String getLabel() {
        return "tool_type";
    }
}
