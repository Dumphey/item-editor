package at.dumphey.itemeditorplus.itemeditor.modules.enchantments.screens.add;

import at.dumphey.itemeditorplus.ui.prompts.ChatPrompt;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import at.dumphey.itemeditorplus.utils.Messages;
import at.dumphey.itemeditorplus.itemeditor.modules.enchantments.screens.manage.ManageEnchantmentsUiScreen;
import at.dumphey.itemeditorplus.utils.NameUtils;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class EnterEnchantmentLevelPrompt extends ChatPrompt {

    private final Enchantment enchantment;

    public EnterEnchantmentLevelPrompt(Player player, Enchantment enchantment) {
        super(player,
                Messages.CHAT_PREFIX + "Enter a level for " + NameUtils.enumToFriendlyName(enchantment.getKey().getKey()),
                true);
        this.enchantment = enchantment;
    }

    @Override
    protected void onCancelled() {
        open(new ManageEnchantmentsUiScreen(player));
    }

    @Override
    protected void onFulfilled(String input) {
        int level = Integer.parseInt(input);
        modifyItem(item -> ItemBuilder.of(item).withEnchantment(enchantment, level).build());
        Messages.send(player,
                "Added enchantment §o" + NameUtils.enumToFriendlyName(
                        enchantment.getKey().getKey()) + " " + level + "§f.");
        player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
        open(new ManageEnchantmentsUiScreen(player));
    }

    @Override
    protected boolean validateInput(String input) {
        try {
            int i = Integer.parseInt(input.trim());
            if (i < 1) {
                return false;
            }
        } catch (NumberFormatException ex) {
            return false;
        }

        return true;
    }
}
