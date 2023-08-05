package at.dumphey.itemeditor.itemeditor.modules.attributes.items;

import at.dumphey.itemeditor.itemeditor.modules.attributes.screens.manage.ManageAttributesUiScreen;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenManageAttributesUiItem extends UiItem {

    public OpenManageAttributesUiItem(UiScreen screen, Player player) {
        super(screen, player);
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.BOOK, "Manage attributes", "Add or remove attributes");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new ManageAttributesUiScreen(player));
        }
    }
}
