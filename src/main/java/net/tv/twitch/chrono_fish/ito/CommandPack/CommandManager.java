package net.tv.twitch.chrono_fish.ito.CommandPack;

import net.tv.twitch.chrono_fish.ito.GamePack.ItoGame;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandManager {

    static ItoGame itogame = new ItoGame("");

    public static void ito(Player sender, String[] args){
        if(args.length == 0){
            sender.sendMessage(ChatColor.RED+"Invalid usage");
            return;
        }

        switch (args[0]) {
            case "start":
                itogame = new ItoGame(args[1]);
                itogame.startGame();
                break;
                
            case "card":
                itogame.dealCard();
                break;

            case "check":
                itogame.broadcastMessage(""+itogame.getTheme());
                break;

            case "end":
                if(itogame.getState().equals(ItoGame.GameState.Running)){
                    itogame.endGame();
                    itogame.showCard();
                }
                break;

            default:
                sender.sendMessage("不明なコマンドです");
                break;
        }

    }
}
