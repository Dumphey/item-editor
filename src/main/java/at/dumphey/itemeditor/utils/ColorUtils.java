package at.dumphey.itemeditor.utils;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import org.bukkit.ChatColor;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {

    private static final String REGEX = "&#([A-Fa-f\\d]{6})";
    private static final Pattern COLOR_PATTERN = Pattern.compile(REGEX);

    public static String translateColors(String value) {
        value = ChatColor.translateAlternateColorCodes('&', value);
        if (ItemEditor.INSTANCE.getCompatUtils().getMajorVersion() < 16) {
            return value;
        }

        Matcher matcher = COLOR_PATTERN.matcher(value);
        while (matcher.find()) {
            String group = matcher.group();
            try {
                String colval = group.substring(1);
                String color = net.md_5.bungee.api.ChatColor.class.getMethod("of", String.class)
                        .invoke(null, colval).toString();

                value = value.replace(group, color);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
        return value;
    }
}
