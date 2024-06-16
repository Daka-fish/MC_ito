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

    private List<Card> cardField = new ArrayList<>();
    private List<Player> playerField = new ArrayList<>();

    private final HashMap<String, Card> numberHashMap = new HashMap<>();
    private final HashMap<Player, ItoScoreboard> scoreboardHashMap = new HashMap<>();

    private final String firstTheme = ChatColor.GRAY+"-準備中-";

    private int order = 0;

    public ItoGame(){
        this.state = GameState.Finished;
        this.theme = firstTheme;
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
        message.append("数字一覧を表示します").append("\n");
        for(Map.Entry<String, Card> entry : numberHashMap.entrySet()){
            message.append(entry.getKey()).append(": ").append(ChatColor.YELLOW+String.valueOf(entry.getValue().getNumber())+ChatColor.RESET);
        }
        broadcastMessage(message.toString());
    }

    public void putField(Player player, Card card){
        playerField.add(player);
        cardField.add(card);
    }

    public List<Card> getCardField(){return cardField;}

    public boolean checkField(){
        List<Integer> list = new ArrayList<>();
        for(Card card : this.cardField){
            list.add(card.getNumber());
        }
        List<Integer> sortedList =new ArrayList<>(list);
        Collections.sort(sortedList);
        return list.equals(sortedList);
    }

    public void openCard(){
        if(order==playerField.size()){
            if(checkField()){
                broadcastMessage("成功!");
            }else{
                broadcastMessage("失敗!");
            }
            return;
        }
        String name = playerField.get(order).getName();
        int number = cardField.get(order).getNumber();
        broadcastMessage(name+": "+number);
        order++;
    }

    public void broadcastMessage(String message){
        Bukkit.broadcastMessage(ChatColor.GREEN+"[ito]"+ChatColor.RESET+message);
    }
}
