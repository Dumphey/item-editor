package at.dumphey.itemeditorplus.ui.items;

import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ActionUiItem extends UiItem {

    protected final ItemStack itemStack;
    private final Runnable action;

    public ActionUiItem(UiScreen screen, Player player, ItemStack itemStack, Runnable action) {
        super(screen, player);
        this.itemStack = itemStack;
        this.action = action;
    }

    @Override
    public ItemStack onRender() {
        return itemStack;
    }

    @Override
    public void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            action.run();
        }
    }
}
