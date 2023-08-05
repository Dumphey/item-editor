package at.dumphey.itemeditor.itemeditor.modules.name;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.ui.prompts.ChatPrompt;
import at.dumphey.itemeditor.utils.ColorUtils;
import at.dumphey.itemeditor.utils.ItemBuilder;
import at.dumphey.itemeditor.utils.Messages;
import org.bukkit.entity.Player;

public class InputNewItemNamePrompt extends ChatPrompt {

    public InputNewItemNamePrompt(Player player) {
        super(player, Messages.CHAT_PREFIX + "Enter a new name for the item:", true);
    }

    @Override
    protected void onCancelled() {
        ItemEditor.INSTANCE.getUiManager().reopen(player);
    }

    @Override
    protected void onFulfilled(String input) {
        final String newName = ColorUtils.translateColors(input);
        ItemEditor.INSTANCE.modifyItem(player, (item) -> ItemBuilder.of(item).withName(newName).build());
        player.sendMessage(Messages.CHAT_PREFIX + "Name set to '" + newName + "§f'.");
        ItemEditor.INSTANCE.getUiManager().reopen(player);
    }

    @Override
    protected boolean validateInput(String input) {
        return true;
    }
}
