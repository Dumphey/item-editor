package at.dumphey.itemeditorplus.itemeditor.modules.enchantments.screens.manage;

import at.dumphey.itemeditorplus.itemeditor.modules.enchantments.screens.remove.RemoveEnchantmentUiScreen;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenRemoveEnchantmentUiItem extends UiItem {

    public OpenRemoveEnchantmentUiItem(UiScreen screen, Player player) {
        super(screen, player);
        require(new ItemHasEnchantmentRequirement(player));
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.of(Material.SPONGE)
                .withName("§eRemove enchantment")
                .withLore("§8§l* §8Remove an enchantment from this item.")
                .build();
    }


    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new RemoveEnchantmentUiScreen(player));
        }
    }
}
