package at.dumphey.itemeditorplus.utils;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;

import java.util.function.Consumer;

public class PotionBuilder {

    private final ItemStack itemStack;

    public PotionBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static PotionBuilder of(ItemStack itemStack) {
        return new PotionBuilder(itemStack);
    }

    public PotionBuilder withPotionEffect(PotionEffect effect) {
        return editMeta(meta -> meta.addCustomEffect(effect, true));
    }

    public PotionBuilder withColor(Color color) {
        return editMeta(meta -> meta.setColor(color));
    }

    public PotionBuilder withBaseData(PotionData data) {
        return editMeta(meta -> meta.setBasePotionData(data));
    }

    private PotionBuilder editMeta(Consumer<PotionMeta> meta) {
        final PotionMeta pm = (PotionMeta) itemStack.getItemMeta();
        meta.accept(pm);
        itemStack.setItemMeta(pm);
        return this;
    }

    public ItemBuilder item() {
        return ItemBuilder.of(itemStack);
    }

    public ItemStack build() {
        return itemStack;
    }

}
