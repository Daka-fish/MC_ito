package net.tv.twitch.chrono_fish.ito;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ItoScoreboard {

    Scoreboard board;
    Objective obj;

    public ItoScoreboard(){
        board = Bukkit.getScoreboardManager().getNewScoreboard();
        obj = board.registerNewObjective("sideBar","dummy", ChatColor.BOLD+"=数字の宣言=");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore(" ");
        score.setScore(0);
    }

    public Scoreboard getBoard() {
        return board;
    }

    public void addPlayerName(String name){
        Score score = obj.getScore(name);
        score.setScore(-(obj.getScoreboard().getEntries().size()));
    }
}
