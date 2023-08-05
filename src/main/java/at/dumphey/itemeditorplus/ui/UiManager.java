package at.dumphey.itemeditorplus.ui;

import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.Map;

public class UiManager {

    private final Map<Player, UiScreen> screens = new HashMap<>();

    public void onQuit(Player player) {
        screens.remove(player);
    }

    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().startsWith(Messages.PREFIX)) {
            // can't ever steal from this
            event.setCancelled(true);
        }

        final Player player = (Player) event.getWhoClicked();
        final UiScreen screen = getScreen(player);

        if (screen == null) {
            return;
        }

        if (!screen.isInventory(event.getInventory())) {
            return;
        }


        screen.onClick(event.getSlot(), event.getClick());
        event.setCancelled(true);
    }

    public void update(Player player) {
        final UiScreen screen = getScreen(player);
        if (screen == null) {
            return;
        }

        screen.update();
    }

    public UiScreen getScreen(Player player) {
        return screens.get(player);
    }


    public void open(Player player, UiScreen screen) {
        screens.put(player, screen);
        update(player);
        screen.show();
    }

    public void close(Player player) {
        final UiScreen screen = getScreen(player);
        if (screen == null) {
            return;
        }

        screen.hide();
        screens.remove(player);
    }

    public void reopen(Player player) {
        final UiScreen screen = getScreen(player);
        if (screen == null) {
            return;
        }
        screen.show();
    }

}
