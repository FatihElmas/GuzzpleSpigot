package co.guzzple.guzzple.api.data;

import co.guzzple.guzzple.api.GzzAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class PlayerData {
    private final File dataDir;
    private final File playerDataFile;
    private final JavaPlugin instance;
    private final String name;

    public PlayerData(Player player) {
        this.instance = GzzAPI.getGuzzple();
        this.name = instance.getName();
        this.dataDir = new File(instance.getDataFolder() + File.separator + "players");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        this.playerDataFile = new File(dataDir.toPath() + File.separator + player.getUniqueId().toString() + ".yml");
        if (!playerDataFile.exists()) {
            try {
                playerDataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File getPlayerDataFile() {
        return playerDataFile;
    }

    public FileConfiguration getPlayerData() {
        return YamlConfiguration.loadConfiguration(playerDataFile);
    }

    public String getString() {
        return getPlayerData().getString(instance.getName().toLowerCase());
    }
}
