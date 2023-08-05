package at.dumphey.itemeditorplus.itemeditor.modules.book.items;

import at.dumphey.itemeditorplus.ui.requirements.Requirement;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class ItemIsBookRequirement extends Requirement {
    protected ItemIsBookRequirement(Player player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return getEditedItem().getItemMeta() instanceof BookMeta;
    }

    @Override
    protected ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return renderOriginalWithDescription(originalItem, "Item is not a book.");
    }
}
