package net.tv.twitch.chrono_fish.ito.GamePack;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private int number;
    private ItemStack paper = new ItemStack(Material.PAPER);

    Card(int i){
        this.number = i;
        ItemMeta paperMeta = paper.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("あなたの数字は"+i+"です");
        paperMeta.setDisplayName(i+"");
        paperMeta.setLore(lore);
        paper.setItemMeta(paperMeta);
    }

    public int getNumber() {
        return number;
    }

    public ItemStack getPaper() {
        return paper;
    }
}
