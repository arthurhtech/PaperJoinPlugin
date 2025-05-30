package org.arthurhtech.paperJoinPlugin.listeners;

import org.arthurhtech.paperJoinPlugin.PaperJoinPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class JoinListener implements Listener {
    private final PaperJoinPlugin plugin;

    public JoinListener(PaperJoinPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Guardando a greeting-message em uma variavel e adicionando uma valor padrao para
        // que nao ocorra de ser null.
        String joinMessage = plugin.getConfig().getString("join.greeting-message",
                "Bem-vindo, {player}!");
        player.sendMessage(joinMessage.replace("{player}", player.getName()));

        player.sendMessage(joinMessage);

        if (!player.hasPlayedBefore()) {
            String firstTimeMsg = plugin.getConfig().getString("join.first-time-message",
                    "Aqui, pegue algo para começar.");
            player.sendMessage(firstTimeMsg);

            List<String> rewards = plugin.getConfig().getStringList("join.rewards");
            for (String itemEntry : rewards) {
                String[] parts = itemEntry.split(":");
                try {
                    Material material = Material.valueOf(parts[0]);
                    int amount = Integer.parseInt(parts[1]);
                    player.getInventory().addItem(new ItemStack(material, amount));
                } catch (Exception e) {
                    plugin.getLogger().warning("Item inválido em config.yml: " + itemEntry);
                }
            }
        }
    }
}
