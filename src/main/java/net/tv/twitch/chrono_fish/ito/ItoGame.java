package net.tv.twitch.chrono_fish.ito;

public class ItoGame {
    public enum GameState{
        Ready,
        Running,
        Finished;
    }

    private String theme;
    private GameState state;

    ItoGame(String s){
        this.theme = s;
        this.state = GameState.Finished;
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
}
