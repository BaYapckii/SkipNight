package org.skipnight.mc.skipnight.api;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.skipnight.mc.skipnight.SkipNight;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class SkipCore {
    public static void skipNight(long time) {
        long fullTime = Bukkit.getServer().getWorld("world").getFullTime();
        try {
            for(long i = fullTime; i <= (fullTime - time) + 23600; i += 100) {
                TimeUnit.MILLISECONDS.sleep(50);
                Bukkit.getServer().getWorld("world").setTime(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static TimeType getTimeType(long time) {
        return time >= 12544 && time < 23460 ? TimeType.NIGHT : TimeType.DAY;
    }

    public static void sendPlayers(String msg, Collection<? extends Player> onlinePlayers) {
        for(Player player : onlinePlayers) {
            player.sendMessage(msg);
        }
    }
}
