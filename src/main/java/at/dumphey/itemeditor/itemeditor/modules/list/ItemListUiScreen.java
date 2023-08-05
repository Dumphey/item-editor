package at.dumphey.itemeditor.itemeditor.modules.list;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.modules.list.items.GetItemFromListUiItem;
import at.dumphey.itemeditor.itemeditor.storage.SerializedItem;
import at.dumphey.itemeditor.ui.UiPosition;
import at.dumphey.itemeditor.ui.items.ActionUiItem;
import at.dumphey.itemeditor.ui.items.FillerUiItem;
import at.dumphey.itemeditor.ui.items.StaticUiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ItemListUiScreen extends UiScreen {

    private int page;

    public ItemListUiScreen(Player player) {
        super(player, 45, "Item List");
    }

    private List<SerializedItem> getItems() {
        return ItemEditor.INSTANCE.getItemStorage().getItems().stream().sorted(Comparator.comparing(
                SerializedItem::getName)).collect(Collectors.toList());
    }

    private int getPageCount() {
        int pages = (getItems().size() / 36);
        if (getItems().size() % 36 != 0) {
            pages++;
        }
        return pages;
    }

    @Override
    protected void onUpdate() {
        List<SerializedItem> items = getItems();
        int pos = 0;
        for (int i = page * 36; i < items.size() && i < (page + 1) * 36; i++) {
            setItem(pos++, new GetItemFromListUiItem(this, player, items.get(i)));
        }

        if (items.size() == 0) {
            setItem(22, new StaticUiItem(this, player, ItemBuilder.uiItem(Material.BARRIER, "No items saved.",
                    "Use §8§o/ie save <name>\n§8to save an item.")));
        }

        setItem(UiPosition.of(4, 4), new StaticUiItem(this, player,
                ItemBuilder.of(Material.PAPER).withName("§ePage §l" + (page + 1)).build()));

        if (page > 0) {
            setItem(UiPosition.of(4, 0),
                    new ActionUiItem(this, player, ItemBuilder.of(Material.ARROW).withName("§eGo to last page").build(),
                            () -> {
                                page--;
                                update();
                            }));
        }
        if (page < getPageCount() - 1) {
            setItem(UiPosition.of(4, 8),
                    new ActionUiItem(this, player, ItemBuilder.of(Material.ARROW).withName("§eGo to next page").build(),
                            () -> {
                                page++;
                                update();
                            }));
        }

        fillEmpty(UiPosition.of(4, 0), UiPosition.of(4, 8), new FillerUiItem(this, player));
    }
}
