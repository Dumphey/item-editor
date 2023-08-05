package at.dumphey.itemeditorplus.events;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        ItemEditor.INSTANCE.getPromptHandler().onChat(event);
    }

}
