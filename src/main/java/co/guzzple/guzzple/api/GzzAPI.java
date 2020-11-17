package co.guzzple.guzzple.api;

import co.guzzple.guzzple.Guzzple;
import co.guzzple.guzzple.api.inventory.GzzInventoryConfig;

import java.io.File;

public class GzzAPI {
    private static Guzzple guzzple;
    private File dataDirectory = new File(Guzzple.getInstance().getDataFolder().getPath());

    private static GzzAPI instance;
    private static GzzInventoryConfig inventoryConfig;

    // GET METHODS
    public File getDataDirectory() {
        return dataDirectory;
    }

    public GzzInventoryConfig getInventoryConfig() {
        return inventoryConfig;
    }

    public GzzAPI(Guzzple g) {
        guzzple = g;
    }

    public static Guzzple getGuzzple() {
        return guzzple;
    }
}
