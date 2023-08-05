package at.dumphey.itemeditorplus.ui.requirements;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Requirement {

    protected final Player player;

    protected Requirement(Player player) {
        this.player = player;
    }

    public abstract boolean isFulfilled();

    public ItemStack renderNotFulfilledItem(ItemStack originalItem) {
        ItemStack rendered = onRenderNotFulfilledItem(originalItem);

        if (rendered == null) {
            rendered = ItemBuilder.uiItem(originalItem.getType(), null);
        }

        if (rendered.getItemMeta() != null && (!rendered.getItemMeta().hasDisplayName() || rendered.getItemMeta()
                .getDisplayName().trim().equals(""))) {
            rendered = ItemBuilder.of(rendered)
                    .withName("§7§m" + ChatColor.stripColor(originalItem.getItemMeta().getDisplayName())).build();
        }

        return rendered;
    }

    protected ItemStack renderOriginalWithDescription(ItemStack originalItem, String... description) {
        return ItemBuilder.uiItem(originalItem.getType(), null, description);
    }

    protected abstract ItemStack onRenderNotFulfilledItem(ItemStack originalItem);

    protected ItemStack getEditedItem() {
        return ItemEditor.INSTANCE.getItem(player);
    }

}
