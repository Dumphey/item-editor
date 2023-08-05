package at.dumphey.itemeditor.itemeditor.modules.potions.screens.manage;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.modules.potions.items.PotionEffectUiItem;
import at.dumphey.itemeditor.itemeditor.modules.utils.colors.ChooseColorUiScreen;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import at.dumphey.itemeditor.utils.Messages;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

public class OpenSetPotionColorUiItem extends PotionEffectUiItem {


    public OpenSetPotionColorUiItem(UiScreen screen, Player player) {
        super(screen, player);
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.LIME_DYE, "Set potion color", "Set a color for this potion.");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new ChooseColorUiScreen(player, null, () -> {
                open(new ManagePotionUiScreen(player));
            }, (color) -> {
                ItemEditor.INSTANCE.modifyItem(player, item -> {
                    PotionMeta potionMeta = getPotionMeta();
                    potionMeta.setColor(color);
                    item.setItemMeta(potionMeta);
                    return item;
                });
                Messages.send(player, "Color set to: " + color.toString());
                open(new ManagePotionUiScreen(player));
            }));
        }
    }
}
