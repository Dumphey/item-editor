package at.dumphey.itemeditor.itemeditor.modules.durability;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class SetDurabilityItem extends UiItem {
    public SetDurabilityItem(UiScreen screen, Player player) {
        super(screen, player);
        requirePermission("iep.edit.durability");
        require(new ItemHasDurabilityRequirement(player));
    }

    @Override
    public ItemStack onRender() {
        return ItemBuilder.uiItemBuilder(Material.WOODEN_HOE, "Set durability", "Set durability of the item")
                .withDurability(getDamage()).withItemFlag(
                        ItemFlag.HIDE_ATTRIBUTES).build();
    }

    @Override
    public void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.getPromptHandler().setPrompt(player, new SetDurabilityPrompt(player));
        }
    }

    private int getDamage() {
        final ItemStack item = ItemEditor.INSTANCE.getItem(player);
        final ItemMeta meta = item.getItemMeta();
        if (meta instanceof Damageable) {
            return ((Damageable) meta).getDamage();
        }
        return 0;
    }
}
