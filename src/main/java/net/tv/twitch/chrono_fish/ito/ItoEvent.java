package net.tv.twitch.chrono_fish.ito;

import net.tv.twitch.chrono_fish.ito.CommandPack.CommandManager;
import net.tv.twitch.chrono_fish.ito.GamePack.Card;
import net.tv.twitch.chrono_fish.ito.GamePack.ItoGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class ItoEvent implements Listener {

    public static BossBar bossBar = Bukkit.createBossBar(ChatColor.BOLD+"ito", BarColor.GREEN, BarStyle.SEGMENTED_10);

    public static BossBar getBossBar() {
        return bossBar;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        bossBar.addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        ItemStack dropItem = e.getItemDrop().getItemStack();
        if(dropItem.getType() == Material.PAPER){
            String paperName = dropItem.getItemMeta().getDisplayName();
            try {
                int number = Integer.parseInt(paperName);
                CommandManager.getItogame().putField(new Card(number));
            } catch (NumberFormatException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
