package net.tv.twitch.chrono_fish.ito;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckManager {
    int count;
    List<Card> deck = new ArrayList<>();
    List<Card> field = new ArrayList<>();
    HashMap<Player, Card> map = new HashMap<>();

    DeckManager(){
        setDeck();
        shuffleDeck();
    }

    public void setDeck() {
        for(int i=1; i<=100; i++){
            this.deck.add(new Card(i));
        }
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void drawCard(Player player, List<Card> deck){
        Card fstCard = deck.get(0);
        this.deck.remove(0);
        putMap(player, fstCard);
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
