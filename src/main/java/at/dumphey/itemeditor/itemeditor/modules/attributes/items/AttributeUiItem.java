package at.dumphey.itemeditor.itemeditor.modules.attributes.items;

import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;
import java.util.Map;

public abstract class AttributeUiItem extends UiItem {

    protected static final Map<EquipmentSlot, Material> fancyEquipmentSlotMaterials = new HashMap<>();
    protected static final Map<Attribute, Material> fancyAttributeMaterials = new HashMap<>();

    static {
        initEquipmentSlotMaterials();
        initFancyAttributeMaterials();
    }

    private static void initFancyAttributeMaterials() {
        fancyAttributeMaterials.put(Attribute.GENERIC_ARMOR, Material.IRON_CHESTPLATE);
        fancyAttributeMaterials.put(Attribute.GENERIC_ARMOR_TOUGHNESS, Material.DIAMOND_CHESTPLATE);
        fancyAttributeMaterials.put(Attribute.GENERIC_ATTACK_DAMAGE, Material.DIAMOND_SWORD);
        fancyAttributeMaterials.put(Attribute.GENERIC_ATTACK_SPEED, Material.WOODEN_SWORD);
        fancyAttributeMaterials.put(Attribute.GENERIC_FLYING_SPEED, Material.ELYTRA);
        fancyAttributeMaterials.put(Attribute.GENERIC_FOLLOW_RANGE, Material.WHEAT);
        fancyAttributeMaterials.put(Attribute.GENERIC_KNOCKBACK_RESISTANCE, Material.SLIME_BLOCK);
        fancyAttributeMaterials.put(Attribute.GENERIC_LUCK, Material.DIAMOND);
        fancyAttributeMaterials.put(Attribute.GENERIC_MAX_HEALTH, Material.ENCHANTED_GOLDEN_APPLE);
        fancyAttributeMaterials.put(Attribute.GENERIC_MOVEMENT_SPEED, Material.LEATHER_BOOTS);
        fancyAttributeMaterials.put(Attribute.HORSE_JUMP_STRENGTH, Material.HORSE_SPAWN_EGG);
        fancyAttributeMaterials.put(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS, Material.ZOMBIE_SPAWN_EGG);
    }

    private static void initEquipmentSlotMaterials() {
        fancyEquipmentSlotMaterials.put(EquipmentSlot.CHEST, Material.DIAMOND_CHESTPLATE);
        fancyEquipmentSlotMaterials.put(EquipmentSlot.HEAD, Material.GOLDEN_HELMET);
        fancyEquipmentSlotMaterials.put(EquipmentSlot.FEET, Material.CHAINMAIL_BOOTS);
        fancyEquipmentSlotMaterials.put(EquipmentSlot.OFF_HAND, Material.TORCH);
        fancyEquipmentSlotMaterials.put(EquipmentSlot.HAND, Material.IRON_PICKAXE);
        fancyEquipmentSlotMaterials.put(EquipmentSlot.LEGS, Material.IRON_LEGGINGS);
    }

    public AttributeUiItem(UiScreen screen, Player player) {
        super(screen, player);
    }

    protected Multimap<Attribute, AttributeModifier> getAttributes() {
        Multimap<Attribute, AttributeModifier> modifiers = getEditedItem().getItemMeta().getAttributeModifiers();
        if (modifiers == null) {
            modifiers = ArrayListMultimap.create();
        }
        return modifiers;
    }
}
