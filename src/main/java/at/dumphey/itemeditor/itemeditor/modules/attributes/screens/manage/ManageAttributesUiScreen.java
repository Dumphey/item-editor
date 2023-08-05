package at.dumphey.itemeditor.itemeditor.modules.attributes.screens.manage;

import at.dumphey.itemeditor.ui.UiPosition;
import at.dumphey.itemeditor.ui.template.UiScreen;
import org.bukkit.entity.Player;

public class ManageAttributesUiScreen extends UiScreen {
    public ManageAttributesUiScreen(Player player) {
        super(player, 45, "Manage attributes");
    }

    @Override
    protected void onUpdate() {
        setItem(UiPosition.of(2, 4), new OpenAddAttributeUiItem(this, player));
        setItem(UiPosition.of(2, 6), new OpenRemoveAttributeUiItem(this, player));
        setBase();
    }
}
