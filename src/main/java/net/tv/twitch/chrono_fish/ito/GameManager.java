package net.tv.twitch.chrono_fish.ito;

import org.bukkit.Bukkit;

import java.util.List;

public class GameManager {
    ItoGame itoGame;

    public void startGame(String theme){
        Deck deck = new Deck();
        this.itoGame = new ItoGame(theme,deck);
        itoGame.startGame();
    };

    public void endGame(){
        itoGame.endGame();
    }
}
