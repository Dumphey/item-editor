package at.dumphey.itemeditor.itemeditor.modules.potions.screens.add;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.modules.potions.items.PotionEffectUiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import at.dumphey.itemeditor.utils.NameUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class ChoosePotionEffectTypeUiItem extends PotionEffectUiItem {

    private final PotionEffectType type;

    public ChoosePotionEffectTypeUiItem(UiScreen screen, Player player, PotionEffectType type) {
        super(screen, player);
        this.type = type;
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItemBuilder(Material.POTION, NameUtils.enumToFriendlyName(type.getName()),
                        "Click to choose type")
                .withAllHideFlags()
                .potion().withPotionEffect(type.createEffect(20 * 60, 0)).withColor(type.getColor())
                .build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.getPromptHandler().setPrompt(player, new SetPotionEffectDurationPrompt(player, type));
        }
    }
}
