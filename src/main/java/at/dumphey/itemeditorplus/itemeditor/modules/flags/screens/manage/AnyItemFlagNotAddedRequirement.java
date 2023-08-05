package at.dumphey.itemeditorplus.itemeditor.modules.flags.screens.manage;

import at.dumphey.itemeditorplus.ui.requirements.Requirement;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class AnyItemFlagNotAddedRequirement extends Requirement {
    public AnyItemFlagNotAddedRequirement(Player player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return getEditedItem().getItemMeta() != null && getEditedItem().getItemMeta().getItemFlags()
                .size() < ItemFlag.values().length;
    }

    @Override
    protected ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return renderOriginalWithDescription(originalItem, "Item has all item flags.");
    }
}
