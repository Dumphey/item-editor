package at.dumphey.itemeditor.itemeditor.modules.book.screens.manage;

import at.dumphey.itemeditor.ui.UiPosition;
import at.dumphey.itemeditor.ui.template.UiScreen;
import org.bukkit.entity.Player;

public class ManageBookUiScreen extends UiScreen {

    public ManageBookUiScreen(Player player) {
        super(player, 45, "Manage book");
    }

    @Override
    protected void onUpdate() {
        setItem(UiPosition.of(2, 4), new SetBookAuthorUiItem(this, player));
        setItem(UiPosition.of(2, 6), new OpenSetBookGenerationUiItem(this, player));
        setBase();
    }
}
