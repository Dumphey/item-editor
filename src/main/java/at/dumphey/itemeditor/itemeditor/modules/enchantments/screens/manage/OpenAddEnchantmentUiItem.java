package at.dumphey.itemeditor.itemeditor.modules.enchantments.screens.manage;

import at.dumphey.itemeditor.itemeditor.modules.enchantments.screens.add.AddEnchantmentUiScreen;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenAddEnchantmentUiItem extends UiItem {
    public OpenAddEnchantmentUiItem(UiScreen screen, Player player) {
        super(screen, player);
        require(new AnyEnchantmentNotAddedRequirement(player));
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.ENCHANTED_BOOK, "Add enchantment", "Add an enchantment");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new AddEnchantmentUiScreen(player));
        }
    }

}
