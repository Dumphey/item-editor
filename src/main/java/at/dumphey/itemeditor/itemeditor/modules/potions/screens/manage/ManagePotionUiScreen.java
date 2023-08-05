package at.dumphey.itemeditor.itemeditor.modules.potions.screens.manage;

import at.dumphey.itemeditor.ui.UiPosition;
import at.dumphey.itemeditor.ui.template.UiScreen;
import org.bukkit.entity.Player;

public class ManagePotionUiScreen extends UiScreen {
    public ManagePotionUiScreen(Player player) {
        super(player, 45, "Manage potion");
    }

    @Override
    protected void onUpdate() {
        setItem(UiPosition.of(2, 4), new OpenAddPotionEffectUiItem(this, player));
        setItem(UiPosition.of(2, 5), new OpenRemovePotionEffectUiItem(this, player));
        setItem(UiPosition.of(2, 6), new OpenSetPotionColorUiItem(this, player));
        setBase();
    }
}
