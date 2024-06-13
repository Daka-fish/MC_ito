package net.tv.twitch.chrono_fish.ito.GamePack;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class ItoBook {
    private ItemStack book;
    ItoBook(){
        book = new ItemStack(Material.WRITABLE_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        bookMeta.setTitle("ゲーム管理");
        bookMeta.setAuthor("sss");
        bookMeta.addPage();
    }
}
