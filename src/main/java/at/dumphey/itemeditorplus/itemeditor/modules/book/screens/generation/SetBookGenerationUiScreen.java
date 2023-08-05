package at.dumphey.itemeditorplus.itemeditor.modules.book.screens.generation;

import at.dumphey.itemeditorplus.itemeditor.modules.book.screens.manage.ManageBookUiScreen;
import at.dumphey.itemeditorplus.ui.UiPosition;
import at.dumphey.itemeditorplus.ui.items.BackUiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SetBookGenerationUiScreen extends UiScreen {
    public SetBookGenerationUiScreen(Player player) {
        super(player, 45, "Set book generation");
    }

    @Override
    protected void onUpdate() {
        fill(UiPosition.of(0, 0), UiPosition.of(0, 8),
                Arrays.stream(BookMeta.Generation.values()).map(g -> new SetBookGenerationUiItem(this, player, g))
                        .collect(
                                Collectors.toList()));
        setItem(44, new BackUiItem(this, player, () -> open(new ManageBookUiScreen(player))));
    }
}
