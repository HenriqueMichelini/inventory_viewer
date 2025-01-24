package io.github.HenriqueMichelini.inventory_viewer.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandLogic implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            // Create the inventory GUI for the player
            InventoryGui inventoryGui = new InventoryGui(player);

            // Open the GUI for the player
            inventoryGui.getGui().open(player);
            return true;
        }

        commandSender.sendMessage("This command can only be run by a player.");
        return false;
    }
}
