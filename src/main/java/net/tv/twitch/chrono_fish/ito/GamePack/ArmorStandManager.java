package net.tv.twitch.chrono_fish.ito.GamePack;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ArmorStandManager {

    private final Location loc;

    public ArmorStandManager(Location loc){
        this.loc = loc;
    }

    public void createArmorStand(Location location, String customName){
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setCustomName(customName);
        armorStand.setCustomNameVisible(true);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
    }

    public void displayPlayerNumber(Location gameLoc){
        Location baseLocation = gameLoc;
        int index = 0;

        for(Player player : Bukkit.getOnlinePlayers()){
            Location loc = baseLocation.clone().add(0,index*0.3,0);
            createArmorStand(loc,player.getName());
        }
    }
}
