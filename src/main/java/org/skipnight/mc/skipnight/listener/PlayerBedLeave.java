package org.skipnight.mc.skipnight.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.skipnight.mc.skipnight.api.PlayersOnBed;
import org.skipnight.mc.skipnight.SkipNight;
import org.skipnight.mc.skipnight.api.SkipCore;
import org.skipnight.mc.skipnight.api.TimeType;

import java.util.Collection;

public class PlayerBedLeave implements Listener {
    @EventHandler
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
        long time = Bukkit.getServer().getWorld("world").getTime();
        TimeType timeType = SkipCore.getTimeType(time);

        if(timeType != TimeType.DAY) {
            Collection<? extends Player> onlinePlayers = event.getPlayer().getServer().getOnlinePlayers();
            SkipCore.sendPlayers(
                    ChatColor.translateAlternateColorCodes('&',
                            SkipNight.getInstance().getConfig().getString("message.playerNoSleep")
                                    .replace("{player}", event.getPlayer().getName())
                    ), onlinePlayers
            );
            PlayersOnBed.players -= 1;
        }
    }
}
