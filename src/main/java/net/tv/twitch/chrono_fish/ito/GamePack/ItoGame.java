package net.tv.twitch.chrono_fish.ito.GamePack;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

    public void dealCard(){
        broadcastMessage("カードを配布します");
        for(Player player : Bukkit.getOnlinePlayers()){
            Card card = deck.drawCard();
            player.sendMessage("あなたの数字は"+card.getNumber()+"です");
            map.put(player,card);
        }
    }

    public String getTheme() {
        return theme;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void showCard(){
        StringBuilder message = new StringBuilder();
        for(Map.Entry<Player, Card> entry : map.entrySet()){
            message.append(entry.getKey().getName()).append(" : ").append(entry.getValue().getNumber()).append("\n");
        }
        broadcastMessage(message.toString());
    }

    public void broadcastMessage(String message){
        Bukkit.broadcastMessage(ChatColor.DARK_GREEN+"[itoGame]"+message);
    }
}
