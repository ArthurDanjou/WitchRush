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
        inventory = Bukkit.createInventory(null, 4 * 9, WitchRush.prefix()+" Teams");

        for(int i = 0; i < 9; i++){
            inventory.setItem(i, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PURPLE).done());
        }
        for(int i = 27; i < 36; i++){
            inventory.setItem(i, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PURPLE).done());
        }
        inventory.setItem(9, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PURPLE).done());
        inventory.setItem(17, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PURPLE).done());
        inventory.setItem(18, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PURPLE).done());
        inventory.setItem(26, new ItemFactory(Material.STAINED_GLASS_PANE).withName("").withColor(DyeColor.PURPLE).done());

        inventory.setItem(22, new ItemFactory(Material.DOUBLE_PLANT).withName("§dTeam Random").done());

        inventory.setItem(12, new ItemFactory(Material.WOOL).withColor(DyeColor.GREEN).withName("§aVert").done());
        inventory.setItem(14, new ItemFactory(Material.WOOL).withColor(DyeColor.YELLOW).withName("§eJaune").done());
        inventory.setItem(20, new ItemFactory(Material.WOOL).withColor(DyeColor.BLUE).withName("§bBleu").done());
        inventory.setItem(24, new ItemFactory(Material.WOOL).withColor(DyeColor.RED).withName("§cRouge").done());

        inventory.setItem(31, new ItemFactory(Material.BARRIER).withName("§cFermer").done());
        player.openInventory(inventory);
    }
}
