package io.github.HenriqueMichelini.inventory_viewer.command;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryGui {

    private final Gui gui;

    public InventoryGui(Player player) {
        // Initialize the GUI with the title "Inventory Viewer"
        gui = Gui.gui()
                .title(Component.text(player.getName() + "'s Inventory"))
                .rows(6) // 6 rows (max slots: 54)
                .create();

        // Populate the GUI with the player's inventory items
        populateGui(player);
    }

    private void populateGui(Player player) {
        Inventory playerInventory = player.getInventory();

        // Loop through all slots in the player's inventory (0-35 for main inventory, 36-39 for armor, etc.)
        for (int slot = 0; slot < playerInventory.getSize(); slot++) {
            ItemStack item = playerInventory.getItem(slot);

            // Only process non-null items
            if (item != null && item.getType() != Material.AIR) {
                // Create a GuiItem from the ItemStack
                GuiItem guiItem = new GuiItem(item);

                // Set the item into the corresponding slot in the GUI
                gui.setItem(slot, guiItem);
            }
        }
    }

    public Gui getGui() {
        return gui;
    }
}
