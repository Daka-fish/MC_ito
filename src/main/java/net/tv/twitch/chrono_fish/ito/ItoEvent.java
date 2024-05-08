package net.tv.twitch.chrono_fish.ito;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ItoEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        BossBar bossBar = Bukkit.createBossBar(ChatColor.BOLD+"ito", BarColor.GREEN, BarStyle.SEGMENTED_12);
        bossBar.addPlayer(e.getPlayer());
    }
}
