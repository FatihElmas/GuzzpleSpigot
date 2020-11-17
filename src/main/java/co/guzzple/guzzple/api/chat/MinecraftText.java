package co.guzzple.guzzple.api.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class MinecraftText {

    private List<String> list;
    private String str;

    /**
     * Chat API for String
     * @param str String to customize
     */
    public MinecraftText(String str) {
        this.str = str;
    }

    /**
     * Chat API for List
     * @param list String List to customize
     */
    public MinecraftText(List<String> list) {
        this.list = list;
    }

    /**
     * Chat API for Arrays
     * @param array Array to customize
     */
    public MinecraftText(String... array) {
        this.list = Arrays.asList(array);
    }

    // CHAT UTILS

    /**
     * If list variable isn't null and str is null, method coloring list.
     * If list variable is null and str isn't null, method coloring str.
     */
    public MinecraftText color() {
       if (str == null && list != null) { // If str is null, color list
            this.list.replaceAll(s -> ChatColor.translateAlternateColorCodes('&', s));
       }
       if (list == null && str != null) { // If list is null, color str
           this.str = ChatColor.translateAlternateColorCodes('&', str);
       }
        return this;
    }

    public MinecraftText placeholder(String placeholder, String value) {
        str = str.replace(placeholder, value);
        return this;
    }

    // GET METHODS

    /**
     * Returns customized list
     * @return Customized list
     */
    public List<String> getList() {
        return list;
    }

    /**
     * Returns customized string
     * @return Customized string
     */
    public String getString() {
        return str;
    }

    /**
     * Returns customized array
     * @return Customized array
     */
    public String[] getArray() {
        return (String[]) list.toArray();
    }

    /**
     * Sends customized string to player
     * @param p Player
     */
    public void send(Player p) {
        if (str != null) {
            p.sendMessage(str);
            return;
        }
        if (list != null) {
            for (String str : list) {
                p.sendMessage(str);
            }
        }
    }
}
