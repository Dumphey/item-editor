package at.dumphey.itemeditor.itemeditor.modules.durability;

import at.dumphey.itemeditor.utils.Messages;
import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.ui.prompts.ChatPrompt;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.entity.Player;

public class SetDurabilityPrompt extends ChatPrompt {

    public SetDurabilityPrompt(Player player) {
        super(player, Messages.CHAT_PREFIX + "Enter a durability value (§opositive number§f) for the item:", true);
    }

    @Override
    protected void onCancelled() {
        ItemEditor.INSTANCE.getUiManager().reopen(player);
    }

    @Override
    protected void onFulfilled(String input) {
        int damage = (int) Float.parseFloat(input);
        ItemEditor.INSTANCE.modifyItem(player, (item) -> ItemBuilder.of(item).withDurability(damage).build());
        Messages.send(player, "Durability set to §o" + damage + "§f.");
        ItemEditor.INSTANCE.getUiManager().reopen(player);
    }

    @Override
    protected boolean validateInput(String input) {
        boolean ok = true;
        try {
            final float val = Float.parseFloat(input.trim());
            if (val < 0) {
                ok = false;
            }
        } catch (NumberFormatException ex) {
            ok = false;
        }

        if (!ok) {
            player.sendMessage("§c§oInvalid input: " + input);
        }
        return ok;
    }
}
