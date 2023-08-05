package at.dumphey.itemeditorplus.itemeditor.modules.potions.screens.remove;

import at.dumphey.itemeditorplus.itemeditor.modules.potions.screens.manage.ManagePotionUiScreen;
import at.dumphey.itemeditorplus.ui.items.BackUiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

public class RemovePotionEffectUiScreen extends UiScreen {
    public RemovePotionEffectUiScreen(Player player) {
        super(player, 45, "Remove potion effect");
    }


    @Override
    protected void onUpdate() {
        final PotionMeta meta = (PotionMeta) getEditedItem().getItemMeta();
        int index = 0;
        for (PotionEffect effect : meta.getCustomEffects()) {
            setItem(index, new RemovePotionEffectUiItem(this, player, effect));
            index++;
        }
        setItem(44, new BackUiItem(this, player, () -> open(new ManagePotionUiScreen(player))));
    }
}
