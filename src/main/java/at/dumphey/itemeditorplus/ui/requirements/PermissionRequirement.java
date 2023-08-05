package at.dumphey.itemeditorplus.ui.requirements;

import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PermissionRequirement extends Requirement {

    private static final String[] WILDCARDS = {"iep.*", "iep.edit.*"};

    private final String permission;
    private String[] wildcards = WILDCARDS;

    public PermissionRequirement(Player player, String permission) {
        super(player);
        this.permission = permission;
    }

    @Override
    public boolean isFulfilled() {
        if (player.hasPermission(permission)) {
            return true;
        }

        if (wildcards != null) {
            for (String wildcardPermission : wildcards) {
                if (player.hasPermission(wildcardPermission)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return ItemBuilder.uiItem(Material.BARRIER, null, "§cYou're don't have permission.");
    }

    public void withoutWildcard() {
        wildcards = null;
    }
}
