package at.dumphey.itemeditor.itemeditor.modules.leather;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.modules.home.HomeUiScreen;
import at.dumphey.itemeditor.itemeditor.modules.utils.colors.ChooseColorUiScreen;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import at.dumphey.itemeditor.utils.Messages;
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
