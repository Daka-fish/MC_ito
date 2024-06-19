package net.tv.twitch.chrono_fish.ito.ArmorStandPack;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ItoArmorStand {

    private final int id;
    private final String name;
    private final Location loc;

    public ItoArmorStand(int id, String name,Location loc){
        this.id = id;
        this.name = name;
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

    public void displayPlayerNumber(Player player){
        player.sendMessage("アーマーを召喚します");
        createArmorStand(loc,player.getName());
    }
}
