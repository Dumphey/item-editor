package at.dumphey.itemeditor.itemeditor.modules.durability;

import at.dumphey.itemeditor.ui.requirements.Requirement;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class ItemHasDurabilityRequirement extends Requirement {
    public ItemHasDurabilityRequirement(Player player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return getEditedItem().getItemMeta() instanceof Damageable;
    }

    @Override
    protected ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return ItemBuilder.uiItem(originalItem.getType(), null, "Item doesn't have durability.");
    }
}
