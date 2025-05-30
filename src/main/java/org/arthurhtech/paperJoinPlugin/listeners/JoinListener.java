package org.arthurhtech.paperJoinPlugin.listeners;

import org.arthurhtech.paperJoinPlugin.PaperJoinPlugin;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class JoinListener implements Listener {

    private final PaperJoinPlugin plugin;

    private static final String GREETING_KEY = "join.greeting-message";
    private static final String FIRST_TIME_KEY = "join.first-time-message";
    private static final String REWARDS_KEY = "join.rewards";

    public JoinListener(PaperJoinPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();

        // Mensagem de boas-vindas personalizada
        String rawGreeting = config.getString(GREETING_KEY, "Bem-vindo, {player}!");
        String greeting = rawGreeting.replace("{player}", player.getName());
        player.sendMessage(greeting);

        // Recompensas para primeiro login
        if (!player.hasPlayedBefore()) {
            String firstTimeMsg = config.getString(FIRST_TIME_KEY, "Aqui, pegue algo para começar.");
            player.sendMessage(firstTimeMsg);

            List<String> rewards = config.getStringList(REWARDS_KEY);
            for (String itemEntry : rewards) {
                String[] parts = itemEntry.split(":");

                if (parts.length != 2) {
                    plugin.getLogger().warning("Formato inválido de recompensa: " + itemEntry);
                    continue;
                }

                try {
                    Material material = Material.valueOf(parts[0].toUpperCase());
                    int amount = Integer.parseInt(parts[1]);
                    player.getInventory().addItem(new ItemStack(material, amount));
                } catch (Exception e) {
                    plugin.getLogger().warning("Erro ao adicionar item '" + itemEntry + "': " + e.getMessage());
                }
            }
        }
    }
}