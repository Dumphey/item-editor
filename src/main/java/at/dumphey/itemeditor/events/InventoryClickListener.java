package at.dumphey.itemeditor.events;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (event.getSlot() < 0) {
            return;
        }

        ItemEditor.INSTANCE.getUiManager().onInventoryClick(event);
    }

}
