package at.dumphey.itemeditorplus.itemeditor.modules.potions.items;

import at.dumphey.itemeditorplus.itemeditor.modules.potions.screens.manage.ManagePotionUiScreen;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenManagePotionUiItem extends UiItem {
    public OpenManagePotionUiItem(UiScreen screen, Player player) {
        super(screen, player);
        requirePermission("iep.edit.potion-effects");
        require(new ItemIsPotionRequirement(player));
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItemBuilder(Material.POTION, "Manage potion", "Add or remove potion effects",
                        "Set potion color")
                .withAllHideFlags()
                .build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new ManagePotionUiScreen(player));
        }
    }
}
