package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.tools.Locations;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void move(PlayerMoveEvent e){
        Player player = e.getPlayer();
        if(player.getLocation().getBlockY() <= 110 && GameState.getStatus().equals(GameState.LOBBY)){
            player.teleport(Locations.WAITING_ROOMS.getLoc());
            player.sendMessage("§cNe tombe pas dans le néant !");
        }
    }

}
