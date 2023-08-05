package at.dumphey.itemeditorplus.ui.items;

import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StaticUiItem extends UiItem {

    private final ItemStack itemStack;

    public StaticUiItem(UiScreen screen, Player player, ItemStack itemStack) {
        super(screen, player);
        this.itemStack = itemStack;
    }

    @Override
    public ItemStack onRender() {
        return itemStack;
    }
}
