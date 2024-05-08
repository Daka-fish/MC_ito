package net.tv.twitch.chrono_fish.ito.GamePack;

import net.tv.twitch.chrono_fish.ito.ItoEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ItoGame {

    public enum GameState{
        Running,
        Finished;
    }

    private String theme;
    private GameState state;
    private Deck deck;

    private static HashMap<Player, Card> map = new HashMap<>();

    public ItoGame(String theme){
        this.state = GameState.Finished;
        this.theme = theme;
        this.deck = new Deck();
    }

    public void startGame(){
        broadcastMessage("ゲームを開始します");
        setState(GameState.Running);
    }

    public void endGame(){
        broadcastMessage("ゲームを終了します");
        setState(GameState.Finished);
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme){this.theme = theme;}

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public HashMap<Player, Card> getMap() {
        return map;
    }

    public void dealCard(){
        for(Player player : Bukkit.getOnlinePlayers()){
            Card card = deck.drawCard();
            player.sendMessage("あなたの数字は"+card.getNumber()+"です");
            map.put(player,card);
        }
    }

    public void showCard(){
        StringBuilder message = new StringBuilder();
        for(Map.Entry<Player, Card> entry : map.entrySet()){
            message.append(entry.getKey().getName()).append(" : ").append(entry.getValue().getNumber()).append("\n");
        }
        broadcastMessage(message.toString());
    }

    public void reloadBossBar(){
        for(Player player : Bukkit.getOnlinePlayers()){
            ItoEvent.getBossBar().setTitle(ChatColor.BOLD+theme);
            ItoEvent.getBossBar().addPlayer(player);
        }
    }

    public void broadcastMessage(String message){
        Bukkit.broadcastMessage(ChatColor.GREEN+"[itoGame]"+message);
    }
}
