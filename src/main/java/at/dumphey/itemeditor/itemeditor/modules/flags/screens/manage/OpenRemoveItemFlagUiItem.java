package at.dumphey.itemeditor.itemeditor.modules.flags.screens.manage;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.modules.flags.screens.remove.RemoveItemFlagUiScreen;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
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
        return ItemBuilder.of(Material.CRIMSON_SIGN)
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
