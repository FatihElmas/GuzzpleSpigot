package co.guzzple.guzzple.api.inventory;

import co.guzzple.guzzple.Guzzple;
import co.guzzple.guzzple.api.GzzAPI;
import co.guzzple.guzzple.api.chat.MinecraftText;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GzzInventoryManager {
    private static GzzAPI api = Guzzple.getAPI();
    private static FileConfiguration c = api.getInventoryConfig().getConfig();
    private static List<GzzInventory> inventories = new ArrayList<>();

    public static void register(GzzInventory inventory) {
        inventories.add(inventory);
    }

    public static void unregister(GzzInventory inventory) {
        if (inventories.contains(inventory)) inventories.remove(inventory);
    }

    // CONFIG
    public static void save(GzzInventory inventory) {
        if (c.contains(inventory.getName())) return;
        c.set(inventory.getName() + "." + "title", inventory.getTitle());
        c.set(inventory.getName() + "." + "row", inventory.getRow());
        for (ItemStack item : inventory.getItems()) {
            if (!item.getType().equals(Material.AIR)) {
                int index = inventory.getItems().indexOf(item);
                Material type = item.getType();
                List<String> lore = item.getItemMeta().getLore();
                lore.replaceAll(s -> s.replace("§", "&"));
                String displayName = item.getItemMeta().getDisplayName().replace("§", "&");
                c.set(inventory.getName() + ".items." + index + ".display_name", displayName);
                c.set(inventory.getName() + ".items." + index + ".lore", lore);
                c.set(inventory.getName() + ".items." + index + ".amount", item.getAmount());
                c.set(inventory.getName() + ".items." + index + ".type", item.getType().name());
                item.getEnchantments().entrySet().forEach(e -> c.set(inventory.getName() + ".items." + type.name() + ".enchantments." + e.getKey().getKey().getNamespace(), e.getValue()));
            }
        }
        api.getInventoryConfig().saveConfig();
    }

    public static GzzInventory load(String name) {
        if (!c.contains(name)) Guzzple.getInstance().error("Inventory Map named " + name + " not found!");
        String title = new MinecraftText(c.getString(name + ".title")).color().getString();
        int row = c.getInt(name + ".row");
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i<row; i++) {
            items.add(new ItemStack(Material.AIR));
        }
        for (String num : c.getConfigurationSection(name + ".items").getKeys(false)) {
            int index = Integer.parseInt(num);

            Material mat = Material.matchMaterial(name + ".items." + index + ".type");
            String displayName = new MinecraftText(c.getString(name + ".items." + index + ".display_name")).color().getString();
            int amount = c.getInt(name + ".items." + index + ".amount");
            List<String> lore = new MinecraftText(c.getStringList(name + ".items." + index + ".lore")).color().getList();
            Map<Enchantment, Integer> enchantmentMap = new HashMap<>();
            for (String enchant : c.getConfigurationSection(name + ".items." + index + ".enchantments").getKeys(false)) {
                int enchantLevel = c.getInt(name + ".items." + index + ".enchantments." + enchant);
                enchantmentMap.put(Enchantment.getByKey(new NamespacedKey(GzzAPI.getGuzzple(), enchant)), enchantLevel);
            }
            ItemStack item = new ItemStack(mat, amount);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(displayName);
            meta.setLore(lore);
            item.setItemMeta(meta);
            items.set(index, item);
        }
        return new GzzInventory(GzzAPI.getGuzzple()).create(name, title, row).setItem(items);
    }
}
