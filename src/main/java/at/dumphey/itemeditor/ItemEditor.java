package at.dumphey.itemeditor;

import at.dumphey.itemeditor.events.InventoryClickListener;
import at.dumphey.itemeditor.events.PlayerChatListener;
import at.dumphey.itemeditor.events.PlayerQuitListener;
import at.dumphey.itemeditor.commands.ItemEditorCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemEditor extends JavaPlugin {


    private static ItemEditor instance;


    public static ItemEditor getInstance() {
        return instance;
    }

    public ItemEditor() {
        instance = this;
    }

    @Override
    public void onEnable() {
        registerCommands();
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        at.dumphey.itemeditor.itemeditor.ItemEditor.INSTANCE.getItemStorage().reload();

        if (isPlaceholderApiEnabled()) {
            getLogger().info("Enabled PlaceholderAPI support.");
        }
    }

    public boolean isPlaceholderApiEnabled() {
        return getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
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
