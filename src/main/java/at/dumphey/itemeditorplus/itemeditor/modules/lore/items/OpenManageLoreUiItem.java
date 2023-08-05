package at.dumphey.itemeditorplus.itemeditor.modules.lore.items;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import at.dumphey.itemeditorplus.itemeditor.modules.lore.screens.manage.ManageLoreUiScreen;
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
