package at.dumphey.itemeditor.ui.template;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.ui.UiPosition;
import at.dumphey.itemeditor.ui.items.BackUiItem;
import at.dumphey.itemeditor.ui.items.FillerUiItem;
import at.dumphey.itemeditor.ui.items.StaticUiItem;
import at.dumphey.itemeditor.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public abstract class UiScreen extends UiComponent {

    private final Inventory inventory;

    private final UiItem[] uiItems;

    public UiScreen(Player player, int inventorySize, String inventoryTitle) {
        super(player);
        inventory = Bukkit.createInventory(null, inventorySize, Messages.PREFIX + " " + inventoryTitle);
        uiItems = new UiItem[inventorySize];
    }

    public void render() {
        inventory.clear();
        for (int position = 0; position < uiItems.length; position++) {
            if (uiItems[position] == null) {
                continue;
            }
            inventory.setItem(position, uiItems[position].render());
        }
    }

    protected void setBackItem() {
        setItem(inventory.getSize() - 1, new BackUiItem(this, player, null));
    }

    protected void fillEmpty(UiItem filler) {
        fillEmpty(0, inventory.getSize() - 1, filler);
    }

    protected void fillEmptyWithFiller() {
        fillEmpty(new FillerUiItem(this, player));
    }

    protected void fillEmpty(int fromPosition, int toPosition, UiItem filler) {
        int fromX = fromPosition % 9;
        int fromY = fromPosition / 9;
        int toX = toPosition % 9;
        int toY = toPosition / 9;

        for (int y = fromY; y <= toY; y++) {
            for (int x = fromX; x <= toX; x++) {
                final int position = y * 9 + x;
                if (uiItems[position] == null) {
                    uiItems[position] = filler;
                }
            }
        }
    }

    protected void fill(int fromPosition, int toPosition, List<UiItem> items) {
        int fromX = fromPosition % 9;
        int fromY = fromPosition / 9;
        int toX = toPosition % 9;
        int toY = toPosition / 9;

        int i = 0;
        for (int y = fromY; y <= toY; y++) {
            for (int x = fromX; x <= toX; x++) {
                final int position = y * 9 + x;
                if (i >= items.size()) {
                    return;
                }
                uiItems[position] = items.get(i++);
            }
        }
    }

    protected void setBase() {
        setCurrentItem();
        setBackItem();
        fillEmptyWithFiller();
    }

    public void update() {
        Arrays.fill(uiItems, null);
        onUpdate();
        render();
    }

    protected abstract void onUpdate();

    protected void setItem(int position, UiItem item) {
        uiItems[position] = item;
    }

    protected void setItem(int y, int x, UiItem item) {
        setItem(UiPosition.of(y, x), item);
    }

    protected void setCurrentItem() {
        setStaticItem(UiPosition.of(2, 1), ItemEditor.INSTANCE.getItem(player));
    }

    protected void setStaticItem(int position, ItemStack itemStack) {
        uiItems[position] = new StaticUiItem(this, player, itemStack);
    }

    public void onClick(int position, ClickType clickType) {
        final UiItem item = uiItems[position];
        if (item == null) {
            return;
        }

        item.click(clickType);
    }

    public void show() {
        player.openInventory(inventory);
    }

    public void hide() {
        if (isInventory(player.getOpenInventory().getTopInventory())) {
            player.closeInventory();
        }
    }

    public boolean isInventory(Inventory inventory) {
        return this.inventory.equals(inventory);
    }

}
