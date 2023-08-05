package at.dumphey.itemeditorplus.ui.template;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.ui.prompts.ChatPrompt;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public abstract class UiComponent {
    protected final Player player;

    protected UiComponent(Player player) {
        this.player = player;
    }

    protected ItemStack getEditedItem() {
        return ItemEditor.INSTANCE.getItem(player);
    }

    protected void open(UiScreen screen) {
        ItemEditor.INSTANCE.getUiManager().open(player, screen);
    }

    protected void modifyItem(Function<ItemStack, ItemStack> modifier) {
        ItemEditor.INSTANCE.modifyItem(player, modifier);
    }

    protected void prompt(ChatPrompt prompt) {
        ItemEditor.INSTANCE.getPromptHandler().setPrompt(player, prompt);
    }
}
