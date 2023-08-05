package at.dumphey.itemeditorplus.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ItemBuilder {

    private ItemStack itemStack;

    private ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static ItemBuilder of(ItemStack itemStack) {
        return new ItemBuilder(itemStack);
    }

    public static ItemBuilder of(Material material) {
        return ItemBuilder.of(new ItemStack(material));
    }

    public static ItemBuilder of(Material material, String name, String... lore) {
        return ItemBuilder.of(material).withName(name).withLore(lore);
    }

    public static ItemBuilder uiItemBuilder(Material material, String name, String... description) {
        List<String> lore = new ArrayList<>();
        if (description != null) {
            List<String[]> lines = Arrays.stream(description).map(s -> s.split("\n"))
                    .peek(arr -> {
                        arr[0] = Format.description(arr[0]);
                        for (int i = 1; i < arr.length; i++) {
                            arr[i] = Format.descriptionContinued(arr[i]);
                        }
                    }).collect(Collectors.toList());
            lines.forEach(subLines -> lore.addAll(Arrays.asList(subLines)));
        }
        ItemBuilder builder = ItemBuilder.of(material);
        if (name != null) {
            builder = builder.withName(Format.name(name));
        }
        return builder.withLore(lore);
    }

    public static ItemStack uiItem(Material material, String name, String... description) {
        return uiItemBuilder(material, name, description).build();
    }


    public static String getName(ItemStack itemStack, boolean includeColors) {
        String name = itemStack.getItemMeta().getDisplayName();
        if (!includeColors) {
            name = ChatColor.stripColor(name);
        }

        return name;
    }

    public ItemBuilder editMeta(Consumer<ItemMeta> consumer) {
        final ItemMeta meta = itemStack.getItemMeta();
        consumer.accept(meta);
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder withName(String name) {
        editMeta(meta -> meta.setDisplayName(ColorUtils.translateColors(name)));
        return this;
    }

    public ItemBuilder withLore(String... lore) {
        return withLore(Arrays.asList(lore));
    }

    public ItemBuilder withAppendLore(String... appendToLore) {
        return editMeta(meta -> {
            List<String> lore = meta.getLore();
            if (lore == null) lore = new ArrayList<>();
            lore = new ArrayList<>(lore);
            lore.addAll(Arrays.asList(appendToLore));
            meta.setLore(lore);
        });
    }

    public ItemBuilder withLore(List<String> lore) {
        editMeta(meta -> meta.setLore(
                lore.stream().map(ColorUtils::translateColors).collect(Collectors.toList())));
        return this;
    }

    public ItemBuilder withEnchantment(Enchantment enchantment, int level) {
        return editMeta(meta -> meta.addEnchant(enchantment, level, true));
    }

    public ItemBuilder withMaterial(Material material) {
        itemStack.setType(material);
        return this;
    }

    public ItemBuilder withDurability(int durability) {
        editMeta(meta -> {
            if (meta instanceof Damageable) {
                ((Damageable) meta).setDamage(
                        (itemStack.getType().getMaxDurability() - durability));
            }
        });

        return this;
    }

    public ItemBuilder withAllHideFlags() {
        editMeta(meta -> {
            meta.addItemFlags(ItemFlag.values());
        });
        return this;
    }

    public ItemStack build() {
        return itemStack;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        editMeta(meta -> meta.setUnbreakable(unbreakable));
        return this;
    }

    public ItemBuilder withItemFlag(ItemFlag itemFlag) {
        return editMeta(meta -> meta.addItemFlags(itemFlag));
    }

    public ItemBuilder removeItemFlag(ItemFlag itemFlag) {
        return editMeta(meta -> meta.removeItemFlags(itemFlag));
    }

    public ItemBuilder withoutEnchantment(Enchantment enchantment) {
        return editMeta(meta -> meta.removeEnchant(enchantment));
    }

    public ItemBuilder withSkullName(String skullName) {
        return editMeta(meta -> {
            final SkullMeta skullMeta = (SkullMeta) meta;
            skullMeta.setOwner(skullName);
        });
    }

    public PotionBuilder potion() {
        return PotionBuilder.of(itemStack);
    }

    public ItemBuilder withAttribute(Attribute attribute, AttributeModifier attributeModifier) {
        return editMeta(meta -> meta.addAttributeModifier(attribute, attributeModifier));
    }

    public ItemBuilder withoutAttribute(Attribute attribute, AttributeModifier modifier) {
        return editMeta(meta -> meta.removeAttributeModifier(attribute, modifier));
    }
}
