package at.dumphey.itemeditor.itemeditor.modules.potions.screens.manage;

import at.dumphey.itemeditor.itemeditor.modules.potions.items.PotionEffectUiItem;
import at.dumphey.itemeditor.itemeditor.modules.potions.screens.remove.RemovePotionEffectUiScreen;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
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
