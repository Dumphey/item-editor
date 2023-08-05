package at.dumphey.itemeditorplus.itemeditor.modules.enchantments.screens.manage;

import at.dumphey.itemeditorplus.ui.requirements.Requirement;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AnyEnchantmentNotAddedRequirement extends Requirement {
    protected AnyEnchantmentNotAddedRequirement(Player player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return getEditedItem().getEnchantments().size() < Enchantment.values().length;
    }

    @Override
    protected ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return renderOriginalWithDescription(originalItem, "Item has all enchantments.");
    }
}
