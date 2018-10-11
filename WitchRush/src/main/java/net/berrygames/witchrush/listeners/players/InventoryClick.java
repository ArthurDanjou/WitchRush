package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.team.TeamsInfos;
import net.berrygames.witchrush.team.TeamManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

    @EventHandler
    public void inventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        Inventory inv = e.getClickedInventory();
        TeamManager teamManager = WitchRush.get().getTeamManager();
        if(inv.getTitle().equals(WitchRush.prefix()+"Teams")){
            e.setCancelled(true);
            switch (item.getType()){
                case WOOL:
                    final TeamsInfos teamInfos = TeamsInfos.getTeamInfosByShortData(e.getCurrentItem().getDurability());
                    if (teamManager.isPlayerInTeam(player, teamInfos)) {
                        player.sendMessage("§dVous êtes déjà dans cette team !");
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                        player.closeInventory();
                        return;
                    }
                    if (teamManager.teamIsFull(teamInfos)) {
                        player.sendMessage("§dL'équipe est pleine !");
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                        player.closeInventory();
                        return;
                    }
                    teamManager.removePlayerAllTeam(player);
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
                    teamManager.addPlayerTeam(player, teamInfos);
                    TeamsInfos infos1 = teamManager.getPlayerTeam(player);
                    player.sendMessage("§dVous avez rejoint la team "+infos1.getChatColor()+infos1.getTeamName());
                    player.closeInventory();
                    break;
                case BARRIER:
                    player.closeInventory();
                    break;
                case DOUBLE_PLANT:
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
                    teamManager.removePlayerAllTeam(player);
                    player.sendMessage("&dVous serez dans une équipe au debut de la partie !");
                    player.closeInventory();
                    break;
            }
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        switch (GameState.getStatus()){
            case LOBBY:
                e.setCancelled(true);
                break;
            case GAME:
                e.setCancelled(false);
                break;
            case END:
                e.setCancelled(true);
                break;
        }
    }
}
