package at.dumphey.itemeditor.itemeditor.modules.flags;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.util.*;
import java.util.stream.Collectors;

public class ItemFlagUtils {
    private static final Map<ItemFlag, Material> fancyMaterials = new HashMap<>();

    static {
        fancyMaterials.put(ItemFlag.HIDE_ENCHANTS, Material.ENCHANTED_BOOK);
        fancyMaterials.put(ItemFlag.HIDE_DESTROYS, Material.WOODEN_PICKAXE);
        //fancyMaterials.put(ItemFlag.HIDE_DYE, Material.ORANGE_DYE);
        fancyMaterials.put(ItemFlag.HIDE_POTION_EFFECTS, Material.POTION);
        fancyMaterials.put(ItemFlag.HIDE_UNBREAKABLE, Material.BEDROCK);
        fancyMaterials.put(ItemFlag.HIDE_PLACED_ON, Material.GRASS_BLOCK);
        fancyMaterials.put(ItemFlag.HIDE_ATTRIBUTES, Material.BOOK);
    }

    public static Material getMaterial(ItemFlag itemFlag) {
        if (fancyMaterials.containsKey(itemFlag)) {
            return fancyMaterials.get(itemFlag);
        }

        return ItemEditor.INSTANCE.getCompatUtils().getSignMaterial();
    }

    public static List<ItemFlag> sorted(Collection<ItemFlag> flags) {
        return flags.stream().sorted(Comparator.comparing(Enum::name)).collect(Collectors.toList());
    }
}
