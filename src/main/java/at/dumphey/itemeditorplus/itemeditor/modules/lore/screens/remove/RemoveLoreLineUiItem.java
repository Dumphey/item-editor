package at.dumphey.itemeditorplus.itemeditor.modules.lore.screens.remove;

import at.dumphey.itemeditorplus.utils.ItemBuilder;
import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.itemeditor.modules.lore.screens.manage.ManageLoreUiScreen;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class RemoveLoreLineUiItem extends UiItem {

    private final int index;
    private final String content;

    public RemoveLoreLineUiItem(UiScreen screen, Player player, int index, String content) {
        super(screen, player);
        this.index = index;
        this.content = content;
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.of(Material.PAPER).withName("§eLine §l" + (index + 1)).withLore("", content, "", "§8§l* §8Click to §cremove §8this line.").build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.modifyItem(player, item -> {
                final ItemMeta meta = item.getItemMeta();
                final List<String> lore = meta.getLore();
                lore.remove(index);
                meta.setLore(lore);
                item.setItemMeta(meta);
                return item;
            });
            ItemEditor.INSTANCE.getUiManager().open(player, new ManageLoreUiScreen(player));
        }
    }
}
