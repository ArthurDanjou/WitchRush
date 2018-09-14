package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.team.TeamManager;
import net.berrygames.witchrush.team.TeamsMenu;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InteractEvent implements Listener {

    @EventHandler
    public void pvp(EntityDamageEvent e){
        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void pvp(EntityDamageByBlockEvent e){
        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent e){
        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void pvp(EntityDamageByEntityEvent e){
        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void click(PlayerInteractAtEntityEvent e){
        Player player = e.getPlayer();
        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setCancelled(true);
        }
        if(e.getRightClicked().getName().equals("S")){
            e.setCancelled(true);
            player.sendMessage("s");
        }
        if(e.getRightClicked().getName().equals("U")){
            e.setCancelled(true);
            player.sendMessage("u");
        }
        if(e.getRightClicked().getType().equals(EntityType.WITCH)){
            //detect player's witch

            //damage other witch
        }
    }

    @EventHandler
    public void click(PlayerInteractEvent e){
        Player player = e.getPlayer();
        WitchPlayer witchPlayer = WitchPlayer.get(player);
        Action action = e.getAction();
        ItemStack item = e.getItem();
        e.setCancelled(true);

        if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)){
            switch (item.getType()){
                case ARMOR_STAND:
                    new TeamsMenu(player);
                    break;
                case BED:
                    player.sendMessage("retour au hub soon");
                    break;
                    default:break;

            }
        }
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        WitchPlayer witchPlayer = WitchPlayer.get(player);
        ItemStack item = e.getCurrentItem();
        Inventory inv = e.getClickedInventory();
        TeamManager teamManager = WitchRush.get().getTeamManager();
        if(!inv.getTitle().equals(WitchRush.prefix()+" Teams")) return;
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
                player.closeInventory();
                break;
            case BARRIER:
                player.closeInventory();
                break;
            case DOUBLE_PLANT:
                if (teamManager.playerHaveTeam(player)) {
                    player.sendMessage(WitchRush.prefix()+"Vous êtes déjà dans une équipe !");
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                    player.closeInventory();
                    return;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
                teamManager.addPlayerInRandomTeam(player);
                TeamInfos infos2 = teamManager.getPlayerTeam(player);
                player.sendMessage(WitchRush.prefix()+"Vous avez rejoint la team "+infos2.getChatColor()+infos2.getTeamName());
                player.closeInventory();
                break;
        }
    }

}
