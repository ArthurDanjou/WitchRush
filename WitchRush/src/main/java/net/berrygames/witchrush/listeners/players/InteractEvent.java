package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

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

}
