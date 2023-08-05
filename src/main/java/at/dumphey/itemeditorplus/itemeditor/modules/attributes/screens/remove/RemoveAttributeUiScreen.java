package at.dumphey.itemeditorplus.itemeditor.modules.attributes.screens.remove;

import at.dumphey.itemeditorplus.itemeditor.modules.attributes.screens.manage.ManageAttributesUiScreen;
import at.dumphey.itemeditorplus.ui.items.BackUiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveAttributeUiScreen extends UiScreen {
    public RemoveAttributeUiScreen(Player player) {
        super(player, 45, "Remove attribute");
    }

    private static class AttributeValue {
        private final Attribute attribute;
        private final AttributeModifier modifier;

        private AttributeValue(Attribute attribute, AttributeModifier modifier) {
            this.attribute = attribute;
            this.modifier = modifier;
        }

    }

    private Multimap<Attribute, AttributeModifier> getModifiers() {
        Multimap<Attribute, AttributeModifier> modifiers = getEditedItem().getItemMeta().getAttributeModifiers();
        if (modifiers == null) modifiers = ArrayListMultimap.create();
        return modifiers;
    }

    private List<AttributeValue> getValues() {
        final List<AttributeValue> all = new ArrayList<>();
        final Multimap<Attribute, AttributeModifier> modifiers = getModifiers();
        for (Attribute attribute : modifiers.keySet()) {
            for (AttributeModifier modifier : modifiers.get(attribute)) {
                all.add(new AttributeValue(attribute, modifier));
            }
        }

        return all.stream().sorted(Comparator.comparing(a -> a.attribute)).collect(Collectors.toList());
    }

    @Override
    protected void onUpdate() {
        final List<AttributeValue> values = getValues();

        int i = 0;
        for (AttributeValue value : values) {
            setItem(i++, new RemoveAttributeUiItem(this, player, value.attribute, value.modifier));
        }

        setItem(44, new BackUiItem(this, player, () -> open(new ManageAttributesUiScreen(player))));
    }
}
