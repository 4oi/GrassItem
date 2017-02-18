package life.grass.grassitem.uses;

/**
 * Created by ecila on 2017/02/19.
 */
public enum Weapon implements UsesType {
    SWORD("sword"),
    BATTLE_AXE("battle_axe"),
    BOW("bow");

    private String key;

    Weapon(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public static Weapon getType(String key) {
        for(Weapon weapon: Weapon.values()) {
            if(weapon.getKey().equals(key)) {
                return weapon;
            }
        }
        return null;
    }

    public static String getLabel() {
        return "weapon_type";
    }
}
