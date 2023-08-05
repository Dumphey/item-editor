package at.dumphey.itemeditor.itemeditor.modules.attributes.screens.add;

import at.dumphey.itemeditor.ui.items.BackUiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.UiUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Arrays;
import java.util.List;

public class AddAttributeChooseEquipmentSlotUiScreen extends UiScreen {



    private final Attribute attribute;

    public AddAttributeChooseEquipmentSlotUiScreen(Player player, Attribute attribute) {
        super(player, 27, "Choose equipment slot");
        this.attribute = attribute;
    }

    private List<EquipmentSlot> getSlots() {
        return UiUtils.sortedByEnumName(Arrays.asList(EquipmentSlot.values()));
    }

    @Override
    protected void onUpdate() {
        int i = 0;
        for (EquipmentSlot slot : getSlots()) {
            setItem(i++, new ChooseAttributeEquipmentSlotUiItem(this, player, attribute, slot));

        }
        setItem(26, new BackUiItem(this, player, () -> open(new AddAttributeChooseTypeUiScreen(player))));
    }
}
