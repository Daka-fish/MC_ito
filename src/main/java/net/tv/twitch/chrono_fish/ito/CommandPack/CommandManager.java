package net.tv.twitch.chrono_fish.ito.CommandPack;

import net.tv.twitch.chrono_fish.ito.GamePack.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandManager {
    public static void ito(Player sender, String[] args){
        if(args.length == 0){
            sender.sendMessage(ChatColor.RED+"Invalid usage");
            return;
        }

        GameManager gameManager = new GameManager();

        switch (args[0]) {
            case "start":
                sender.sendMessage("ゲームを開始します");
                gameManager.startGame(args[1]);
                break;
                
            case "call":
                sender.sendMessage("数字の宣言を開始します");
                break;

            case "check":
                sender.sendMessage("成功判定をします");
                break;

            case "end":
                sender.sendMessage("ゲームを終了します");
                gameManager.endGame();
                break;
            default:
                sender.sendMessage("不明なコマンドです");
                break;
        }

    }
}
