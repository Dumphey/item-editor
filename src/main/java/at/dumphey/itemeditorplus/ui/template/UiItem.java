package at.dumphey.itemeditorplus.ui.template;

import at.dumphey.itemeditorplus.ui.requirements.InlineRequirement;
import at.dumphey.itemeditorplus.ui.requirements.PermissionRequirement;
import at.dumphey.itemeditorplus.ui.requirements.Requirement;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public abstract class UiItem extends UiComponent {

    protected final UiScreen screen;
    private final List<Requirement> requirements = new ArrayList<>();

    public UiItem(UiScreen screen, Player player) {
        super(player);
        this.screen = screen;
    }

    protected void require(Predicate<Player> isFulfilled, BiFunction<Player, ItemStack, ItemStack> renderNotFulfilledItem) {
        require(new InlineRequirement(player, isFulfilled, renderNotFulfilledItem));
    }

    protected void requirePermission(String permission) {
        require(new PermissionRequirement(player, permission));
    }

    protected void require(Requirement requirement) {
        requirements.add(requirement);
    }

    public final ItemStack render() {
        ItemStack itemStack = onRender();

        for (Requirement requirement : requirements) {
            if (!requirement.isFulfilled()) {
                return requirement.renderNotFulfilledItem(itemStack);
            }
        }

        if (itemStack == null) {
            throw new RuntimeException("UiItem " + this + " render() method returned null.");
        }

        return itemStack;
    }

    protected abstract ItemStack onRender();

    public final void click(ClickType type) {
        for (Requirement requirement : requirements) {
            if (!requirement.isFulfilled()) {
                return;
            }
        }

        onClick(type);
    }

    protected void onClick(ClickType clickType) {
    }
}
