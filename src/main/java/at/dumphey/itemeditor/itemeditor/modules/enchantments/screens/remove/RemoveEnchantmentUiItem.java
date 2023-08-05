package at.dumphey.itemeditor.itemeditor.modules.enchantments.screens.remove;

import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import at.dumphey.itemeditor.utils.Messages;
import at.dumphey.itemeditor.itemeditor.modules.enchantments.EnchantUtils;
import at.dumphey.itemeditor.itemeditor.modules.enchantments.screens.manage.ManageEnchantmentsUiScreen;
import at.dumphey.itemeditor.utils.NameUtils;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class RemoveEnchantmentUiItem extends UiItem {

    private final Enchantment enchantment;

    public RemoveEnchantmentUiItem(UiScreen screen, Player player, Enchantment enchantment) {
        super(screen, player);
        this.enchantment = enchantment;
    }

    private int getLevel() {
        return getEditedItem().getEnchantmentLevel(enchantment);
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.of(EnchantUtils.getMaterial(enchantment))
                          .withName("§e" + NameUtils.enumToFriendlyName(enchantment.getKey().getKey()))
                          .withEnchantment(enchantment, getLevel())
                          .withLore("§8§l* §8Click to §cremove §8enchantment.")
                          .build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            modifyItem(item -> ItemBuilder.of(item).withoutEnchantment(enchantment).build());
            Messages.send(player, "Removed enchantment §o" + NameUtils.enumToFriendlyName(
                    enchantment.getKey().getKey()) + " " + getLevel() + " §ffrom item.");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
            open(new ManageEnchantmentsUiScreen(player));
        }
    }
}
