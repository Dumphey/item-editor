package at.dumphey.itemeditor.itemeditor.modules.flags.items;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.modules.flags.screens.manage.ManageItemFlagsUiScreen;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenManageItemFlagsUiItem extends UiItem {
    public OpenManageItemFlagsUiItem(UiScreen screen, Player player) {
        super(screen, player);
        requirePermission("iep.edit.item-flags");
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.OAK_SIGN, "Manage item flags",
                "Add or remove item flags");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.getUiManager().open(player, new ManageItemFlagsUiScreen(player));
        }
    }
}
