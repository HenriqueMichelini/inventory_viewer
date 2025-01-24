package io.github.HenriqueMichelini.inventory_viewer;

import io.github.HenriqueMichelini.inventory_viewer.command.CommandLogic;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Inventory_viewer extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Inventory Viewer Plugin Enabled!");

        // Register commands
        Objects.requireNonNull(getCommand("inventoryviewer")).setExecutor(new CommandLogic());

        // Register event listeners (if needed)
        // getServer().getPluginManager().registerEvents(new InventoryViewerListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Inventory Viewer Plugin Disabled!");
    }
}
