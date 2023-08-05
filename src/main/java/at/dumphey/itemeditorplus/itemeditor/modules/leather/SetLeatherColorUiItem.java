package at.dumphey.itemeditorplus.itemeditor.modules.leather;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.itemeditor.modules.home.HomeUiScreen;
import at.dumphey.itemeditorplus.itemeditor.modules.utils.colors.ChooseColorUiScreen;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import at.dumphey.itemeditorplus.utils.Messages;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class SetLeatherColorUiItem extends UiItem {
    public SetLeatherColorUiItem(UiScreen screen, Player player) {
        super(screen, player);
        requirePermission("iep.edit.leather");
        require(new ItemIsLeatherRequirement(player));

    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.LEATHER_CHESTPLATE, "Set color", "Set the color of this leather item.");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new ChooseColorUiScreen(player, null, () -> {
                open(new HomeUiScreen(player));
            }, (color) -> {
                ItemEditor.INSTANCE.modifyItem(player, item -> ItemBuilder.of(item).editMeta(meta -> {
                    LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) meta;
                    leatherArmorMeta.setColor(color);
                }).build());
                Messages.send(player, "Set color to: " + color);
                open(new HomeUiScreen(player));
            }));
        }
    }
}
