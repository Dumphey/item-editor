package at.dumphey.itemeditorplus;

import at.dumphey.itemeditorplus.events.InventoryClickListener;
import at.dumphey.itemeditorplus.events.PlayerChatListener;
import at.dumphey.itemeditorplus.events.PlayerQuitListener;
import at.dumphey.itemeditorplus.commands.ItemEditorCommand;
import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemEditorPlus extends JavaPlugin {




    private static ItemEditorPlus instance;

    public static ItemEditorPlus getInstance() {
        return instance;
    }

    public ItemEditorPlus() {
        instance = this;
    }

    @Override
    public void onEnable() {
        registerCommands();
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        ItemEditor.INSTANCE.getItemStorage().reload();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        final ItemEditorCommand cmd = new ItemEditorCommand();
        getCommand("itemeditor").setExecutor(cmd);
        getCommand("itemeditor").setTabCompleter(cmd);
    }
}
