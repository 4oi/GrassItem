package life.grass.grassitem;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ecila on 2017/02/14.
 */
public class ItemHandler {
    private String rawJson;
    private Gson gson;
    private JsonObject uniqueJson;
    private JsonObject defaultJson;
    private boolean valid = false;

    public ItemHandler(JsonObject source) {
        uniqueJson = source;
        valid = true;
    }

    public ItemHandler(String source) {
        gson = new Gson();
        valid = true;
        uniqueJson = gson.fromJson(source, JsonObject.class);
    }

    public ItemHandler(ItemStack item) {
        gson = new Gson();
        rawJson = item.getItemMeta().getLore().get(0);
        if(rawJson != null) {
            valid = true;
        }
        uniqueJson = gson.fromJson(rawJson, JsonObject.class);
        // DefaultJsonをIDから読み込むのをやる
    }

    public ItemHandler buildFromId(int id) {
        JsonObject json = new JsonObject();
        json.addProperty(ItemElement.ITEM_ID.getKey(), id);
        return new ItemHandler(json);
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

    public boolean isValid() {
        return valid;
    }

    public ItemStack buildItemStack() {
        return null;
    }
}
