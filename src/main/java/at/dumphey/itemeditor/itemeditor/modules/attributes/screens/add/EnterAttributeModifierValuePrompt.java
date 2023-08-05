package at.dumphey.itemeditor.itemeditor.modules.attributes.screens.add;

import at.dumphey.itemeditor.itemeditor.modules.attributes.screens.manage.ManageAttributesUiScreen;
import at.dumphey.itemeditor.ui.prompts.ChatPrompt;
import at.dumphey.itemeditor.utils.ItemBuilder;
import at.dumphey.itemeditor.utils.Messages;
import at.dumphey.itemeditor.utils.NameUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

import java.util.UUID;

public class EnterAttributeModifierValuePrompt extends ChatPrompt {

    private final Attribute attribute;
    private final AttributeModifier.Operation operation;
    private final EquipmentSlot equipmentSlot;

    public EnterAttributeModifierValuePrompt(Player player, Attribute attribute, AttributeModifier.Operation operation, EquipmentSlot equipmentSlot) {
        super(player, Messages.CHAT_PREFIX + "Enter a numeric attribute modifier value:", true);
        this.attribute = attribute;
        this.operation = operation;
        this.equipmentSlot = equipmentSlot;
    }

    @Override
    protected void onCancelled() {
        open(new AddAttributeChooseOperationUiScreen(player, attribute, equipmentSlot));
    }

    @Override
    protected void onFulfilled(String input) {
        double val = Double.parseDouble(input);

        modifyItem(item -> ItemBuilder.of(item).withAttribute(attribute, new AttributeModifier(UUID.randomUUID(), attribute.name(), val, operation, equipmentSlot)).build());
        Messages.send(player, "Added attribute §o" + NameUtils.enumToFriendlyName(attribute) + "§f for slot §o" + NameUtils.enumToFriendlyName(equipmentSlot) + "§f with value §o" + val + " §fand operation §o" + NameUtils.enumToFriendlyName(operation) + "§f.");
        open(new ManageAttributesUiScreen(player));
    }

    @Override
    protected boolean validateInput(String input) {
        try {
            double val = Double.parseDouble(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
