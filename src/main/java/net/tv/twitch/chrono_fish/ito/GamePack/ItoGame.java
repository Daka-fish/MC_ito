package net.tv.twitch.chrono_fish.ito.GamePack;

import org.bukkit.Bukkit;
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

    private HashMap<Player, Card> map = new HashMap<>();

    ItoGame(String theme){
        this.state = GameState.Finished;
        this.theme = theme;
        this.deck = new Deck();
    }

    public void dealCard(){
        for(Player player : Bukkit.getOnlinePlayers()){
            Card card = deck.drawCard();
            player.sendMessage("あなたの数字は"+card.getNumber()+"です");
            map.put(player,card);
        }
    }

    public void endGame(){
        if(getState().equals(GameState.Running)){
            broadcastMessage("ゲームを終了します");
            setState(GameState.Finished);
            showCard();
        }else{
            broadcastMessage("進行中のゲームがありません");
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
        broadcastMessage("check");
        for(Map.Entry<Player, Card> entry : map.entrySet()){
            Player player = entry.getKey();
            Card card = entry.getValue();
            broadcastMessage(player.getName() + " : "+ card.getNumber());
        }
    }

    public void broadcastMessage(String message){
        Bukkit.broadcastMessage(message);
    }
}
