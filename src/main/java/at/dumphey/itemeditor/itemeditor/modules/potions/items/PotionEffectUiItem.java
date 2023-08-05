package at.dumphey.itemeditor.itemeditor.modules.potions.items;

import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.PotionMeta;

public abstract class PotionEffectUiItem extends UiItem {

    public PotionEffectUiItem(UiScreen screen, Player player) {
        super(screen, player);
    }

    public PotionMeta getPotionMeta() {
        return (PotionMeta) getEditedItem().getItemMeta();
    }
}
