package at.dumphey.itemeditor.itemeditor.modules.attributes.screens.add;

import at.dumphey.itemeditor.compatibility.CompatUtils;
import at.dumphey.itemeditor.itemeditor.modules.attributes.items.AttributeUiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import at.dumphey.itemeditor.utils.NameUtils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ChooseAttributeTypeUiItem extends AttributeUiItem {


    private final Attribute attribute;

    public ChooseAttributeTypeUiItem(UiScreen screen, Player player, Attribute attribute) {
        super(screen, player);
        this.attribute = attribute;
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(CompatUtils.either(fancyAttributeMaterials.get(attribute), Material.PAPER),
                NameUtils.enumToFriendlyName(attribute.name()), "Click to choose this type");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new AddAttributeChooseEquipmentSlotUiScreen(player, attribute));
        }
    }
}
