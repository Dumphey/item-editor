package at.dumphey.itemeditor.itemeditor.modules.leather;

import at.dumphey.itemeditor.ui.requirements.Requirement;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemIsLeatherRequirement extends Requirement {

    private static final List<Material> leather = Arrays.asList(Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS,
            Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET);

    protected ItemIsLeatherRequirement(Player player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return leather.contains(getEditedItem().getType());
    }

    @Override
    protected ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return renderOriginalWithDescription(originalItem, "Item is not leather armor.");
    }
}
