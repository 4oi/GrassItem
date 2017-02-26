package life.grass.grassitem.uses;

import java.util.stream.Stream;

/**
 * Created by ecila on 2017/02/19.
 */
public enum Armor implements UsesType {
    LABEL("armor_type"),
    ARMOR("armor"),
    SHIELD("shield");

    private String key;

    Armor(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public static Armor getType(String key) {
        return Stream.of(Armor.values()).filter(t -> t.getKey().equals(key)).findFirst().orElse(null);
    }

    public static String getLabel() {
        return "armor_type";
    }
}
