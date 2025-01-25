package io.github.HenriqueMichelini.inventory_viewer.command;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static org.apache.logging.log4j.LogManager.getLogger;

public class InventoryGui {

    private final Gui gui;
    private final Player targetPlayer;
    private BukkitRunnable updateTask;

    public InventoryGui(Player targetPlayer) {
        this.targetPlayer = targetPlayer;

        // Initialize the GUI with the title "Inventory Viewer"
        gui = Gui.gui()
                .title(Component.text(targetPlayer.getName() + "'s Inventory"))
                .rows(6) // 6 rows (max slots: 54)
                .disableAllInteractions() // Prevent modifications in the GUI
                .create();

        // Populate the GUI with the player's inventory items initially
        populateGui();

        // Start the periodic update task
        startUpdating();
    }

    private void populateGui() {
        try {
            Inventory playerInventory = targetPlayer.getInventory();

            // Loop through all slots in the player's inventory
            for (int slot = 0; slot < playerInventory.getSize(); slot++) {
                ItemStack item = playerInventory.getItem(slot);

                if (item != null && item.getType() != Material.AIR) {
                    GuiItem guiItem = new GuiItem(item);
                    gui.setItem(slot, guiItem);
                } else {
                    gui.removeItem(slot);
                }
            }

            // Refresh the GUI after populating
            gui.update(); // Check if your GUI library provides an update method
        } catch (Exception e) {
            Bukkit.getLogger().warning("Error in populateGui: " + e.getMessage());
            e.printStackTrace();
        }
    }



    private boolean isGuiOpen = false; // Track if the GUI is open

    private void startUpdating() {
        updateTask = new BukkitRunnable() {
            @Override
            public void run() {
                // Check if the player is online and the GUI is still open
                if (!targetPlayer.isOnline() || !isGuiOpen) {
                    this.cancel();
                    Bukkit.getLogger().info("Task canceled for player: " + targetPlayer.getName());
                    return;
                }

                // Update the GUI
                populateGui();
                Bukkit.getLogger().info("GUI updated for player: " + targetPlayer.getName());
            }
        };

        // Schedule the task
        updateTask.runTaskTimer(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("inventory_viewer")), 0L, 10L);

        // Set an action when the GUI is opened and closed
        gui.setOpenGuiAction(event -> isGuiOpen = true);
        gui.setCloseGuiAction(event -> {
            isGuiOpen = false;
            if (updateTask != null && !updateTask.isCancelled()) {
                updateTask.cancel();
            }
        });
    }


    public Gui getGui() {
        return gui;
    }
}
