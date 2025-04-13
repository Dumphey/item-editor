package at.dumphey.itemeditor.itemeditor.modules.enchantments.screens.add;

import at.dumphey.itemeditor.itemeditor.modules.enchantments.EnchantUtils;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import at.dumphey.itemeditor.utils.NameUtils;
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
