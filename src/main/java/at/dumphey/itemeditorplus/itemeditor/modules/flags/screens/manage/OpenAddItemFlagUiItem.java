package at.dumphey.itemeditorplus.itemeditor.modules.flags.screens.manage;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.itemeditor.modules.flags.screens.add.AddItemFlagUiScreen;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenAddItemFlagUiItem extends UiItem {
    public OpenAddItemFlagUiItem(UiScreen screen, Player player) {
        super(screen, player);
        require(new AnyItemFlagNotAddedRequirement(player));
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItemBuilder(ItemEditor.INSTANCE.getCompatUtils().getAddItemFlagUiItemMaterial(),
                "§eAdd item flag", "Add an item flag").withAllHideFlags().build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.getUiManager().open(player, new AddItemFlagUiScreen(player));
        }
    }
}
