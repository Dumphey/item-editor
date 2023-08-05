package at.dumphey.itemeditorplus.itemeditor.modules.utils.colors;

import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.function.Consumer;

public class ChooseColorUiItem extends UiItem {

    private final Color color;
    private final Consumer<Color> onChosen;

    public ChooseColorUiItem(UiScreen screen, Player player, Color color, Consumer<Color> onChosen) {
        super(screen, player);
        this.color = color;
        this.onChosen = onChosen;
    }

    @Override
    protected ItemStack onRender() {
        final ItemStack item = ItemBuilder.of(Material.LEATHER_CHESTPLATE)
                                          .withName("§fClick to choose").build();
        final ItemMeta meta = item.getItemMeta();
        ((LeatherArmorMeta) meta).setColor(color);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            onChosen.accept(color);
        }
    }
}
