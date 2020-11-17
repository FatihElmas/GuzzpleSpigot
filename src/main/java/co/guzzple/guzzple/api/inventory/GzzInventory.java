package co.guzzple.guzzple.api.inventory;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class GzzInventory {
    private final JavaPlugin pl;
    private Inventory inv;
    private String title;
    private List<ItemStack> items = new ArrayList<>();
    private int row;
    private String name;


    public GzzInventory(JavaPlugin pl) {
        this.pl = pl;
    }
    
    public GzzInventory create(String name, String title, int row) {
        this.title = title;
        this.row = 9 * row;
        this.name = name;

        for (int i = 0; i<this.row; i++) {
            items.add(new ItemStack(Material.AIR));
        }
        return this;
    }

    // Algorithm: (y * 9 - 9) + (x - 1)
    public GzzInventory setItem(int x, int y, ItemStack item) {
        int slot = (y * 9 - 9) + (x - 1);
        items.set(slot, item);
        return this;
    }

    public GzzInventory setItem(int slot, ItemStack item) {
        items.set(slot, item);
        return this;
    }

    public GzzInventory setItem(List<ItemStack> items) {
        this.items = items;
        return this;
    }

    public GzzInventory setBackground(ItemStack background) {
        for (int i = 0; i<this.row; i++) {
            if (items.get(i).getType() == Material.AIR) {
                items.set(i, background);
            }
        }
        return this;
    }

    public GzzInventory setBackground(Material background) {
        for (int i = 0; i<this.row; i++) {
            if (items.get(i).getType() == Material.AIR) {
                items.set(i, new ItemStack(background));
            }
        }
        return this;
    }

    public Inventory get() {
        inv = Bukkit.createInventory(null, row, title);
        for (int i = 0; i<this.row; i++) {
            inv.setItem(i, items.get(i));
        }
        return inv;
    }

    public Inventory get(Player p) {
        inv = Bukkit.createInventory(p, row, title);
        for (int i = 0; i<this.row; i++) {
            inv.setItem(i, items.get(i));
        }
        return inv;
    }

    public String getName() {
        return name;
    }

    public int getRow() {
        return row;
    }

    public String getTitle() {
        return title;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public void open(Player p) {
        Inventory inv = get(p);
        p.openInventory(inv);

    }
}
