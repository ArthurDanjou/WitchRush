package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.team.TeamManager;
import net.berrygames.witchrush.tools.TeamsTagsManager;
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
        WitchPlayer witchPlayer = WitchPlayer.get(player);
        ItemStack item = e.getCurrentItem();
        Inventory inv = e.getClickedInventory();
        TeamManager teamManager = WitchRush.get().getTeamManager();
        if(inv.getTitle().equals(WitchRush.prefix()+"Teams")){
            e.setCancelled(true);
            switch (item.getType()){
                case WOOL:
                    final TeamInfos teamInfos = TeamInfos.getTeamInfosByShortData(e.getCurrentItem().getDurability());
                    if (teamManager.isPlayerInTeam(player, teamInfos)) {
                        player.sendMessage(WitchRush.prefix()+"Vous êtes déjà dans cette team !");
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                        player.closeInventory();
                        return;
                    }
                    if (teamManager.teamIsFull(teamInfos)) {
                        player.sendMessage(WitchRush.prefix()+"L'équipe est pleine !");
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                        player.closeInventory();
                        return;
                    }
                    teamManager.removePlayerAllTeam(player);
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
                    teamManager.addPlayerTeam(player, teamInfos);
                    TeamInfos infos1 = teamManager.getPlayerTeam(player);
                    player.sendMessage(WitchRush.prefix()+"Vous avez rejoint la team "+infos1.getChatColor()+infos1.getTeamName());
                    TeamsTagsManager.setNameTag(player, player.getName(), infos1.getChatColor()+infos1.getTeamName()+" ");
                    player.closeInventory();
                    break;
                case BARRIER:
                    player.closeInventory();
                    break;
                case DOUBLE_PLANT:
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
                    teamManager.removePlayerAllTeam(player);
                    teamManager.addPlayerInRandomTeam(player);
                    TeamInfos infos2 = teamManager.getPlayerTeam(player);
                    player.sendMessage(WitchRush.prefix()+"Vous avez rejoint la team "+infos2.getChatColor()+infos2.getTeamName()+" ");
                    TeamsTagsManager.setNameTag(player, player.getName(), infos2.getChatColor());
                    player.closeInventory();
                    break;
            }
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setCancelled(true);
        }
    }

}
