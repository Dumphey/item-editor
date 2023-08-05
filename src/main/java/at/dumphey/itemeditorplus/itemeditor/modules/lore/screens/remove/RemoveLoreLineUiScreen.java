package at.dumphey.itemeditorplus.itemeditor.modules.lore.screens.remove;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.itemeditor.modules.lore.screens.manage.ManageLoreUiScreen;
import at.dumphey.itemeditorplus.ui.UiPosition;
import at.dumphey.itemeditorplus.ui.items.BackUiItem;
import at.dumphey.itemeditorplus.ui.items.FillerUiItem;
import at.dumphey.itemeditorplus.ui.items.StaticUiItem;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RemoveLoreLineUiScreen extends UiScreen {
    public RemoveLoreLineUiScreen(Player player) {
        super(player, 45, "Remove lore line");
    }

    private List<String> getLore() {
        return ItemEditor.INSTANCE.getItem(player).getItemMeta().getLore();
    }

    @Override
    protected void onUpdate() {
        final List<String> lore = getLore();
        if (lore == null) {
            return;
        }
        final List<UiItem> items = new ArrayList<>();
        for (int i = 0; i < lore.size(); i++) {
            items.add(new RemoveLoreLineUiItem(this, player, i, lore.get(i)));
        }
        fill(UiPosition.of(0, 3), UiPosition.of(3, 8), items);
        setItem(UiPosition.of(2, 1), new StaticUiItem(this, player, ItemEditor.INSTANCE.getItem(player)));
        fillEmpty(UiPosition.of(0, 0), UiPosition.of(4, 2), new FillerUiItem(this, player));
        setItem(44, new BackUiItem(this, player, () -> ItemEditor.INSTANCE.getUiManager().open(player, new ManageLoreUiScreen(player))));
    }
}
