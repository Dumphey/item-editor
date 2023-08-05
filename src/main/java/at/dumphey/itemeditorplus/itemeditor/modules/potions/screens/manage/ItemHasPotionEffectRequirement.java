package at.dumphey.itemeditorplus.itemeditor.modules.potions.screens.manage;

import at.dumphey.itemeditorplus.ui.requirements.Requirement;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

public class ItemHasPotionEffectRequirement extends Requirement {
    protected ItemHasPotionEffectRequirement(Player player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return getEditedItem().hasItemMeta() && getEditedItem().getItemMeta() instanceof PotionMeta && ((PotionMeta) getEditedItem().getItemMeta()).getCustomEffects()
                .size() > 0;
    }

    @Override
    protected ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return ItemBuilder.uiItem(originalItem.getType(), null, "Item has no potion effects.");
    }
}
