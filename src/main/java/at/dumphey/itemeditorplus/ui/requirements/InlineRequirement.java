package at.dumphey.itemeditorplus.ui.requirements;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public class InlineRequirement extends Requirement {

    private final Predicate<Player> isFulfilled;
    private final BiFunction<Player, ItemStack, ItemStack> renderNotFulfilledItem;

    public InlineRequirement(Player player, Predicate<Player> isFulfilled, BiFunction<Player, ItemStack, ItemStack> renderNotFulfilledItem) {
        super(player);
        this.isFulfilled = isFulfilled;
        this.renderNotFulfilledItem = renderNotFulfilledItem;
    }

    @Override
    public boolean isFulfilled() {
        return isFulfilled.test(player);
    }

    @Override
    protected ItemStack onRenderNotFulfilledItem(ItemStack originalItem) {
        return renderNotFulfilledItem.apply(player, originalItem);
    }

}
