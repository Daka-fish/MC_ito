package net.tv.twitch.chrono_fish.ito;

import net.tv.twitch.chrono_fish.ito.GamePack.Card;
import net.tv.twitch.chrono_fish.ito.GamePack.ItoGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class ItoEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player joined = e.getPlayer();
        ItoGame itoGame = Ito.getItogame();
        itoGame.getNumberHashMap().put(joined.getName(), new Card(-1));
        ItoScoreboard itoScoreboard = new ItoScoreboard(e.getPlayer());
        itoGame.getScoreboardHashMap().put(e.getPlayer(),itoScoreboard);
        joined.setScoreboard(itoGame.getScoreboardHashMap().get(joined).getBoard());
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        ItemStack dropItem = e.getItemDrop().getItemStack();
        if(dropItem.getType() == Material.PAPER){
            String paperName = dropItem.getItemMeta().getDisplayName();
            try {
                Player droper = e.getPlayer();
                int number = Integer.parseInt(paperName);

                ItoGame itoGame = Ito.getItogame();
                itoGame.putField(new Card(number), droper.getName());
                itoGame.broadcastMessage(ChatColor.YELLOW+droper.getName()+ChatColor.RESET+"が数字を宣言しました");
                Bukkit.getOnlinePlayers().forEach(player -> {
                    ItoScoreboard itoScoreboard = itoGame.getScoreboardHashMap().get(player);
                    itoScoreboard.updateOrder(droper);
                });
            } catch (NumberFormatException ex) {
                throw new RuntimeException(ex);
            }
            e.getItemDrop().remove();
        }
    }
}
