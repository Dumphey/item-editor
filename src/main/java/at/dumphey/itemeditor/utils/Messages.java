package at.dumphey.itemeditor.utils;

import org.bukkit.command.CommandSender;

public class Messages {

    public static final String PREFIX = "§6§li§e§le§f§l2§8";
    public static final String CHAT_PREFIX = PREFIX + " - §f";

    public static void send(CommandSender target, String message) {
        target.sendMessage(CHAT_PREFIX + message);
    }
}
