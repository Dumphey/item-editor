package at.dumphey.itemeditor.itemeditor.modules.lore.screens.manage;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class AddLoreLineUiItem extends UiItem {
    public AddLoreLineUiItem(UiScreen screen, Player player) {
        super(screen, player);
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.PAPER, "Add line", "Add a line to the lore");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.getPromptHandler().setPrompt(player, new AddLoreLinePrompt(player));
        }
    }
}
