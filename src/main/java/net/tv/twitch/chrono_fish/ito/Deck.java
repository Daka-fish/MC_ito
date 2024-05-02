package net.tv.twitch.chrono_fish.ito;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Deck {
    int count;
    List<Card> deck = new ArrayList<>();
    List<Card> field = new ArrayList<>();
    HashMap<Player, Card> map = new HashMap<>();

    Deck(){
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

    public Card drawCard(){
        Card fstCard = this.deck.get(0);
        this.deck.remove(0);
        return fstCard;
    }

    public void putField(Card card){
        this.field.add(card);
    }

    public List getField(){
        return field;
    }

    public void putMap(Player player, Card card){
        this.map.put(player,card);
    }
}
