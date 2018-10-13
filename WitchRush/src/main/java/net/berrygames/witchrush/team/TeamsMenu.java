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
        inventory = Bukkit.createInventory(null, 5 * 9, WitchRush.prefix()+"Teams");

        for(int i = 0; i < 9; i++){
            inventory.setItem(i, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PINK).done());
        }
        for(int i = 36; i < 45; i++){
            inventory.setItem(i, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PINK).done());
        }
        inventory.setItem(9, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PINK).done());
        inventory.setItem(17, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PINK).done());
        inventory.setItem(27, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PINK).done());
        inventory.setItem(35, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PINK).done());

        inventory.setItem(31, new ItemFactory(Material.DOUBLE_PLANT).withName("§dTeam Random").done());

        for(TeamsInfos infos : TeamsInfos.values()){
            inventory.setItem(infos.getSlotGUI(), new ItemFactory(Material.WOOL)
                    .withName(infos.getChatColor()+infos.getTeamName())
                    .withColor(infos.getColor())
                    .done());
        }


        inventory.setItem(40, new ItemFactory(Material.BARRIER).withName("§cFermer").done());
        player.openInventory(inventory);
    }
}
