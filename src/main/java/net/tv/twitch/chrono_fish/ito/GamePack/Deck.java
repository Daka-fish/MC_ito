package net.tv.twitch.chrono_fish.ito.GamePack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> deck = new ArrayList<>();


    Deck(){
        setDeck();
        shuffleDeck();
    }

    public List<Card> getDeck() {
        return deck;
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
        Card fstCard = deck.get(0);
        deck.remove(0);
        if(deck.size()==0){
            resetDeck();
            deck = new Deck().getDeck();
        }
        return fstCard;
    }

    public void resetDeck(){
        deck.clear();
    }
}
