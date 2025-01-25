package io.github.HenriqueMichelini.inventory_viewer.command;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class InventoryGui {

    private final Gui gui;
    private final Player targetPlayer;
    private BukkitRunnable updateTask;

    public InventoryGui(Player targetPlayer) {
        this.targetPlayer = targetPlayer;

        // Initialize the GUI with the title "Inventory Viewer"
        gui = Gui.gui()
                .title(Component.text(targetPlayer.getName() + "'s Inventory"))
                .rows(6)
                .disableAllInteractions()
                .create();

        populateGui();

        startUpdating();
    }

    private void populateGui() {
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

            int playerHealthAmount = Math.max(1, (int) targetPlayer.getHealth()); // Prevent zero health
            int playerFoodLevelAmount = Math.max(1, targetPlayer.getFoodLevel());
            int playerLevelAmount = Math.max(1, targetPlayer.getLevel());
            String playerName = targetPlayer.getName();

            ItemStack healthItem = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, playerHealthAmount);
            ItemMeta healthMeta = healthItem.getItemMeta();
            healthMeta.displayName(Component.text(playerName.concat("'s Health")).color(TextColor.color(0xFFFFFF))); // Set to default white color
            healthItem.setItemMeta(healthMeta);
            GuiItem health = new GuiItem(healthItem);
            gui.setItem(42, health);

            ItemStack foodItem = new ItemStack(Material.MELON_SLICE, playerFoodLevelAmount);
            ItemMeta foodMeta = foodItem.getItemMeta();
            foodMeta.displayName(Component.text(playerName.concat("'s Food level")).color(TextColor.color(0xFFFFFF))); // Set to default white color
            foodItem.setItemMeta(foodMeta);
            GuiItem food = new GuiItem(foodItem);
            gui.setItem(43, food);

            ItemStack levelItem = new ItemStack(Material.EXPERIENCE_BOTTLE, playerLevelAmount);
            ItemMeta levelMeta = levelItem.getItemMeta();
            levelMeta.displayName(Component.text(playerName.concat("'s Experience level")).color(TextColor.color(0xFFFFFF))); // Set to default white color
            levelItem.setItemMeta(levelMeta);
            GuiItem level = new GuiItem(levelItem);
            gui.setItem(44, level);

            gui.update();
    }



    private boolean isGuiOpen = false; // Track if the GUI is open

    private void startUpdating() {
        updateTask = new BukkitRunnable() {
            @Override
            public void run() {
                // Check if the player is online and the GUI is still open
                if (!targetPlayer.isOnline() || !isGuiOpen) {
                    this.cancel();
                    //Bukkit.getLogger().info("Task canceled for player: " + targetPlayer.getName());
                    return;
                }

                // Update the GUI
                populateGui();
                //Bukkit.getLogger().info("GUI updated for player: " + targetPlayer.getName());
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
