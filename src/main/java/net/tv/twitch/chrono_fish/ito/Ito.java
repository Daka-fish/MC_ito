package net.tv.twitch.chrono_fish.ito;

import org.bukkit.plugin.java.JavaPlugin;

public final class Ito extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
    }

    public void registerCommands(){
        for(String commandName : getDescription().getCommands().keySet()){
            getCommand(commandName).setExecutor(new ItoCommands());
        }

    }
}
