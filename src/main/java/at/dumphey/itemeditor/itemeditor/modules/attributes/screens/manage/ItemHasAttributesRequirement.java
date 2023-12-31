package at.dumphey.itemeditor.itemeditor.modules.attributes.screens.manage;

import at.dumphey.itemeditor.ui.requirements.Requirement;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemHasAttributesRequirement extends Requirement {
    public ItemHasAttributesRequirement(Player player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return getEditedItem().hasItemMeta() && getEditedItem().getItemMeta().hasAttributeModifiers();
    }

    @Override
    protected ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return ItemBuilder.uiItem(originalItem.getType(), null, "Item doesn't have any attributes.");
    }
}
