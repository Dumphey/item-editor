package at.dumphey.itemeditorplus.itemeditor.modules.enchantments.screens.add;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.itemeditor.modules.enchantments.screens.manage.ManageEnchantmentsUiScreen;
import at.dumphey.itemeditorplus.ui.items.BackUiItem;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.UiUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddEnchantmentUiScreen extends UiScreen {
    public AddEnchantmentUiScreen(Player player) {
        super(player, 45, "Add enchant");
    }

    private List<Enchantment> getMissingEnchants() {
        return Arrays.stream(Enchantment.values()).filter(ench -> !hasEnchantment(ench)).collect(Collectors.toList());
    }

    private boolean hasEnchantment(Enchantment enchantment) {
        return ItemEditor.INSTANCE.getItem(player).getEnchantments().containsKey(enchantment);
    }

    @Override
    protected void onUpdate() {
        final List<Enchantment> enchantments = getMissingEnchants();
        final List<UiItem> items = UiUtils.sortedByMaterialAndName(
                enchantments.stream().map(e -> new AddEnchantmentUiItem(this, player, e)).collect(
                        Collectors.toList()));

        for (int i = 0; i < items.size(); i++) {
            setItem(i, items.get(i));
        }

        setItem(44, new BackUiItem(this, player, () -> open(new ManageEnchantmentsUiScreen(player))));
    }
}
