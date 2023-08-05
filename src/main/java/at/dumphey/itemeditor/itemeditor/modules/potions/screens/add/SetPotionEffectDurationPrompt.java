package at.dumphey.itemeditor.itemeditor.modules.potions.screens.add;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.modules.potions.screens.manage.ManagePotionUiScreen;
import at.dumphey.itemeditor.ui.prompts.ChatPrompt;
import at.dumphey.itemeditor.utils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class SetPotionEffectDurationPrompt extends ChatPrompt {

    private int duration;
    private final PotionEffectType type;

    public SetPotionEffectDurationPrompt(Player player, PotionEffectType type) {
        super(player, Messages.CHAT_PREFIX + "Enter the duration of the effect in seconds:", true);
        this.type = type;
    }

    @Override
    protected void onCancelled() {
        open(new ManagePotionUiScreen(player));
    }

    @Override
    protected void onFulfilled(String input) {
        duration = Integer.parseInt(input.trim());
    }

    @Override
    public void onComplete() {
        ItemEditor.INSTANCE.getPromptHandler()
                .setPrompt(player, new SetPotionEffectStrengthPrompt(player, type, duration));
    }

    @Override
    protected boolean validateInput(String input) {
        try {
            int val = Integer.parseInt(input.trim());
            if (val < 1) {
                player.sendMessage("§c§oDuration needs to be >= 0.");
                return false;
            }
        } catch (NumberFormatException exception) {
            player.sendMessage("§c§oInvalid duration: " + input);
            return false;
        }

        return true;
    }
}
