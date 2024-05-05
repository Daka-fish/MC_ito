package net.tv.twitch.chrono_fish.ito.GamePack;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckManager {
    List<Card> field = new ArrayList<>();
    HashMap<Player, Card> map = new HashMap<>();

    DeckManager(){
    }

    public void putField(Card card){
        this.field.add(card);
    }

    public List<Card> getField(){
        return field;
    }

    public void putMap(Player player, Card card){
        this.map.put(player,card);
    }

    public void showField(){
        StringBuilder s = new StringBuilder();
        s.append("==========================\n");
        s.append("プレイヤー名"+"\t"+"(数字)\n");
        for(Map.Entry<Player, Card> entry : map.entrySet()){
            s.append(entry.getKey().getName() + "\t" + "("+entry.getValue().getNumber()+")\n");
        }
        Bukkit.broadcastMessage(s.toString());
    }
}
