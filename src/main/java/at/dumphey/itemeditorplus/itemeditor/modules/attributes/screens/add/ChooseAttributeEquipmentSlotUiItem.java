package at.dumphey.itemeditorplus.itemeditor.modules.attributes.screens.add;

import at.dumphey.itemeditorplus.compatibility.CompatUtils;
import at.dumphey.itemeditorplus.itemeditor.modules.attributes.items.AttributeUiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import at.dumphey.itemeditorplus.utils.NameUtils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class ChooseAttributeEquipmentSlotUiItem extends AttributeUiItem {


    private final Attribute attribute;
    private final EquipmentSlot equipmentSlot;

    public ChooseAttributeEquipmentSlotUiItem(UiScreen screen, Player player, Attribute attribute, EquipmentSlot equipmentSlot) {
        super(screen, player);
        this.attribute = attribute;
        this.equipmentSlot = equipmentSlot;
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(CompatUtils.either(fancyEquipmentSlotMaterials.get(equipmentSlot), Material.PAPER),
                NameUtils.enumToFriendlyName(equipmentSlot.name()), "Click to choose this equipment slot");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            open(new AddAttributeChooseOperationUiScreen(player, attribute, equipmentSlot));
        }
    }
}
