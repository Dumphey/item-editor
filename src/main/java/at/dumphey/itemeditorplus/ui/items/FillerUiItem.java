package at.dumphey.itemeditorplus.ui.items;

import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class FillerUiItem extends StaticUiItem {
    public FillerUiItem(UiScreen screen, Player player) {
        super(screen, player, ItemBuilder.of(Material.BLACK_STAINED_GLASS_PANE).withName("§0").withAllHideFlags().build());
    }
}
