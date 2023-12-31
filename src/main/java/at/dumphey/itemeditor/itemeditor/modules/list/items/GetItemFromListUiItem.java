package at.dumphey.itemeditor.itemeditor.modules.list.items;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.storage.SerializedItem;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class GetItemFromListUiItem extends UiItem {

    private final SerializedItem item;

    public GetItemFromListUiItem(UiScreen screen, Player player, SerializedItem item) {
        super(screen, player);
        this.item = item;
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.of(item.getItemStack().clone())
                          .withAppendLore("", "§8§l* §e" + item.getName(), "  §7Left-click to §oget §7item",
                                  "  §7Right-click to §oedit §7item")
                          .build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        ItemEditor.INSTANCE.getUiManager().close(player);
        if (clickType.isLeftClick()) {
            player.getInventory().setItemInMainHand(item.getItemStack());
        } else if (clickType.isRightClick()) {
            ItemEditor.INSTANCE.edit(player, item.getItemStack());
        }
    }
}
