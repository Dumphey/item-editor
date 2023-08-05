package at.dumphey.itemeditor.itemeditor.modules.attributes.screens.manage;

import at.dumphey.itemeditor.itemeditor.modules.attributes.screens.add.AddAttributeChooseTypeUiScreen;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenAddAttributeUiItem extends UiItem {

    public OpenAddAttributeUiItem(UiScreen screen, Player player) {
        super(screen, player);
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.BOOK, "+ Add attribute", "Add an attribute");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new AddAttributeChooseTypeUiScreen(player));
        }
    }
}
