package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.team.TeamsMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {

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
        e.setCancelled(true);
        if(e.getRightClicked().getName().equals("§6§lSHOP")){
            e.setCancelled(true);
            player.closeInventory();
        }
        if(e.getRightClicked().getName().equals("§3§lUPGRADE")){
            e.setCancelled(true);
            player.closeInventory();
        }

    }

    @EventHandler
    public void click(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack item = e.getItem();
        e.setCancelled(true);

        if(item == null || item.getType() == Material.AIR) return;

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
}
