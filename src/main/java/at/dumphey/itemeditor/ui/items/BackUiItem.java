package at.dumphey.itemeditor.ui.items;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class BackUiItem extends UiItem {

    private final Runnable back;

    public BackUiItem(UiScreen screen, Player player, Runnable back) {
        super(screen, player);
        this.back = back;
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.ARROW, "§cBack", "Go back to the last page",
                "§7§oShift-click to go to homepage");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType == ClickType.SHIFT_LEFT || (clickType.isLeftClick() && back == null)) {
            ItemEditor.INSTANCE.openHome(player);
        } else if (clickType.isLeftClick()) {
            back.run();
        }
    }
}
