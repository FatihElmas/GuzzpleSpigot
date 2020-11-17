package co.guzzple.guzzple.api.inventory;

import co.guzzple.guzzple.Guzzple;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class GzzInventoryConfig {
    private File file = new File(Guzzple.getAPI().getDataDirectory() + File.separator + "config" + File.separator + "inventories.yml");

    public GzzInventoryConfig() {
        if (!file.exists()) {
            create();
        }
    }

    private void create() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveConfig() {
        try {
            getConfig().save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }
}
