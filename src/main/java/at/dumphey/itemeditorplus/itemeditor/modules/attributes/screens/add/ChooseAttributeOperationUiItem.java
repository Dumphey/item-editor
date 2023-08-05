package at.dumphey.itemeditorplus.itemeditor.modules.attributes.screens.add;

import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import at.dumphey.itemeditorplus.utils.NameUtils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class ChooseAttributeOperationUiItem extends UiItem {

    private final Attribute attribute;
    private final EquipmentSlot equipmentSlot;
    private final AttributeModifier.Operation operation;

    public ChooseAttributeOperationUiItem(UiScreen screen, Player player, Attribute attribute, EquipmentSlot equipmentSlot, AttributeModifier.Operation operation) {
        super(screen, player);
        this.attribute = attribute;
        this.equipmentSlot = equipmentSlot;
        this.operation = operation;
    }


    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.COMMAND_BLOCK, NameUtils.enumToFriendlyName(operation.name()),
                "Click to choose this operation");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            prompt(new EnterAttributeModifierValuePrompt(player, attribute, operation, equipmentSlot));
        }
    }
}
