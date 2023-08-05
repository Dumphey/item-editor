package at.dumphey.itemeditor.itemeditor.modules.enchantments.items;

import at.dumphey.itemeditor.itemeditor.modules.enchantments.screens.manage.ManageEnchantmentsUiScreen;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenManageEnchantmentsUiItem extends UiItem {

    public OpenManageEnchantmentsUiItem(UiScreen screen, Player player) {
        super(screen, player);
        requirePermission("iep.edit.enchants");
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.of(Material.ENCHANTED_BOOK).withName("§eManage enchantments").withLore("§8§l* §8Add or remove enchantments").build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new ManageEnchantmentsUiScreen(player));
        }
    }
}
