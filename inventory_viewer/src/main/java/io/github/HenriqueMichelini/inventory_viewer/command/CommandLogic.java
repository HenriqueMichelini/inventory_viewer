package io.github.HenriqueMichelini.inventory_viewer.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandLogic implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("This command can only be run by a player.");
            return false;
        }

        Player player = (Player) commandSender;

        if (args.length < 1) {
            player.sendMessage("Usage: /inventoryviewer player");
            return false;
        }

        String targetPlayerName = args[0];
        Player targetPlayer = Bukkit.getPlayer(targetPlayerName);

        if (targetPlayer == null || !targetPlayer.isOnline()) {
            player.sendMessage("The player '" + targetPlayerName + "' is not online or does not exist.");
            return false;
        }

        if (targetPlayer == ((Player) commandSender).getPlayer()) {
            player.sendMessage("The player cannot be yourself.");
            return false;
        }

        InventoryGui inventoryGui = new InventoryGui(targetPlayer);

        inventoryGui.getGui().open(player);
        return true;
    }
}
