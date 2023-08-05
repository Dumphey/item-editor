package at.dumphey.itemeditor.itemeditor.modules.potions.screens.remove;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.modules.potions.items.PotionEffectUiItem;
import at.dumphey.itemeditor.itemeditor.modules.potions.screens.manage.ManagePotionUiScreen;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import at.dumphey.itemeditor.utils.NameUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

public class RemovePotionEffectUiItem extends PotionEffectUiItem {

    private final PotionEffect potionEffect;

    public RemovePotionEffectUiItem(UiScreen screen, Player player, PotionEffect potionEffect) {
        super(screen, player);
        this.potionEffect = potionEffect;
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItemBuilder(Material.POTION,
                        NameUtils.enumToFriendlyName(potionEffect.getType().getName()), "Click to §cremove §8effect.")
                .potion().withPotionEffect(potionEffect).item().build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.modifyItem(player, item -> ItemBuilder.of(item).editMeta(meta -> {
                PotionMeta potionMeta = (PotionMeta) meta;
                potionMeta.removeCustomEffect(potionEffect.getType());
            }).build());
            open(new ManagePotionUiScreen(player));
        }
    }
}
