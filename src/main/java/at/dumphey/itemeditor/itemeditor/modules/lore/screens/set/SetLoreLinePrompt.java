package at.dumphey.itemeditor.itemeditor.modules.lore.screens.set;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.modules.lore.screens.manage.ManageLoreUiScreen;
import at.dumphey.itemeditor.ui.prompts.ChatPrompt;
import at.dumphey.itemeditor.utils.ColorUtils;
import at.dumphey.itemeditor.utils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SetLoreLinePrompt extends ChatPrompt {

    private final int index;

    public SetLoreLinePrompt(Player player, int index) {
        super(player, Messages.CHAT_PREFIX + "Enter a new text for line " + (index + 1) + ". §7§o(To create an empty line, write §f&f§7§o)", true);
        this.index = index;
    }

    @Override
    protected void onCancelled() {
        ItemEditor.INSTANCE.getUiManager().open(player, new ManageLoreUiScreen(player));
    }

    @Override
    protected void onFulfilled(String input) {
        ItemEditor.INSTANCE.modifyItem(player, item -> {
            final ItemMeta meta = item.getItemMeta();
            final List<String> lore = meta.getLore();
            lore.set(index, ColorUtils.translateColors(input));
            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        });
        ItemEditor.INSTANCE.getUiManager().open(player, new ManageLoreUiScreen(player));
    }

    @Override
    protected boolean validateInput(String input) {
        return true;
    }
}
