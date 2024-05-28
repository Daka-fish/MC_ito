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

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        ItemStack dropItem = e.getItemDrop().getItemStack();
        if(dropItem.getType() == Material.PAPER){
            String paperName = dropItem.getItemMeta().getDisplayName();
            try {
                ItoGame itoGame = Ito.getItogame();
                int number = Integer.parseInt(paperName);
                itoGame.putField(new Card(number), e.getPlayer().getName());
                itoGame.broadcastMessage(ChatColor.YELLOW+e.getPlayer().getName()+ChatColor.RESET+"が数字を宣言しました");
                ItoScoreboard itoScoreboard = new ItoScoreboard();
                itoScoreboard.addPlayerName(e.getPlayer().getName());
                e.getPlayer().setScoreboard(itoScoreboard.getBoard());
            } catch (NumberFormatException ex) {
                throw new RuntimeException(ex);
            }
            e.getItemDrop().remove();
        }
    }
}
