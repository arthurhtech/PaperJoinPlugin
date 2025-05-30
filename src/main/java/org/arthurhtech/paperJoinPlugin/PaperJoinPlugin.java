package org.arthurhtech.paperJoinPlugin;

import org.arthurhtech.paperJoinPlugin.listeners.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PaperJoinPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
