package at.dumphey.itemeditorplus.itemeditor.modules.potions.screens.add;

import at.dumphey.itemeditorplus.itemeditor.modules.potions.screens.manage.ManagePotionUiScreen;
import at.dumphey.itemeditorplus.ui.items.BackUiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AddPotionEffectChooseTypeUiScreen extends UiScreen {
    public AddPotionEffectChooseTypeUiScreen(Player player) {
        super(player, 45, "Choose potion effect type");
    }

    private List<PotionEffectType> getTypes() {
        return Arrays.stream(PotionEffectType.values()).sorted(Comparator.comparing(PotionEffectType::getName)).collect(
                Collectors.toList());
    }

    @Override
    protected void onUpdate() {
        final List<PotionEffectType> types = getTypes();
        for (int i = 0; i < types.size(); i++) {
            setItem(i, new ChoosePotionEffectTypeUiItem(this, player, types.get(i)));
        }
        setItem(44, new BackUiItem(this, player, () -> open(new ManagePotionUiScreen(player))));
    }
}
