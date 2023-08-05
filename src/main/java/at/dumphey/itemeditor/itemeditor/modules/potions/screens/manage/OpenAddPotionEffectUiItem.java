package at.dumphey.itemeditor.itemeditor.modules.potions.screens.manage;

import at.dumphey.itemeditor.itemeditor.modules.potions.screens.add.AddPotionEffectChooseTypeUiScreen;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenAddPotionEffectUiItem extends UiItem {
    public OpenAddPotionEffectUiItem(UiScreen screen, Player player) {
        super(screen, player);
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItemBuilder(Material.POTION, "Add potion effect", "Add a potion effect")
                .withAllHideFlags()
                .build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new AddPotionEffectChooseTypeUiScreen(player));
        }
    }
}
