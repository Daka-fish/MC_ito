package net.tv.twitch.chrono_fish.ito;

import net.tv.twitch.chrono_fish.ito.CommandPack.ItoCommands;
import net.tv.twitch.chrono_fish.ito.GamePack.ItoGame;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ito extends JavaPlugin {

    private static ItoGame itoGame;

    @Override
    public void onEnable() {
        registerCommands();
        Bukkit.getPluginManager().registerEvents(new ItoEvent(),this);
        itoGame = new ItoGame("準備中");
    }

    public static ItoGame getItogame(){
        return itoGame;
    }
    public void registerCommands(){
        for(String commandName : getDescription().getCommands().keySet()){
            getCommand(commandName).setExecutor(new ItoCommands());
        }
    }
}
