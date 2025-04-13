package at.dumphey.itemeditor.itemeditor.modules.flags.screens.manage;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.modules.flags.screens.add.AddItemFlagUiScreen;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
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
        return ItemBuilder.uiItemBuilder(Material.WARPED_SIGN,
                "§eAdd item flag", "Add an item flag").withAllHideFlags().build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.getUiManager().open(player, new AddItemFlagUiScreen(player));
        }
    }
}
