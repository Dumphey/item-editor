package at.dumphey.itemeditor.itemeditor.modules.book.screens.manage;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.ui.prompts.ChatPrompt;
import at.dumphey.itemeditor.utils.ItemBuilder;
import at.dumphey.itemeditor.utils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BookMeta;

public class SetBookAuthorPrompt extends ChatPrompt {
    public SetBookAuthorPrompt(Player player) {
        super(player, Messages.CHAT_PREFIX + "Enter the name of the new author:", true);
    }

    @Override
    protected void onCancelled() {
        open(new ManageBookUiScreen(player));
    }

    @Override
    protected void onFulfilled(String input) {
        ItemEditor.INSTANCE.modifyItem(player, item -> ItemBuilder.of(item).editMeta(meta -> {
            BookMeta bookMeta = (BookMeta) meta;
            bookMeta.setAuthor(input);
        }).build());
        Messages.send(player, "Book's author set to: §o" + input);
        open(new ManageBookUiScreen(player));
    }

    @Override
    protected boolean validateInput(String input) {
        return true;
    }
}
