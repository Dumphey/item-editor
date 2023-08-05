package at.dumphey.itemeditor.itemeditor.modules.flags.screens.manage;

import at.dumphey.itemeditor.ui.UiPosition;
import at.dumphey.itemeditor.ui.template.UiScreen;
import org.bukkit.entity.Player;

public class ManageItemFlagsUiScreen extends UiScreen {

    public ManageItemFlagsUiScreen(Player player) {
        super(player, 45, "Manage item flags");
    }

    @Override
    protected void onUpdate() {
        setItem(UiPosition.of(2, 4), new OpenAddItemFlagUiItem(this, player));
        setItem(UiPosition.of(2, 6), new OpenRemoveItemFlagUiItem(this, player));
        setBase();
    }
}
