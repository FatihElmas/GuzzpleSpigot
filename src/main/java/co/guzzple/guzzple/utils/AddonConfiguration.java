package co.guzzple.guzzple.utils;

import co.guzzple.guzzple.AddonBase;
import co.guzzple.guzzple.Guzzple;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class AddonConfiguration {


    private final JarFile jf;
    private final File jar;
    private final Guzzple api;

    public AddonConfiguration(JarFile jf, File jar, Guzzple api) {
        this.jf = jf;
        this.jar = jar;
        this.api = api;

    }

    private FileConfiguration getConfiguration() {
        JarEntry config = Collections.list(jf.entries()).stream().filter(je -> je.getName().equals("addon.yml")).collect(Collectors.toList()).get(0);
        if (config == null) api.error("&c" + jar.getName() + " &7not contain addon.yml file.");

        FileConfiguration cfg = null;
        try {
            InputStreamReader reader = new InputStreamReader(jf.getInputStream(config));
            cfg = YamlConfiguration.loadConfiguration(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cfg;
    }

    private List<JarEntry> getJarEntries() {
        return Collections.list(jf.entries());
    }

    public String getName() {
        return getConfiguration().getString("name");
    }

    public String getVersion() {
        return getConfiguration().getString("version");
    }

    public AddonBase getAddon() {
        String main = getConfiguration().getString("main").replace(".", File.separator);
        List<JarEntry> entry = getJarEntries().stream().filter(j -> j.getName().equals(main + ".class")).collect(Collectors.toList());
        if (entry.size() < 1) {
            api.error(getName() + " not contain main class.");
            return null;
        }
        if (entry.size() > 1) {
            api.error(getName() + " has multiple main class.");
            return null;
        }
        JarEntry e = entry.get(0);

        AddonBase addon = null;
        try {
            URLClassLoader cl = URLClassLoader.newInstance(new URL[]{jar.toURI().toURL()}, getClass().getClassLoader());
            String className = e.getName().substring(0, e.getName().length()-6);
            className = className.replace('/', '.');
            Class<?> loadedClass = Class.forName(className, true, cl);
            Class<? extends AddonBase> addonClass = (Class<? extends AddonBase>) loadedClass;
            addon = addonClass.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return addon;
    }
}
