package net.tv.twitch.chrono_fish.ito;

import net.tv.twitch.chrono_fish.ito.GamePack.Card;
import net.tv.twitch.chrono_fish.ito.GamePack.ItoGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scoreboard.*;

public class ItoScoreboard {

    Scoreboard board;
    Objective obj;

    private final String theme = ChatColor.BOLD+"+テーマ";
    private final String yourNumber = ChatColor.BOLD+"+あなたの数字";
    private final String callOrder = ChatColor.BOLD+"+数字の宣言";

    Player player;

    public ItoScoreboard(Player player){
        this.player = player;
        board = Bukkit.getScoreboardManager().getNewScoreboard();
        obj = board.registerNewObjective("sideBar", Criteria.DUMMY, ChatColor.BOLD+"=Info=");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore(" ");
        score.setScore(0);
        for (int i = 0; i < 4; i++) {
            obj.getScore(generateSpace(i)).setScore(-3 * i);
        }
        setScores(player);
    }

    public Scoreboard getBoard() {
        return board;
    }

    public void setScores(Player player){
        ItoGame itogame = Ito.getItogame();
        obj.getScore(theme).setScore(-1);
        obj.getScore("   └ "+itogame.getTheme()).setScore(-2);
        obj.getScore(yourNumber).setScore(-4);
        obj.getScore("   └ "+itogame.getNumberHashMap().get(player.getName()).getNumber()).setScore(-5);
        obj.getScore(callOrder).setScore(-7);
    }

    public String generateSpace(int count){
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < count; i++) {
            spaces.append(' ');
        }
        return spaces.toString();
    }

    public void updateTheme(String currentTheme){
        board.resetScores("   └ "+currentTheme);
        obj.getScore("   └ "+Ito.getItogame().getTheme()).setScore(-2);
    }

    public void updateNumber(Card card){
        board.resetScores("   └ "+card.getNumber());
        obj.getScore("   └ "+Ito.getItogame().getNumberHashMap().get(player.getName()).getNumber()).setScore(-5);
    }

    public void updateOrder(Player player){
        obj.getScore(player.getName()).setScore(obj.getScore(callOrder).getScore()-1);
    }
}
