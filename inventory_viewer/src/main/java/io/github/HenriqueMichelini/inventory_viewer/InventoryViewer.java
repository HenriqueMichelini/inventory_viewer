package io.github.HenriqueMichelini.inventory_viewer;

import io.github.HenriqueMichelini.inventory_viewer.command.CommandLogic;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class InventoryViewer extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Inventory Viewer Plugin Enabled!");
        Objects.requireNonNull(getCommand("inventoryviewer")).setExecutor(new CommandLogic());
    }

    @Override
    public void onDisable() {
        getLogger().info("Inventory Viewer Plugin Disabled!");
    }
}
