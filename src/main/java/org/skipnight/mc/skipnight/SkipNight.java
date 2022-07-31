package org.skipnight.mc.skipnight;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.skipnight.mc.skipnight.listener.PlayerBedEnter;
import org.skipnight.mc.skipnight.listener.PlayerBedLeave;

public final class SkipNight extends JavaPlugin {
    private static SkipNight instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new PlayerBedEnter(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerBedLeave(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SkipNight getInstance() {
        return instance;
    }
}
