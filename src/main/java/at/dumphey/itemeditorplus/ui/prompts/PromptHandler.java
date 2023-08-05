package at.dumphey.itemeditorplus.ui.prompts;

import at.dumphey.itemeditorplus.ItemEditorPlus;
import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;

public class PromptHandler {

    private final Map<Player, ChatPrompt> prompts = new HashMap<>();

    public void setPrompt(Player player, ChatPrompt prompt) {
        prompts.put(player, prompt);
        ItemEditor.INSTANCE.getUiManager().getScreen(player).hide();
        player.sendMessage(prompt.getMessage());
        if (prompt.isCancellable()) {
            player.sendMessage("§7§oTo cancel, type 'cancel'.");
        }
    }

    public void onChat(AsyncPlayerChatEvent event) {
        final ChatPrompt prompt = prompts.get(event.getPlayer());
        if (prompt == null) {
            return;
        }

        event.setCancelled(true);

        Bukkit.getScheduler().runTask(ItemEditorPlus.getInstance(), () -> {
            if (prompt.fulfill(event.getMessage())) {
                prompts.remove(event.getPlayer());
                prompt.onComplete();
            }
        });
    }

    public void onQuit(Player player) {
        prompts.remove(player);
    }
}
