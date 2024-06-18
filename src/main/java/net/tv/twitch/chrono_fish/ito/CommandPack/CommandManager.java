package net.tv.twitch.chrono_fish.ito.CommandPack;

import net.tv.twitch.chrono_fish.ito.GamePack.Card;
import net.tv.twitch.chrono_fish.ito.GamePack.ItoGame;
import net.tv.twitch.chrono_fish.ito.Ito;
import net.tv.twitch.chrono_fish.ito.ItoScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandManager {

    public static void ito(Player sender, String[] args){
        final ItoGame itogame = Ito.getItogame();
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
                    itogame.setTheme(args[1]);
                    itogame.startGame();
                    itogame.broadcastMessage("テーマは"+itogame.getTheme()+"です");
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        int currentNumber = itogame.getNumberHashMap().get(player.getName()).getNumber();
                        ItoScoreboard itoScoreboard = itogame.getScoreboardHashMap().get(player);
                        itogame.dealCard(player);
                        itoScoreboard.updateTheme(itogame.getFirstTheme());
                        itoScoreboard.updateNumber(currentNumber);
                        itogame.setGameLoc(sender.getLocation());
                    });
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
                    String currentTheme = itogame.getTheme();
                    itogame.setTheme(args[1]);
                    itogame.broadcastMessage("テーマが"+itogame.getTheme()+"に変更されました");
                    Bukkit.getOnlinePlayers().forEach(player -> itogame.getScoreboardHashMap().get(player).updateTheme(currentTheme));
                    return;
                }
                sender.sendMessage(ChatColor.RED+"進行中のゲームはありません\n");
                break;

            case "check":
                if(itogame.getCardField().isEmpty()){
                    sender.sendMessage(ChatColor.RED+"field is empty!");
                    return;
                }
                itogame.openCard();
                break;

            case "end":
                if(itogame.getState().equals(ItoGame.GameState.Running)){
                    String currentTheme = itogame.getTheme();
                    itogame.endGame();
                    itogame.showCard();
                    itogame.setTheme(itogame.getFirstTheme());
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        int currentNumber = itogame.getNumberHashMap().get(player.getName()).getNumber();
                        itogame.getScoreboardHashMap().get(player).updateTheme(currentTheme);
                        itogame.getNumberHashMap().put(player.getName(), new Card(-1));
                        itogame.getScoreboardHashMap().get(player).updateNumber(currentNumber);
                        itogame.getScoreboardHashMap().get(player).clearOrder();
                    });
                    itogame.getCardField().clear();
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
