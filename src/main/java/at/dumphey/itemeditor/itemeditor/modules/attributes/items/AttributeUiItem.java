package at.dumphey.itemeditor.itemeditor.modules.attributes.items;

import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
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
        addFancyAttributeMaterial(Material.NETHERITE_CHESTPLATE,
                Attribute.ARMOR,                        // Base armor bonus
                Attribute.ARMOR_TOUGHNESS,               // Extra durability factor
                Attribute.KNOCKBACK_RESISTANCE,          // General knockback resistance
                Attribute.EXPLOSION_KNOCKBACK_RESISTANCE // Resistance against explosive knockback
        );

        addFancyAttributeMaterial(Material.DIAMOND_SWORD,
                Attribute.ATTACK_DAMAGE,     // Extra melee damage
                Attribute.ATTACK_KNOCKBACK,  // Knockback from melee hits
                Attribute.ATTACK_SPEED,      // Swing faster or slower
                Attribute.SWEEPING_DAMAGE_RATIO // Higher sweeping attack damage
        );

        addFancyAttributeMaterial(Material.NETHERITE_PICKAXE,
                Attribute.BLOCK_BREAK_SPEED, // Overall block break speed
                Attribute.MINING_EFFICIENCY  // Efficiency when using correct tools
        );

        addFancyAttributeMaterial(Material.STICK,
                Attribute.BLOCK_INTERACTION_RANGE, // Extended block reach
                Attribute.ENTITY_INTERACTION_RANGE  // Extended entity reach
        );

        addFancyAttributeMaterial(Material.FLINT_AND_STEEL,
                Attribute.BURNING_TIME // How long entities stay on fire
        );

        addFancyAttributeMaterial(Material.FEATHER,
                Attribute.FALL_DAMAGE_MULTIPLIER, // Multiplier for fall damage
                Attribute.SAFE_FALL_DISTANCE       // Distance before fall damage starts
        );

        addFancyAttributeMaterial(Material.ELYTRA,
                Attribute.FLYING_SPEED, // Speed while flying (e.g. with Elytra)
                Attribute.GRAVITY,      // Adjust how strongly gravity affects you
                Attribute.SCALE         // Change entity size
        );

        addFancyAttributeMaterial(Material.TURTLE_HELMET,
                Attribute.OXYGEN_BONUS,          // More air underwater
                Attribute.SUBMERGED_MINING_SPEED // Faster block-breaking underwater
        );

        addFancyAttributeMaterial(Material.LEATHER_BOOTS,
                Attribute.MOVEMENT_SPEED,      // Base walking speed
                Attribute.MOVEMENT_EFFICIENCY, // Move efficiently on difficult terrain
                Attribute.SNEAKING_SPEED       // Speed while sneaking
        );

        addFancyAttributeMaterial(Material.NETHERITE_BOOTS,
                Attribute.JUMP_STRENGTH, // How high you can jump
                Attribute.STEP_HEIGHT    // Height you can walk over without jumping
        );

        addFancyAttributeMaterial(Material.GOLDEN_APPLE,
                Attribute.MAX_ABSORPTION, // Maximum absorption hearts
                Attribute.MAX_HEALTH,     // Increase total HP
                Attribute.LUCK            // Improves luck-based loot or outcomes
        );

        addFancyAttributeMaterial(Material.LEAD,
                Attribute.FOLLOW_RANGE, // Mobs can track or follow from farther away
                Attribute.TEMPT_RANGE   // Mobs can be tempted from farther away
        );

        addFancyAttributeMaterial(Material.ZOMBIE_HEAD,
                Attribute.SPAWN_REINFORCEMENTS // Chance to spawn additional mobs
        );
    }

    private static void addFancyAttributeMaterial(Material material, Attribute... attributes) {
        for (Attribute attribute : attributes) {
            fancyAttributeMaterials.put(attribute, material);
        }
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


    public static Material getAttributeMaterial(Attribute attribute) {
        if (fancyAttributeMaterials.containsKey(attribute)) {
            return fancyAttributeMaterials.get(attribute);
        }
        return Material.PAPER;
    }

    public static Material getEquipmentSlotMaterial(EquipmentSlot equipmentSlot) {
        if (fancyEquipmentSlotMaterials.containsKey(equipmentSlot)) {
            return fancyEquipmentSlotMaterials.get(equipmentSlot);
        }
        return Material.IRON_HELMET;
    }
}
