package at.dumphey.itemeditor.itemeditor.modules.lore.screens.manage;

import at.dumphey.itemeditor.ui.UiPosition;
import at.dumphey.itemeditor.ui.template.UiScreen;
import org.bukkit.entity.Player;

public class ManageLoreUiScreen extends UiScreen {
    public ManageLoreUiScreen(Player player) {
        super(player, 45, "Manage lore");
    }

    @Override
    protected void onUpdate() {
        setItem(UiPosition.of(2, 4), new AddLoreLineUiItem(this, player));
        setItem(UiPosition.of(2, 5), new OpenSetLoreLineUiItem(this, player));
        setItem(UiPosition.of(2, 6), new OpenRemoveLoreLineUiItem(this, player));
        setBase();
    }
}
