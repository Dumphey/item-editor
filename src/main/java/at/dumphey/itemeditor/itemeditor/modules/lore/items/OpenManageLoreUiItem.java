package at.dumphey.itemeditor.itemeditor.modules.lore.items;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import at.dumphey.itemeditor.itemeditor.modules.lore.screens.manage.ManageLoreUiScreen;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenManageLoreUiItem extends UiItem {
    public OpenManageLoreUiItem(UiScreen screen, Player player) {
        super(screen, player);
        requirePermission("iep.edit.lore");
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.WRITABLE_BOOK,"Manage lore","Add or remove lore lines");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.getUiManager().open(player, new ManageLoreUiScreen(player));
        }
    }
}
