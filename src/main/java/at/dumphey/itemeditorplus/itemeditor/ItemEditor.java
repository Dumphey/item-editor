package at.dumphey.itemeditorplus.itemeditor;

import at.dumphey.itemeditorplus.compatibility.CompatUtils;
import at.dumphey.itemeditorplus.itemeditor.modules.home.HomeUiScreen;
import at.dumphey.itemeditorplus.itemeditor.storage.ItemStorage;
import at.dumphey.itemeditorplus.ui.prompts.PromptHandler;
import at.dumphey.itemeditorplus.ui.UiManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

public class ItemEditor implements Listener {

    public static final ItemEditor INSTANCE = new ItemEditor();

    private final UiManager uiManager;
    private final PromptHandler promptHandler;
    private final CompatUtils compatUtils;
    private final ItemStorage itemStorage;
    private final Map<UUID, ItemStack> editing = new HashMap<>();

    public ItemEditor() {
        this.uiManager = new UiManager();
        this.promptHandler = new PromptHandler();
        this.compatUtils = new CompatUtils();
        this.itemStorage = new ItemStorage();
    }

    public void edit(Player player, ItemStack editItem) {
        setItem(player, editItem);
        uiManager.open(player, new HomeUiScreen(player));
        player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
    }

    public ItemStack getItem(Player player) {
        return editing.get(player.getUniqueId());
    }

    public void setItem(Player player, ItemStack itemStack) {
        editing.put(player.getUniqueId(), itemStack);
    }

    public void finish(Player player) {
        final ItemStack item = getItem(player);
        uiManager.close(player);
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
        player.getInventory().setItemInMainHand(item);
    }

    public UiManager getUiManager() {
        return uiManager;
    }

    public PromptHandler getPromptHandler() {
        return promptHandler;
    }

    public CompatUtils getCompatUtils() {
        return compatUtils;
    }

    public boolean isEditing(Player player) {
        return editing.containsKey(player.getUniqueId());
    }

    public void modifyItem(Player player, Function<ItemStack, ItemStack> modifier) {
        setItem(player, modifier.apply(getItem(player)));
        uiManager.update(player);
    }

    public ItemStorage getItemStorage() {
        return itemStorage;
    }

    public void onQuit(Player player) {
        promptHandler.onQuit(player);
        uiManager.onQuit(player);
    }

    public void openHome(Player player) {
        uiManager.open(player, new HomeUiScreen(player));
    }
}
