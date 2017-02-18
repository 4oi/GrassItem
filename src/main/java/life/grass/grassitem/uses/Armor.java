package life.grass.grassitem.uses;

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
        for(Armor armor: Armor.values()) {
            if(armor.getKey().equals(key)) {
                return armor;
            }
        }
        return null;
    }

    public static String getLabel() {
        return "armor_type";
    }
}
