package at.dumphey.itemeditorplus.itemeditor.modules.skull;

import at.dumphey.itemeditorplus.ui.requirements.Requirement;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemIsPlayerSkullRequirement extends Requirement {
    protected ItemIsPlayerSkullRequirement(Player player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return getEditedItem().getType() == Material.PLAYER_HEAD || getEditedItem().getType() == Material.PLAYER_WALL_HEAD;
    }

    @Override
    protected ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return renderOriginalWithDescription(originalItem, "Item is not a player head.");
    }
}
