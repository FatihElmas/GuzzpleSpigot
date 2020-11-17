package co.guzzple.guzzple.utils;

import co.guzzple.guzzple.AddonBase;
import co.guzzple.guzzple.Guzzple;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarFile;

public class AddonLoader {
    private final Guzzple api;
    private final List<AddonBase> addons = new ArrayList<>();

    public AddonLoader(Guzzple g) {
        api = g;
    }
    public void load() {
        if (api.getAddonsDir().listFiles(file -> file.getName().endsWith(".jar")).length < 1) return;
        List<File> jarFiles = Arrays.asList(api.getAddonsDir().listFiles(file -> file.getName().endsWith(".jar")));

        jarFiles.forEach(jar -> {
            try {
                JarFile jf = new JarFile(jar);
                AddonConfiguration cfg = new AddonConfiguration(jf, jar, api);
                addons.add(cfg.getAddon());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void enableAll() {
        getAddons().forEach(AddonBase::onEnable);
    }

    public void disableAll() {
        getAddons().forEach(AddonBase::onDisable);
    }

    public List<AddonBase> getAddons() {
        return addons;
    }

    public void disableAddon(AddonBase addon) {
        addon.onDisable();
    }

    public void enableAddon(AddonBase addon) {
        addon.onEnable();
    }
}
