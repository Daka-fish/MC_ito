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
        joined.spigot().sendMessage(Ito.getMessage());
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
                Player dropper = e.getPlayer();
                int number = Integer.parseInt(paperName);

                ItoGame itoGame = Ito.getItogame();
                itoGame.putField(dropper, new Card(number));
                itoGame.broadcastMessage(ChatColor.YELLOW+dropper.getName()+ChatColor.RESET+"が数字を宣言しました");
                Bukkit.getOnlinePlayers().forEach(player -> {
                    ItoScoreboard itoScoreboard = itoGame.getScoreboardHashMap().get(player);
                    itoScoreboard.updateOrder(dropper);
                });
            } catch (NumberFormatException ex) {
                throw new RuntimeException(ex);
            }
            e.getItemDrop().remove();
        }
    }
}
