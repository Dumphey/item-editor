package at.dumphey.itemeditor.itemeditor.modules.potions.screens.add;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.modules.potions.screens.manage.ManagePotionUiScreen;
import at.dumphey.itemeditor.ui.prompts.ChatPrompt;
import at.dumphey.itemeditor.utils.Messages;
import at.dumphey.itemeditor.utils.NameUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SetPotionEffectStrengthPrompt extends ChatPrompt {

    private final PotionEffectType type;
    private final int duration;

    public SetPotionEffectStrengthPrompt(Player player, PotionEffectType type, int duration) {
        super(player, Messages.CHAT_PREFIX + "Enter a strength for the potion effect:", true);
        this.type = type;
        this.duration = duration;
    }

    @Override
    protected void onCancelled() {
        open(new ManagePotionUiScreen(player));
    }

    @Override
    protected void onFulfilled(String input) {
        int strength = Integer.parseInt(input.trim());
        ItemEditor.INSTANCE.modifyItem(player, item -> {
            PotionMeta meta = (PotionMeta) item.getItemMeta();
            meta.addCustomEffect(new PotionEffect(type, duration * 20, strength - 1), true);
            item.setItemMeta(meta);
            return item;
        });
        Messages.send(player, "Added potion effect " + NameUtils.enumToFriendlyName(
                type.getName()) + " " + strength + " with a duration of " + duration + " seconds.");
    }

    @Override
    public void onComplete() {
        open(new ManagePotionUiScreen(player));
    }

    @Override
    protected boolean validateInput(String input) {
        try {
            int val = Integer.parseInt(input.trim());
            if (val < 1) {
                player.sendMessage("§c§oStrength needs to be >= 1.");
                return false;
            }
        } catch (NumberFormatException exception) {
            player.sendMessage("§c§oInvalid strength: " + input);
            return false;
        }

        return true;
    }
}
