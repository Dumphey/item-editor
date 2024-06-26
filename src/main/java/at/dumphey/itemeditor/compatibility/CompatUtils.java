package at.dumphey.itemeditor.compatibility;

import at.dumphey.itemeditor.ItemEditor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;

import java.util.logging.Level;

public class CompatUtils {

    private int majorVersion;
    private int minorVersion;

    public CompatUtils() {
        parseVersion();
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    private void parseVersion() {
        try {
            String versionString = Bukkit.getServer().getVersion();
            String mc = versionString.substring(versionString.indexOf("MC: ") + 4);
            mc = mc.substring(2, mc.indexOf(')'));
            String[] parts = mc.split("\\.");
            majorVersion = Integer.parseInt(parts[0]);
            if (parts.length > 1) {
                minorVersion = Integer.parseInt(parts[1]);
            } else {
                minorVersion = 0;
            }
            ItemEditor.getInstance()
                    .getLogger()
                    .log(Level.INFO, "Detected minecraft 1." + majorVersion + "." + minorVersion);
        } catch (Exception e) {
            ItemEditor.getInstance().getLogger().log(Level.SEVERE, "Failed to parse version", e);
        }
    }

    public static boolean isAir(ItemStack itemStack) {
        return itemStack == null || itemStack.getType() == Material.AIR;
    }

    public Material getRemoveItemFlagUiItemMaterial() {
        if (majorVersion >= 14) {
            return Material.getMaterial("ACACIA_SIGN");
        }
        return Material.SIGN;
    }

    public Material getAddItemFlagUiItemMaterial() {

        if (majorVersion >= 14) {
            return Material.getMaterial("OAK_SIGN");
        }
        return Material.SIGN;
    }

    public Material getSignMaterial() {
        if (majorVersion >= 14) {
            return Material.getMaterial("OAK_SIGN");
        }
        return Material.SIGN;
    }

    public String getEnchantmentKey(Enchantment enchantment) {
        if (majorVersion >= 19) {
            return enchantment.getKey().getKey();
        } else {
            return enchantment.toString();
        }
    }

    public static Material either(Material material, Material orElse) {
        return material != null ? material : orElse;
    }
}
