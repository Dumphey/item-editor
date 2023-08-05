package at.dumphey.itemeditorplus.itemeditor.modules.flags.screens.manage;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.itemeditor.modules.flags.screens.remove.RemoveItemFlagUiScreen;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenRemoveItemFlagUiItem extends UiItem {
    public OpenRemoveItemFlagUiItem(UiScreen screen, Player player) {
        super(screen, player);
        require(new ItemHasItemFlagsRequirement(player));
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.of(ItemEditor.INSTANCE.getCompatUtils().getRemoveItemFlagUiItemMaterial())
                .withName("§eRemove item flag")
                .withAllHideFlags()
                .withLore("§8§l* §8Remove an item flag")
                .build();
    }


    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.getUiManager().open(player, new RemoveItemFlagUiScreen(player));
        }
    }
}
