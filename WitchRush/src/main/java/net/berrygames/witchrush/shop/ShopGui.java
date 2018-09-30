package net.berrygames.witchrush.shop;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ShopGui {

    private Player player;
    private Inventory inventory;

    public ShopGui(Player player) {
        this.player = player;
        this.inventory = Bukkit.createInventory(null, 9 * 5, "§6§lSHOP");

        

        this.player.openInventory(this.inventory);
    }
}
