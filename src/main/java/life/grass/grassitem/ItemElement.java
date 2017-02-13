package life.grass.grassitem;

/**
 * Created by ecila on 2017/02/14.
 */
public enum ItemElement {
    ITEM_ID("item_id"),
    MATERIAL("material"),
    META("meta"),
    NAME("name"),
    INFO("info"),
    USES("uses"),
    STATUS("status");

    private String key;

    ItemElement(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
