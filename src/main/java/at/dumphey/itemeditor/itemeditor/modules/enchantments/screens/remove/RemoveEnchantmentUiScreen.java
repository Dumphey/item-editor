package at.dumphey.itemeditor.itemeditor.modules.enchantments.screens.remove;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.itemeditor.modules.enchantments.screens.manage.ManageEnchantmentsUiScreen;
import at.dumphey.itemeditor.ui.items.BackUiItem;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.UiUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveEnchantmentUiScreen extends UiScreen {
    public RemoveEnchantmentUiScreen(Player player) {
        super(player, 45, "Remove enchant");
    }

    private List<Enchantment> getMissingEnchants() {
        return Arrays.stream(Enchantment.values()).filter(ench -> hasEnchantment(ench)).collect(Collectors.toList());
    }

    private boolean hasEnchantment(Enchantment enchantment) {
        return ItemEditor.INSTANCE.getItem(player).getEnchantments().containsKey(enchantment);
    }

    @Override
    protected void onUpdate() {
        final List<Enchantment> enchantments = getMissingEnchants();
        final List<UiItem> items = UiUtils.sortedByMaterialAndName(
                enchantments.stream().map(e -> new RemoveEnchantmentUiItem(this, player, e)).collect(
                        Collectors.toList()));

        for (int i = 0; i < items.size(); i++) {
            setItem(i, items.get(i));
        }

        setItem(44, new BackUiItem(this, player, () -> open(new ManageEnchantmentsUiScreen(player))));
    }
}
