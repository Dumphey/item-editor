package at.dumphey.itemeditorplus.itemeditor.modules.attributes.screens.remove;

import at.dumphey.itemeditorplus.compatibility.CompatUtils;
import at.dumphey.itemeditorplus.itemeditor.modules.attributes.items.AttributeUiItem;
import at.dumphey.itemeditorplus.itemeditor.modules.attributes.screens.manage.ManageAttributesUiScreen;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import at.dumphey.itemeditorplus.utils.Messages;
import at.dumphey.itemeditorplus.utils.NameUtils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class RemoveAttributeUiItem extends AttributeUiItem {

    private final Attribute attribute;
    private final AttributeModifier modifier;

    public RemoveAttributeUiItem(UiScreen screen, Player player, Attribute attribute, AttributeModifier modifier) {
        super(screen, player);
        this.attribute = attribute;
        this.modifier = modifier;
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.of(CompatUtils.either(fancyAttributeMaterials.get(attribute), Material.PAPER))
                .withName("§e" + NameUtils.enumToFriendlyName(attribute))
                .withLore("§fSlot: §e" + NameUtils.enumToFriendlyName(modifier.getSlot()),
                        "§fOperation: §e" + NameUtils.enumToFriendlyName(modifier.getOperation()),
                        "§fValue: §e" + modifier.getAmount(), "", "§8§l* §8Click to §cremove §8attribute")
                .build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            modifyItem(item -> ItemBuilder.of(item).withoutAttribute(attribute, modifier).build());
            Messages.send(player, "Removed attribute modifier §o" + NameUtils.enumToFriendlyName(attribute));
            open(new ManageAttributesUiScreen(player));
        }
    }
}
