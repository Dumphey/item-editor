package at.dumphey.itemeditorplus.itemeditor.modules.flags.screens.remove;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.itemeditor.modules.flags.ItemFlagUtils;
import at.dumphey.itemeditorplus.itemeditor.modules.flags.screens.manage.ManageItemFlagsUiScreen;
import at.dumphey.itemeditorplus.ui.items.BackUiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.List;

public class RemoveItemFlagUiScreen extends UiScreen {
    public RemoveItemFlagUiScreen(Player player) {
        super(player, 45, "Remove item flag");
    }

    private List<ItemFlag> getItemFlags() {
        return ItemFlagUtils.sorted(ItemEditor.INSTANCE.getItem(player).getItemMeta().getItemFlags());
    }


    @Override
    protected void onUpdate() {
        final List<ItemFlag> flags = getItemFlags();
        for (int i = 0; i < flags.size(); i++) {
            setItem(i, new RemoveItemFlagUiItem(this, player, flags.get(i)));
        }
        setItem(44, new BackUiItem(this, player,
                () -> ItemEditor.INSTANCE.getUiManager().open(player, new ManageItemFlagsUiScreen(player))));
    }
}
