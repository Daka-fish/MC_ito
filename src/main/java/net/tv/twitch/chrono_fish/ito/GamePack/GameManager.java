package net.tv.twitch.chrono_fish.ito.GamePack;

public class GameManager {
    ItoGame itoGame = new ItoGame("");

    public void startGame(String theme){
        this.itoGame = new ItoGame(theme);
        itoGame.startGame();
    };

    public void endGame(){
        if(itoGame.getState().equals(ItoGame.GameState.Running)){itoGame.endGame();}
    }
}
