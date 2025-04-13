package at.dumphey.itemeditor.itemeditor.modules.enchantments;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

public class EnchantUtils {

    private static final Map<Enchantment, Material> fancyMaterials = new HashMap<>();

    static {
        addFancyMaterial(Material.BOW,
                Enchantment.INFINITY,      // Infinite arrows
                Enchantment.FLAME,         // Arrows ignite targets
                Enchantment.PUNCH,         // Knockback for arrows
                Enchantment.POWER          // Extra damage with arrows
        );

        addFancyMaterial(Material.CROSSBOW,
                Enchantment.QUICK_CHARGE,  // Faster reload
                Enchantment.MULTISHOT,     // Fires 3 projectiles
                Enchantment.PIERCING       // Projectiles pierce multiple entities
        );

        addFancyMaterial(Material.TRIDENT,
                Enchantment.LOYALTY,       // Returns to thrower
                Enchantment.RIPTIDE,       // Propels you forward in water/rain
                Enchantment.IMPALING,      // Extra damage to ocean mobs
                Enchantment.CHANNELING     // Summons lightning (if stormy)
        );

        addFancyMaterial(Material.DIAMOND_SWORD,
                Enchantment.SHARPNESS,             // Increased damage (all targets)
                Enchantment.SMITE,                 // Increased damage (undead)
                Enchantment.BANE_OF_ARTHROPODS,    // Increased damage (arthropods)
                Enchantment.FIRE_ASPECT,           // Ignites targets
                Enchantment.KNOCKBACK,             // Pushes mobs away
                Enchantment.SWEEPING_EDGE,         // Extra sweep-attack damage
                Enchantment.LOOTING               // Extra mob loot
        );

        addFancyMaterial(Material.MACE,
                Enchantment.BREACH,        // Reduces armor effectiveness (maces)
                Enchantment.DENSITY        // Increases fall impact from maces
        );

        addFancyMaterial(Material.FISHING_ROD,
                Enchantment.LUCK_OF_THE_SEA,   // Fewer junk catches
                Enchantment.LURE               // Faster bite rate
        );

        addFancyMaterial(Material.IRON_HELMET,
                Enchantment.AQUA_AFFINITY, // Mine blocks faster underwater
                Enchantment.RESPIRATION    // Lose air slower underwater
        );

        addFancyMaterial(Material.IRON_CHESTPLATE,
                Enchantment.PROTECTION,                // Basic protection
                Enchantment.FIRE_PROTECTION,           // Extra fire defense
                Enchantment.BLAST_PROTECTION,          // Extra explosion defense
                Enchantment.PROJECTILE_PROTECTION,     // Extra projectile defense
                Enchantment.THORNS                     // Damages attackers
        );

        addFancyMaterial(Material.IRON_BOOTS,
                Enchantment.FEATHER_FALLING,   // Reduce fall damage
                Enchantment.DEPTH_STRIDER,     // Walk faster in water
                Enchantment.FROST_WALKER,      // Freeze water underfoot
                Enchantment.SOUL_SPEED         // Faster movement on soul blocks
        );

        addFancyMaterial(Material.IRON_LEGGINGS,
                Enchantment.SWIFT_SNEAK       // Move faster while sneaking
        );

        addFancyMaterial(Material.LEATHER_HELMET,
                Enchantment.BINDING_CURSE,    // Cannot remove item
                Enchantment.VANISHING_CURSE   // Disappears on death
        );

        addFancyMaterial(Material.DIAMOND_PICKAXE,
                Enchantment.EFFICIENCY,   // Mine faster
                Enchantment.SILK_TOUCH,   // Blocks drop themselves
                Enchantment.FORTUNE       // Chance of more drops
        );

        addFancyMaterial(Material.SHIELD,
                Enchantment.UNBREAKING,   // Reduces durability loss
                Enchantment.MENDING       // Repairs from experience orbs
        );

        addFancyMaterial(Material.NETHERITE_SWORD,
                Enchantment.WIND_BURST    // Emits wind burst on hit
        );

    }

    private static void addFancyMaterial(Material material, Enchantment... enchantments) {
        for (Enchantment enchantment : enchantments) {
            fancyMaterials.put(enchantment, material);
        }
    }

    public static Material getMaterial(Enchantment enchantment) {
        if (fancyMaterials.containsKey(enchantment)) {
            return fancyMaterials.get(enchantment);
        }
        return Material.ENCHANTED_BOOK;
    }

}
