package at.dumphey.itemeditor.itemeditor.modules.skull;

import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class SetSkullNameUiItem extends UiItem {
    public SetSkullNameUiItem(UiScreen screen, Player player) {
        super(screen, player);
        requirePermission("iep.edit.skull-name");
        require(new ItemIsPlayerSkullRequirement(player));
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItemBuilder(Material.PLAYER_HEAD, "Set skull name",
                        "Set the player name \nof this skull item.")
                .withSkullName("Dumphey").build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            prompt(new SetSkullPlayerNamePrompt(player));
        }
    }
}
