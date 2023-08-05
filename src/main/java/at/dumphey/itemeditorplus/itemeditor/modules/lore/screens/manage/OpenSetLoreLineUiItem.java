package at.dumphey.itemeditorplus.itemeditor.modules.lore.screens.manage;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import at.dumphey.itemeditorplus.itemeditor.modules.lore.screens.set.SetLoreLineUiScreen;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenSetLoreLineUiItem extends UiItem {
    public OpenSetLoreLineUiItem(UiScreen screen, Player player) {
        super(screen, player);
        require(new ItemHasLoreLineRequirement(player));
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.FEATHER, "Set line", "Set a line of the lore");
    }


    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.getUiManager().open(player, new SetLoreLineUiScreen(player));
        }
    }
}
