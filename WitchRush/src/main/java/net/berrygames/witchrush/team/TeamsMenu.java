package net.berrygames.witchrush.team;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.tools.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TeamsMenu {

    private Player player;
    private Inventory inventory;

    public TeamsMenu(Player player) {
        this.player = player;
        inventory = Bukkit.createInventory(null, 4 * 9, WitchRush.prefix()+"Teams");

        for(int i = 0; i < 9; i++){
            inventory.setItem(i, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PINK).done());
        }
        for(int i = 45; i < 54; i++){
            inventory.setItem(i, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PINK).done());
        }
        inventory.setItem(9, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PINK).done());
        inventory.setItem(17, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PINK).done());
        inventory.setItem(36, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PINK).done());
        inventory.setItem(44, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PINK).done());

        inventory.setItem(40, new ItemFactory(Material.DOUBLE_PLANT).withName("§dTeam Random").done());

        for(int i = 0; i < TeamsInfos.values().length; i++){
            for(int slot = 10; slot < 17; slot++){
                inventory.setItem(slot, new ItemFactory(Material.WOOL).withName(TeamsInfos.getTeamInfosByID(i).getTeamName()).done());
            }
            for(int slot = 19; slot < 26; slot++){
                inventory.setItem(slot, new ItemFactory(Material.WOOL).withName(TeamsInfos.getTeamInfosByID(i).getTeamName()).done());
            }
            inventory.setItem(30, new ItemFactory(Material.WOOL).withName(TeamsInfos.getTeamInfosByID(i).getTeamName()).done());
            inventory.setItem(32, new ItemFactory(Material.WOOL).withName(TeamsInfos.getTeamInfosByID(i).getTeamName()).done());
        }

        inventory.setItem(49, new ItemFactory(Material.BARRIER).withName("§cFermer").done());
        player.openInventory(inventory);
    }
}
