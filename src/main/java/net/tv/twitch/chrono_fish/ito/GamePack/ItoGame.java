package net.tv.twitch.chrono_fish.ito.GamePack;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ItoGame {

    public enum GameState{
        Running,
        Finished;
    }

    private String theme;
    private GameState state;
    private Deck deck;

    private HashMap<Player, Card> map;

    ItoGame(String theme, Deck deck){
        this.state = GameState.Finished;
        this.theme = theme;
        this.deck = deck;
        this.map = new HashMap<>();
    }

    public void startGame(){
        setState(GameState.Running);
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

    public void broadcastMessage(String message){
        Bukkit.broadcastMessage(message);
    }
}
