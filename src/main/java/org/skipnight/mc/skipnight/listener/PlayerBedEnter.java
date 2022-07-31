package org.skipnight.mc.skipnight.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.skipnight.mc.skipnight.api.PlayersOnBed;
import org.skipnight.mc.skipnight.SkipNight;
import org.skipnight.mc.skipnight.api.SkipCore;
import org.skipnight.mc.skipnight.api.TimeType;

import java.util.Collection;
import java.util.Random;

public class PlayerBedEnter implements Listener {
    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        long time = Bukkit.getServer().getWorld("world").getTime();
        TimeType timeType = SkipCore.getTimeType(time);

        if(timeType == TimeType.NIGHT && !(event.isCancelled())) {
            Collection<? extends Player> onlinePlayers = event.getPlayer().getServer().getOnlinePlayers();
            int playersRequired = (int) (onlinePlayers.size() * 0.4f);
            PlayersOnBed.players += 1;
            SkipCore.sendPlayers(ChatColor.translateAlternateColorCodes('&',
                    Math.max(playersRequired - PlayersOnBed.players, 0) != 0 ?
                    SkipNight.getInstance().getConfig().getString("message.playerSleep")
                            .replace("{player}", event.getPlayer().getName())
                            .replace("{players}", String.valueOf(
                                    playersRequired - PlayersOnBed.players
                            ))
                            :
                    SkipNight.getInstance().getConfig().getString("message.skipNight")
                            .replace("{player}", event.getPlayer().getName())
            ), onlinePlayers);

            if(PlayersOnBed.players >= playersRequired) {
                PlayersOnBed.players = 0;
                Bukkit.getScheduler().scheduleAsyncDelayedTask(SkipNight.getInstance(), () -> {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(SkipNight.getInstance(), () -> {
                        SkipCore.skipNight(time);
                    });
                });
            }
        }
    }
}
