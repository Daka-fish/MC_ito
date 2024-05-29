package net.tv.twitch.chrono_fish.ito.GamePack;

import net.tv.twitch.chrono_fish.ito.ItoScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.*;

public class ItoGame implements Listener {

    public enum GameState{
        Running,
        Finished
    }

    private String theme;
    private GameState state;
    private Deck deck;

    private List<Card> field = new ArrayList<>();

    private final HashMap<String, Card> numberHashMap = new HashMap<>();
    private final HashMap<Player, ItoScoreboard> scoreboardHashMap = new HashMap<>();

    private final String firstTheme = ChatColor.GRAY+"-準備中-";

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

    public HashMap<String, Card> getNumberHashMap() {
        return numberHashMap;
    }
    public HashMap<Player, ItoScoreboard> getScoreboardHashMap() {
        return scoreboardHashMap;
    }

    public String getFirstTheme(){
        return firstTheme;
    }

    public void dealCard(Player player){
        Card card = deck.drawCard();
        player.sendMessage("あなたの数字は"+card.getNumber()+"です");
        player.getInventory().addItem(card.getPaper());
        numberHashMap.put(player.getName(),card);
    }

    public void showCard(){
        StringBuilder message = new StringBuilder();
        message.append("数字一覧を表示します").append("\n").append("+--------------+").append("\n");
        for(Map.Entry<String, Card> entry : numberHashMap.entrySet()){
            message.append(entry.getKey()).append(": ").append(entry.getValue().getNumber()).append("\n");
        }
        message.append("+--------------+");
        broadcastMessage(message.toString());
    }

    public void putField(Card card, String name){
        this.field.add(card);
    }

    public List<Card> getField(){return field;}

    public boolean checkField(){
        List<Integer> list = new ArrayList<>();
        for(Card card : this.field){
            list.add(card.getNumber());
        }
        List<Integer> sortedList =new ArrayList<>(list);
        Collections.sort(sortedList);
        return list.equals(sortedList);
    }

    public void broadcastMessage(String message){
        Bukkit.broadcastMessage(ChatColor.GREEN+"[itoGame]"+ChatColor.RESET+message);
    }
}
