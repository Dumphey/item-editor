package at.dumphey.itemeditor.itemeditor.modules.book.screens.manage;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class SetBookAuthorUiItem extends UiItem {
    public SetBookAuthorUiItem(UiScreen screen, Player player) {
        super(screen, player);
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.PLAYER_HEAD, "Set author", "Set the name of the author.");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.getPromptHandler().setPrompt(player, new SetBookAuthorPrompt(player));
        }
    }
}
