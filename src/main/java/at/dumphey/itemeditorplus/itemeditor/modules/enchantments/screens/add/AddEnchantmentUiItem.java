package at.dumphey.itemeditorplus.itemeditor.modules.enchantments.screens.add;

import at.dumphey.itemeditorplus.itemeditor.modules.enchantments.EnchantUtils;
import at.dumphey.itemeditorplus.itemeditor.modules.enchantments.screens.add.EnterEnchantmentLevelPrompt;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import at.dumphey.itemeditorplus.utils.NameUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class AddEnchantmentUiItem extends UiItem {

    private final Enchantment enchantment;

    public AddEnchantmentUiItem(UiScreen screen, Player player, Enchantment enchantment) {
        super(screen, player);
        this.enchantment = enchantment;
    }


    @Override
    protected ItemStack onRender() {
        return ItemBuilder.of(EnchantUtils.getMaterial(enchantment))
                          .withName("§e" + NameUtils.enumToFriendlyName(enchantment.getKey().getKey()))
                          .withLore("§7Vanilla start level: §o" + enchantment.getStartLevel(),
                                  "§7Vanilla max level: §o" + enchantment.getMaxLevel(),
                                  "",
                                  "§8§l* §8Click to §aadd §8enchantment")
                          .build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {

            prompt(new EnterEnchantmentLevelPrompt(player, enchantment));
        }
    }
}
