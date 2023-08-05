package at.dumphey.itemeditorplus.itemeditor.modules.attributes.screens.add;

import at.dumphey.itemeditorplus.itemeditor.modules.attributes.screens.manage.ManageAttributesUiScreen;
import at.dumphey.itemeditorplus.ui.items.BackUiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.UiUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class AddAttributeChooseTypeUiScreen extends UiScreen {
    public AddAttributeChooseTypeUiScreen(Player player) {
        super(player, 45, "Choose attribute type");
    }

    private List<Attribute> getAttributes() {
        return UiUtils.sortedByEnumName(Arrays.asList(Attribute.values()));
    }

    @Override
    protected void onUpdate() {
        int i = 0;
        for (Attribute attribute : getAttributes()) {
            setItem(i++, new ChooseAttributeTypeUiItem(this, player, attribute));
        }
        setItem(44, new BackUiItem(this, player, () -> open(new ManageAttributesUiScreen(player))));
    }
}
