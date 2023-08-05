package at.dumphey.itemeditor.ui.requirements;

import at.dumphey.itemeditor.utils.ItemBuilder;
import at.dumphey.itemeditor.utils.Permissions;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PermissionRequirement extends Requirement {


    private final String permission;

    public PermissionRequirement(Player player, String permission) {
        super(player);
        this.permission = permission;
    }

    @Override
    public boolean isFulfilled() {
        if (player.hasPermission(permission)) {
            return true;
        }

        return player.hasPermission(Permissions.WILDCARD);
    }

    @Override
    public ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return ItemBuilder.uiItem(Material.BARRIER, null, "§cYou're don't have permission.");
    }

}
