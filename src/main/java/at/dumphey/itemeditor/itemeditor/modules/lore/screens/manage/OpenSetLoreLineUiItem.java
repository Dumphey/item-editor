package at.dumphey.itemeditor.itemeditor.modules.lore.screens.manage;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import at.dumphey.itemeditor.itemeditor.modules.lore.screens.set.SetLoreLineUiScreen;
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
