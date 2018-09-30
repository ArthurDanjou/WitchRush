package net.berrygames.witchrush.shop;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class UpgradeGUI {

    private Player player;
    private Inventory inventory;

    public UpgradeGUI(Player player) {
        this.player = player;
        this.inventory = Bukkit.createInventory(null, 9 * 5, "§3§lUPGRADEE");



        this.player.openInventory(this.inventory);
    }

}
