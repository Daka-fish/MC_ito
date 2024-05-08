package net.tv.twitch.chrono_fish.ito;

import net.tv.twitch.chrono_fish.ito.CommandPack.ItoCommands;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ito extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        Bukkit.getPluginManager().registerEvents(new ItoEvent(),this);
    }

    public void registerCommands(){
        for(String commandName : getDescription().getCommands().keySet()){
            getCommand(commandName).setExecutor(new ItoCommands());
        }
    }
}
