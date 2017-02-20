package life.grass.grassitem.uses;

import java.util.stream.Stream;

/**
 * Created by ecila on 2017/02/19.
 */
public enum Food implements UsesType {
    INGREDIENT("ingredient"),
    SEASONING("seasoning");

    private String key;

    Food(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public static Food getType(String key) {
        return Stream.of(Food.values()).filter(t -> t.getKey().equals(key)).findFirst().orElse(null);
    }

    public static String getLabel() {
        return "food_type";
    }
}
