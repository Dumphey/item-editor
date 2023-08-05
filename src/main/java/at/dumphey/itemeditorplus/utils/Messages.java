package at.dumphey.itemeditorplus.utils;

import org.bukkit.command.CommandSender;

public class Messages {

    public static final String PREFIX = "§6§lI§e§lE§f§lP§8";
    public static final String CHAT_PREFIX = PREFIX + "§l> §f";

    public static void send(CommandSender target, String message) {
        target.sendMessage(CHAT_PREFIX + message);
    }
}
