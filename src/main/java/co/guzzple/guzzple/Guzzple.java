package co.guzzple.guzzple;

import co.guzzple.guzzple.api.GzzAPI;
import co.guzzple.guzzple.api.chat.MinecraftText;
import co.guzzple.guzzple.utils.AddonLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Guzzple extends JavaPlugin {
    private File addonsDir = new File(getDataFolder().getPath() + File.separator + "addons");
    private static Guzzple instance;
    private AddonLoader loader;
    private static GzzAPI api;

    @Override
    public void onEnable() {
        instance = this;
        loader = new AddonLoader(this);
        api = new GzzAPI(this);

        /**
         * ADDON LOADER
         */
        if (!addonsDir.exists()) addonsDir.mkdirs();
        loader.load();
        loader.enableAll();
        info("Loaded &a" + loader.getAddons().size() + " &7of Addons");
    }

    @Override
    public void onDisable() {
        loader.disableAll();
    }

    public static Guzzple getInstance() {
        return instance;
    }

    public static GzzAPI getAPI() {
        return api;
    }

    public File getAddonsDir() {
        return addonsDir;
    }

    public AddonLoader getLoader() {
        return loader;
    }

    // LOGGER
    public void info(String str) {
        System.out.println(new MinecraftText("&9[Guzzple INFO] &7" + str).color().getString());
    }

    public void warn(String str) {
        System.out.println(new MinecraftText("&e[Guzzple WARN] &7" + str).color().getString());
    }

    public void error(String str) {
        System.out.println(new MinecraftText("&c[Guzzple ERROR] &7" + str).color().getString());
    }

}
