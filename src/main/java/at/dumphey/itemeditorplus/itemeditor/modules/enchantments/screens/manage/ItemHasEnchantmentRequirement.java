package at.dumphey.itemeditorplus.itemeditor.modules.enchantments.screens.manage;

import at.dumphey.itemeditorplus.ui.requirements.Requirement;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemHasEnchantmentRequirement extends Requirement {
    protected ItemHasEnchantmentRequirement(Player player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return getEditedItem().getEnchantments().size() > 0;
    }

    @Override
    protected ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return ItemBuilder.uiItem(originalItem.getType(), null, "Item has no enchantments.");
    }
}
