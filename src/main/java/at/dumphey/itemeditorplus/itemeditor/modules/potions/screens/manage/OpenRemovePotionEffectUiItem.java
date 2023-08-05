package at.dumphey.itemeditorplus.itemeditor.modules.potions.screens.manage;

import at.dumphey.itemeditorplus.itemeditor.modules.potions.items.PotionEffectUiItem;
import at.dumphey.itemeditorplus.itemeditor.modules.potions.screens.remove.RemovePotionEffectUiScreen;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenRemovePotionEffectUiItem extends PotionEffectUiItem {
    public OpenRemovePotionEffectUiItem(UiScreen screen, Player player) {
        super(screen, player);
        require(new ItemHasPotionEffectRequirement(player));
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.SPONGE, "Remove potion effect", "Remove a potion effect");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new RemovePotionEffectUiScreen(player));
        }
    }
}
