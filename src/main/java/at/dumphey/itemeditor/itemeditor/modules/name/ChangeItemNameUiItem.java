package at.dumphey.itemeditor.itemeditor.modules.name;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ChangeItemNameUiItem extends UiItem {

    public ChangeItemNameUiItem(UiScreen screen, Player player) {
        super(screen, player);
        requirePermission("iep.edit.name");
    }

    @Override
    public ItemStack onRender() {
        return ItemBuilder.uiItem(Material.NAME_TAG, "Change name", "Change the name of the item");
    }

    @Override
    public void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.getPromptHandler()
                    .setPrompt(player, new InputNewItemNamePrompt(player));
        }
    }
}
