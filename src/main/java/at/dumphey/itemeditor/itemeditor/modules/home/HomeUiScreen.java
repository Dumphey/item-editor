package at.dumphey.itemeditor.itemeditor.modules.home;

import at.dumphey.itemeditor.itemeditor.modules.attributes.items.OpenManageAttributesUiItem;
import at.dumphey.itemeditor.itemeditor.modules.book.items.OpenManageBookUiItem;
import at.dumphey.itemeditor.itemeditor.modules.durability.SetDurabilityItem;
import at.dumphey.itemeditor.itemeditor.modules.enchantments.items.OpenManageEnchantmentsUiItem;
import at.dumphey.itemeditor.itemeditor.modules.flags.items.OpenManageItemFlagsUiItem;
import at.dumphey.itemeditor.itemeditor.modules.home.items.GetItemUiItem;
import at.dumphey.itemeditor.itemeditor.modules.leather.SetLeatherColorUiItem;
import at.dumphey.itemeditor.itemeditor.modules.lore.items.OpenManageLoreUiItem;
import at.dumphey.itemeditor.itemeditor.modules.name.ChangeItemNameUiItem;
import at.dumphey.itemeditor.itemeditor.modules.potions.items.OpenManagePotionUiItem;
import at.dumphey.itemeditor.itemeditor.modules.skull.SetSkullNameUiItem;
import at.dumphey.itemeditor.itemeditor.modules.unbreakable.ToggleUnbreakableUiItem;
import at.dumphey.itemeditor.ui.UiPosition;
import at.dumphey.itemeditor.ui.items.FillerUiItem;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeUiScreen extends UiScreen {


    public HomeUiScreen(Player player) {
        super(player, 45, "Home");
    }

    @Override
    protected void onUpdate() {
        setItem(UiPosition.of(2, 1), new GetItemUiItem(this, player));
        fill(UiPosition.of(1, 4), UiPosition.of(3, 7), getOptions());
        fillEmpty(UiPosition.of(0, 0), UiPosition.of(4, 2), new FillerUiItem(this, player));
    }

    private List<UiItem> getOptions() {
        return new ArrayList<>(Arrays.asList(new ChangeItemNameUiItem(this, player),
                new ToggleUnbreakableUiItem(this, player), new SetDurabilityItem(this, player),
                new OpenManageItemFlagsUiItem(this, player), new OpenManageLoreUiItem(this, player),
                new OpenManageEnchantmentsUiItem(this, player), new OpenManageAttributesUiItem(this, player),
                new SetSkullNameUiItem(this, player),
                new OpenManagePotionUiItem(this, player),
                new SetLeatherColorUiItem(this, player), new OpenManageBookUiItem(this, player)));
    }


}
