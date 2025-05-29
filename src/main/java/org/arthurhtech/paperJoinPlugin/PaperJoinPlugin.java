package org.arthurhtech.paperJoinPlugin;

import org.arthurhtech.paperJoinPlugin.listeners.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PaperJoinPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
