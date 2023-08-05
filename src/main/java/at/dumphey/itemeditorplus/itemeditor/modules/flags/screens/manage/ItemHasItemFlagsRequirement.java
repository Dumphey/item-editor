package at.dumphey.itemeditorplus.itemeditor.modules.flags.screens.manage;

import at.dumphey.itemeditorplus.ui.requirements.Requirement;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemHasItemFlagsRequirement extends Requirement {
    protected ItemHasItemFlagsRequirement(Player player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return getEditedItem().hasItemMeta() && getEditedItem().getItemMeta().getItemFlags().size() > 0;
    }

    @Override
    protected ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return ItemBuilder.uiItem(originalItem.getType(), null, "Item has no item flags.");
    }
}
