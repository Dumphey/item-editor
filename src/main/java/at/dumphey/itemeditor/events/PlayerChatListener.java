package at.dumphey.itemeditor.events;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        ItemEditor.INSTANCE.getPromptHandler().onChat(event);
    }

}
