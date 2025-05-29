package org.arthurhtech.paperJoinPlugin.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.sendMessage("Bem vindo ao servidor! " + player.getName());

        if (!player.hasPlayedBefore()) {
            player.sendMessage("Aqui, pegue algo para começar.");
            player.getInventory().addItem(new ItemStack(Material.BREAD, 10));
            player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
        }
    }
}
