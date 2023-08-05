package at.dumphey.itemeditor.itemeditor.modules.book.screens.manage;

import at.dumphey.itemeditor.itemeditor.modules.book.screens.generation.SetBookGenerationUiScreen;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenSetBookGenerationUiItem extends UiItem {
    public OpenSetBookGenerationUiItem(UiScreen screen, Player player) {
        super(screen, player);
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.FEATHER, "Set generation", "Original, Copy of copy, ...");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new SetBookGenerationUiScreen(player));
        }
    }
}
