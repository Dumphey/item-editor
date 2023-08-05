package at.dumphey.itemeditorplus.itemeditor.modules.attributes.screens.add;

import at.dumphey.itemeditorplus.ui.items.BackUiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.UiUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Arrays;
import java.util.List;

public class AddAttributeChooseOperationUiScreen extends UiScreen {

    private final Attribute attribute;
    private final EquipmentSlot equipmentSlot;

    public AddAttributeChooseOperationUiScreen(Player player, Attribute attribute, EquipmentSlot equipmentSlot) {
        super(player, 27, "Choose attribute operation");
        this.attribute = attribute;
        this.equipmentSlot = equipmentSlot;
    }

    private List<AttributeModifier.Operation> getOperations() {
        return UiUtils.sortedByEnumName(Arrays.asList(AttributeModifier.Operation.values()));
    }

    @Override
    protected void onUpdate() {
        int i = 0;
        for (AttributeModifier.Operation operation : getOperations()) {
            setItem(i++, new ChooseAttributeOperationUiItem(this, player, attribute, equipmentSlot, operation));
        }

        setItem(26, new BackUiItem(this, player, () -> open(new AddAttributeChooseEquipmentSlotUiScreen(player,
                attribute))));
    }
}
