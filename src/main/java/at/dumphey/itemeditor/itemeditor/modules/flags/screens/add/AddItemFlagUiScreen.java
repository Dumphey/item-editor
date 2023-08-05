package at.dumphey.itemeditor.itemeditor.modules.flags.screens.add;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.modules.flags.ItemFlagUtils;
import at.dumphey.itemeditor.itemeditor.modules.flags.screens.manage.ManageItemFlagsUiScreen;
import at.dumphey.itemeditor.ui.items.BackUiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddItemFlagUiScreen extends UiScreen {
    public AddItemFlagUiScreen(Player player) {
        super(player, 45, "Add item flag");
    }

    private List<ItemFlag> getMissingItemFlags() {
        return ItemFlagUtils.sorted(
                Stream.of(ItemFlag.values()).filter(itemFlag -> !hasItemFlag(itemFlag)).collect(Collectors.toList()));
    }

    private boolean hasItemFlag(ItemFlag itemFlag) {
        return ItemEditor.INSTANCE.getItem(player).getItemMeta().getItemFlags().contains(itemFlag);
    }

    @Override
    protected void onUpdate() {
        final List<ItemFlag> flags = getMissingItemFlags();
        for (int i = 0; i < flags.size(); i++) {
            setItem(i, new AddItemFlagUiItem(this, player, flags.get(i)));
        }
        setItem(44, new BackUiItem(this, player,
                () -> ItemEditor.INSTANCE.getUiManager().open(player, new ManageItemFlagsUiScreen(player))));
    }
}
