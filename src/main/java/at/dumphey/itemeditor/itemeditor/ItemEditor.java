package at.dumphey.itemeditor.itemeditor;

import at.dumphey.itemeditor.compatibility.CompatUtils;
import at.dumphey.itemeditor.itemeditor.modules.home.HomeUiScreen;
import at.dumphey.itemeditor.itemeditor.storage.ItemStorage;
import at.dumphey.itemeditor.ui.prompts.PromptHandler;
import at.dumphey.itemeditor.ui.UiManager;
import at.dumphey.itemeditor.utils.ItemBuilder;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public ItemStack personalizeItem(ItemStack itemStack, Player player) {
        ItemStack result = itemStack.clone();
        if (!at.dumphey.itemeditor.ItemEditor.getInstance().isPlaceholderApiEnabled()) {
            return result;
        }

        ItemBuilder builder = ItemBuilder.of(result);
        if (!result.hasItemMeta() || result.getItemMeta() == null) {
            return result;
        }

        if (result.getItemMeta().hasDisplayName()) {
            builder = builder.withName(PlaceholderAPI.setPlaceholders(player, result.getItemMeta().getDisplayName()));
        }

        if (result.getItemMeta().hasLore() && result.getItemMeta().getLore() != null) {
            builder = builder.withLore(result.getItemMeta().getLore().stream().map(l -> PlaceholderAPI.setPlaceholders(player, l)).collect(Collectors.toList()));
        }

        return builder.build();
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
