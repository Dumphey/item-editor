package at.dumphey.itemeditor.itemeditor.modules.flags.screens.remove;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.modules.flags.ItemFlagUtils;
import at.dumphey.itemeditor.itemeditor.modules.flags.screens.manage.ManageItemFlagsUiScreen;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import at.dumphey.itemeditor.utils.Messages;
import at.dumphey.itemeditor.utils.NameUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class RemoveItemFlagUiItem extends UiItem {
    private final ItemFlag itemFlag;
    private final String name;

    public RemoveItemFlagUiItem(UiScreen screen, Player player, ItemFlag itemFlag) {
        super(screen, player);
        this.itemFlag = itemFlag;
        name = NameUtils.capitalize(itemFlag.name().replace("_", " ").toLowerCase());
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.of(ItemFlagUtils.getMaterial(itemFlag))
                .withName("§e" + name)
                .withLore("§8§l* §8Click to §cremove §8the item flag.")
                .build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType == ClickType.LEFT) {
            ItemEditor.INSTANCE.modifyItem(player, item -> ItemBuilder.of(item).removeItemFlag(itemFlag).build());
            Messages.send(player, "Removed item flag §o" + name + "§f.");
            open(new ManageItemFlagsUiScreen(player));
        }
    }
}
