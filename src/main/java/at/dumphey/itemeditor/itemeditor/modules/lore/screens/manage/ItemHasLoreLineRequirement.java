package at.dumphey.itemeditor.itemeditor.modules.lore.screens.manage;

import at.dumphey.itemeditor.ui.requirements.Requirement;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemHasLoreLineRequirement extends Requirement {
    protected ItemHasLoreLineRequirement(Player player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return getEditedItem().hasItemMeta() && getEditedItem().getItemMeta().hasLore() && getEditedItem().getItemMeta()
                .getLore().size() > 0;
    }

    @Override
    protected ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return renderOriginalWithDescription(originalItem, "Item has no lore.");
    }
}
