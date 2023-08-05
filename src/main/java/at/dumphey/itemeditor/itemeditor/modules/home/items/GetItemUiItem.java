package at.dumphey.itemeditor.itemeditor.modules.home.items;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class GetItemUiItem extends UiItem {


    public GetItemUiItem(UiScreen screen, Player player) {
        super(screen, player);
    }

    @Override
    public ItemStack onRender() {
        return ItemEditor.INSTANCE.getItem(player);
    }

    @Override
    public void onClick(ClickType clickType) {
        if (!clickType.isLeftClick()) {
            return;
        }

        ItemEditor.INSTANCE.finish(player);
    }
}
