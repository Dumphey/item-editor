package at.dumphey.itemeditorplus.itemeditor.modules.potions.items;

import at.dumphey.itemeditorplus.ui.requirements.Requirement;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

public class ItemIsPotionRequirement extends Requirement {
    protected ItemIsPotionRequirement(Player player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return getEditedItem().getItemMeta() instanceof PotionMeta;
    }

    @Override
    protected ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return renderOriginalWithDescription(originalItem, "Item is not a potion/...");
    }
}
