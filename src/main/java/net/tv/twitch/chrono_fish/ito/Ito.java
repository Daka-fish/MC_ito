package net.tv.twitch.chrono_fish.ito;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.tv.twitch.chrono_fish.ito.CommandPack.ItoCommands;
import net.tv.twitch.chrono_fish.ito.GamePack.ItoGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ito extends JavaPlugin {

    private static ItoGame itoGame;

    @Override
    public void onEnable() {
        registerCommands();
        Bukkit.getPluginManager().registerEvents(new ItoEvent(),this);
        itoGame = new ItoGame(ChatColor.GRAY+"-準備中-");
    }

    public static ItoGame getItogame(){
        return itoGame;
    }
    public void registerCommands(){
        for(String commandName : getDescription().getCommands().keySet()){
            getCommand(commandName).setExecutor(new ItoCommands());
        }
    }

    public static TextComponent getMessage(){
        TextComponent message = new TextComponent(ChatColor.GOLD+"ito version 1.1.0 is running. Check the newest release note from ");
        TextComponent here = new TextComponent(ChatColor.UNDERLINE+"here");

        here.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://github.com/Daka-fish/MC_ito/releases"));
        here.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to open the release note")));
        message.addExtra(here);
        return message;
    }
}
