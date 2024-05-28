package net.tv.twitch.chrono_fish.ito.CommandPack;

import net.tv.twitch.chrono_fish.ito.GamePack.Card;
import net.tv.twitch.chrono_fish.ito.GamePack.ItoGame;
import net.tv.twitch.chrono_fish.ito.Ito;
import net.tv.twitch.chrono_fish.ito.ItoScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandManager {

    static ItoGame itogame = Ito.getItogame();

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
                if(itogame.getState().equals(ItoGame.GameState.Finished)){
                    itogame = new ItoGame(args[1]);
                    itogame.startGame();
                    itogame.reloadBossBar();
                    itogame.broadcastMessage("テーマは"+itogame.getTheme()+"です");
                    itogame.dealCard();
                    for(Player player: Bukkit.getOnlinePlayers()){
                        player.setScoreboard(new ItoScoreboard().getBoard());
                    }
                } else {
                    sender.sendMessage(ChatColor.RED+"既にゲームが進行中です");
                }
                break;

            case "change":
                if(args.length<2){
                    sender.sendMessage(ChatColor.RED+"不明なコマンド\n /ito change <theme>");
                    return;
                }
                if(itogame.getState().equals(ItoGame.GameState.Running)){
                    itogame.setTheme(args[1]);
                    itogame.broadcastMessage("テーマが"+itogame.getTheme()+"に変更されました");
                    itogame.reloadBossBar();
                    return;
                }
                sender.sendMessage(ChatColor.RED+"進行中のゲームはありません\n");
                break;

            case "check":
                List<Card> field = itogame.getField();
                if(field.isEmpty()){
                    sender.sendMessage(ChatColor.RED+"field is empty !\n");
                    return;
                }
                if(itogame.checkField()){
                    itogame.broadcastMessage("成功!");
                } else {
                    itogame.broadcastMessage("失敗!");
                }
                field.clear();
                break;

            case "end":
                if(itogame.getState().equals(ItoGame.GameState.Running)){
                    itogame.endGame();
                    itogame.showCard();
                    itogame.setTheme("ito");
                    itogame.reloadBossBar();
                    itogame.getMap().clear();
                    return;
                }
                sender.sendMessage(ChatColor.RED+"進行中のゲームはありません\n");
                break;

            default:
                sender.sendMessage("不明なコマンドです");
                break;
        }

    }
}
