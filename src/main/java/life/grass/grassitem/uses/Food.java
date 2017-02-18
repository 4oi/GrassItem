package life.grass.grassitem.uses;

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
        for(Food food: Food.values()) {
            if(food.getKey().equals(key)) {
                return food;
            }
        }
        return null;
    }

    public static String getLabel() {
        return "food_type";
    }
}
