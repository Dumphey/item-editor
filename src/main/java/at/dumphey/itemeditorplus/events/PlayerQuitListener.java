package at.dumphey.itemeditorplus.events;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        ItemEditor.INSTANCE.onQuit(event.getPlayer());
    }

}
