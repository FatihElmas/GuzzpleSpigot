package co.guzzple.guzzple.api.cooldown;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GzzCooldown {

    private static HashMap<String, GzzCooldown> cooldowns = new HashMap<>();
    private final Player p;
    private static final GzzCooldownConfig cooldownConfig = new GzzCooldownConfig();
    private long start;
    private final int timeInSeconds;
    private final UUID id;
    private final String cooldownName;

    public GzzCooldown(Player p, String cooldownName, int timeInSeconds){
        this.id = p.getUniqueId();
        this.cooldownName = cooldownName;
        this.timeInSeconds = timeInSeconds;
        this.p = p;
    }

    public static boolean isInCooldown(Player p, String cooldownName){
        if(getTimeLeft(p, cooldownName)>=1){
            return true;
        } else {
            stop(p, cooldownName);
            return false;
        }
    }

    private static void stop(Player p, String cooldownName){
        GzzCooldown.cooldowns.remove(p.getUniqueId().toString()+cooldownName);
    }

    private static GzzCooldown getCooldown(Player p, String cooldownName){
        return cooldowns.get(p.getUniqueId().toString()+cooldownName);
    }

    public static int getTimeLeft(Player p, String cooldownName){
        GzzCooldown cooldown = getCooldown(p, cooldownName);
        int f = -1;
        if(cooldown!=null){
            long now = System.currentTimeMillis();
            long cooldownTime = cooldown.start;
            int totalTime = cooldown.timeInSeconds;
            int r = (int) (now - cooldownTime) / 1000;
            f = (r - totalTime) * (-1);
        }
        return f;
    }

    public void start(){
        this.start = System.currentTimeMillis();
        cooldowns.put(this.id.toString()+this.cooldownName, this);
    }

    public static Map<String, GzzCooldown> getCooldowns() {
        return cooldowns;
    }

    public static void save() throws Exception {
        String ser = serialize(cooldowns);
        FileConfiguration cfg = cooldownConfig.getConfig();
        cfg.set("cooldowns", ser);
        cooldownConfig.saveConfig();
    }

    public static void load() throws Exception{
        FileConfiguration cfg = cooldownConfig.getConfig();
        if (!cfg.contains("cooldowns")) return;
        String ser = cfg.getString("cooldowns");
        cooldowns = (HashMap<String, GzzCooldown>) deserialize(ser);
        cooldowns.values().forEach(c -> c.start());
    }


    private static String serialize(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    private static Object deserialize(String s) throws IOException,
            ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

}
