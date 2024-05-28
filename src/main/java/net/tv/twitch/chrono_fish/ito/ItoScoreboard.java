package net.tv.twitch.chrono_fish.ito;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.*;

public class ItoScoreboard {

    Scoreboard board;
    Objective obj;

    private final String theme = ChatColor.BOLD+"+Theme";
    private final String yourNumber = ChatColor.BOLD+"+Your Number";
    private final String callOrder = ChatColor.BOLD+"+Call Order";

    public ItoScoreboard(){
        board = Bukkit.getScoreboardManager().getNewScoreboard();
        obj = board.registerNewObjective("sideBar", Criteria.DUMMY, ChatColor.BOLD+"=Info=");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore(" ");
        score.setScore(0);
        for (int i = 0; i < 4; i++) {
            obj.getScore(generateSpace(i)).setScore(-3 * i);
        }
        setScores();
    }

    public Scoreboard getBoard() {
        return board;
    }

    public void setScores(){
        obj.getScore(theme).setScore(-1);
        obj.getScore(yourNumber).setScore(-4);
        obj.getScore(callOrder).setScore(-7);
    }

    public String generateSpace(int count){
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < count; i++) {
            spaces.append(' ');
        }
        return spaces.toString();
    }

    public void addPlayerName(String name){
        Score score = obj.getScore(name);
        score.setScore(-(obj.getScoreboard().getEntries().size()));
    }
}
