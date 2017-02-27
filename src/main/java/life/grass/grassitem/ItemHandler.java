package life.grass.grassitem;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import life.grass.grassitem.uses.Armor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import life.grass.grassitem.uses.UsesType;

/**
 * Created by ecila on 2017/02/14.
 */
public class ItemHandler {
    private static Map<Integer, JsonObject> defaultItems = new HashMap<>();
    private static Map<Integer, JsonObject> enchants = new HashMap<>();

    private String rawJson;
    private Gson gson;
    private JsonObject uniqueJson;
    private JsonObject defaultJson;
    private boolean valid = false;

    public ItemHandler(JsonObject source) {
        uniqueJson = source;
        valid = true;
        defaultJson = defaultItems.get(getItemId());
    }

    public ItemHandler(String source) {
        gson = new Gson();
        valid = true;
        uniqueJson = gson.fromJson(source, JsonObject.class);
        defaultJson = defaultItems.get(getItemId());
    }

    public ItemHandler(ItemStack item) {
        gson = new Gson();
        rawJson = item.getItemMeta().getLore().get(0);
        if(rawJson != null) {
            valid = true;
        }
        uniqueJson = gson.fromJson(rawJson, JsonObject.class);
        defaultJson = defaultItems.get(getItemId());
    }

    public ItemHandler buildFromId(int id) {
        JsonObject json = new JsonObject();
        json.addProperty(ItemElement.ITEM_ID.getKey(), id);
        return new ItemHandler(json);
    }

    private static int calcElements(int base, String mod) {
        try {
            if(mod.startsWith("+")) {
                return base + Integer.parseInt(mod);
            } else if(mod.startsWith("-")) {
                return base - Integer.parseInt(mod);
            } else if(mod.startsWith("*")) {
                return (int) (base * Double.parseDouble(mod));
            } else {
                return Integer.parseInt(mod);
            }
        } catch(NumberFormatException e) {
        }
        return base;
    }

    private JsonElement getDefaultElement(ItemElement element) {
        return defaultJson.get(element.getKey());
    }

    public int getItemId() {
        return getDefaultElement(ItemElement.ITEM_ID).getAsInt();
    }

    public Material getItemMaterial() {
        return Material.getMaterial(getDefaultElement(ItemElement.MATERIAL).getAsString());
    }

    public byte getItemMeta() {
        return getDefaultElement(ItemElement.META).getAsByte();
    }

    public String getItemName() {
        return getDefaultElement(ItemElement.NAME).getAsString();
    }

    public String getItemInfo() {
        return getDefaultElement(ItemElement.INFO).getAsString();
    }

    private JsonObject getDefaultUses() {
        JsonElement uses = defaultJson.get("uses");
        return uses != null ? uses.getAsJsonObject() : null;
    }

    public Armor getArmorType() {
        JsonObject uses = getDefaultUses();
        if(uses == null) return null;
        return Armor.getType(uses.get(Armor.getLabel()).getAsString());
    }

    public void setArmorType(Armor armorType) {
        JsonObject json = new JsonObject();
        json.addProperty(Armor.getLabel(), armorType.getKey());
        defaultJson.add("uses", json);
    }

    public <T extends Enum<T> & UsesType> T getType(Class<T> clazz) {
        JsonObject uses = getDefaultUses();
        if (uses == null) {
            return null;
        }
        try {
            String label = clazz.getSimpleName() + "_label";
            return Enum.valueOf(clazz, uses.get(label).getAsString());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public <T extends Enum<T> & UsesType> void setType(Class<T> clazz, T value) {
        JsonObject json = new JsonObject();
        String label = clazz.getSimpleName() + "_label";
        json.addProperty(label, value.getKey());
        defaultJson.add("uses", json);
    }

    public boolean isValid() {
        return valid;
    }

    public ItemStack buildItemStack() {
        return null;
    }
}
