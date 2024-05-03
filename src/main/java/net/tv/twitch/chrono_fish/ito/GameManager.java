package net.tv.twitch.chrono_fish.ito;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class GameManager {
    String theme = "";

    public void startGame(){
        DeckManager deckManager = new DeckManager();
        List<Card> deck = deckManager.getDeck();

        for(Player player : Bukkit.getOnlinePlayers()){
            deckManager.drawCard(player,deck);
        }
    };

    public void setTheme(String theme) {
        this.theme = theme;
        BossBarManager.getBossBar().setTitle(theme);
    }

    public String getTheme() {
        return theme;
    }

    public void check(List<Card> field){
        for(int i=0; i<field.size(); i++){
            if(i+1<field.size()){
                if(field.get(i).getNumber()<field.get(i+1).getNumber()){
                     continue;
                }else{
                    return;//失敗
                }
            }
        }
        broadcastMessage("成功！");
    }

    public void endGame(){}

    public void broadcastMessage(String message){
        Bukkit.broadcastMessage(message);
    }
}
