package net.tv.twitch.chrono_fish.ito;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarManager {
    static BossBar bossBar;

    BossBarManager(){
        bossBar = Bukkit.createBossBar("ito", BarColor.PINK, BarStyle.SEGMENTED_20);
    }

    public static BossBar getBossBar(){
        return bossBar;
    }

    public static void addAllPlayer(){
        for(Player player : Bukkit.getOnlinePlayers()){
            bossBar.addPlayer(player);
        }
    }
}
