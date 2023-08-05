package at.dumphey.itemeditor.itemeditor.modules.enchantments.screens.manage;

import at.dumphey.itemeditor.ui.UiPosition;
import at.dumphey.itemeditor.ui.template.UiScreen;
import org.bukkit.entity.Player;

public class ManageEnchantmentsUiScreen extends UiScreen {

    public ManageEnchantmentsUiScreen(Player player) {
        super(player, 45, "Manage enchants");
    }

    @Override
    protected void onUpdate() {
        setItem(UiPosition.of(2, 4), new OpenAddEnchantmentUiItem(this, player));
        setItem(UiPosition.of(2, 6), new OpenRemoveEnchantmentUiItem(this, player));
        setBase();
    }
}
