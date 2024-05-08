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
                if(args.length<2){
                    sender.sendMessage(ChatColor.RED+"不明なコマンド\n/ito start <theme>");
                    return;
                }
                itogame = new ItoGame(args[1]);
                itogame.startGame();
                itogame.reloadBossBar();
                itogame.broadcastMessage("テーマは"+itogame.getTheme()+"です");
                itogame.dealCard();
                break;

            case "change":
                if(args.length<2){
                    sender.sendMessage(ChatColor.RED+"不明なコマンド\n /ito change <theme>");
                    return;
                }
                itogame.setTheme(args[1]);
                itogame.reloadBossBar();
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
