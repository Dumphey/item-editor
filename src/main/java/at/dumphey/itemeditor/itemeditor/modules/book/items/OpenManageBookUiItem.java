package at.dumphey.itemeditor.itemeditor.modules.book.items;

import at.dumphey.itemeditor.itemeditor.modules.book.screens.manage.ManageBookUiScreen;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenManageBookUiItem extends UiItem {

    public OpenManageBookUiItem(UiScreen screen, Player player) {
        super(screen, player);
        requirePermission("iep.edit.book");
        require(new ItemIsBookRequirement(player));
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.of(Material.WRITTEN_BOOK).withName("§eManage book")
                .withLore("§8§l* §8Set author and generation").build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new ManageBookUiScreen(player));
        }
    }
}
