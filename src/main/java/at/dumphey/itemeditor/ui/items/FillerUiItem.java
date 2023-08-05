package at.dumphey.itemeditor.ui.items;

import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class FillerUiItem extends StaticUiItem {
    public FillerUiItem(UiScreen screen, Player player) {
        super(screen, player, ItemBuilder.of(Material.BLACK_STAINED_GLASS_PANE).withName("§0").withAllHideFlags().build());
    }
}
