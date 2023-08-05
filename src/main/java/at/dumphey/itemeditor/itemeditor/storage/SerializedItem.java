package at.dumphey.itemeditor.itemeditor.storage;

import org.bukkit.inventory.ItemStack;

public class SerializedItem {
    private final String name;
    private final ItemStack itemStack;

    public SerializedItem(String name, ItemStack itemStack) {
        this.name = name;
        this.itemStack = itemStack;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}