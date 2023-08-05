package at.dumphey.itemeditor.itemeditor.modules.attributes.screens.manage;

import at.dumphey.itemeditor.itemeditor.modules.attributes.items.AttributeUiItem;
import at.dumphey.itemeditor.itemeditor.modules.attributes.screens.remove.RemoveAttributeUiScreen;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenRemoveAttributeUiItem extends AttributeUiItem {
    public OpenRemoveAttributeUiItem(UiScreen screen, Player player) {
        super(screen, player);
        require(new ItemHasAttributesRequirement(player));
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.SPONGE, "- Remove attribute", "Remove an attribute");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new RemoveAttributeUiScreen(player));
        }
    }
}
