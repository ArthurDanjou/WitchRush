package net.berrygames.witchrush.listeners.world;

import net.berrygames.witchrush.game.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldEvents implements Listener {

    @EventHandler
    public void place(BlockPlaceEvent e){
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
    @EventHandler
    public void breakB(BlockBreakEvent e){
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
    @EventHandler
    public void damage(BlockDamageEvent e){
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
    @EventHandler
    public void onWeatherChange(final WeatherChangeEvent e){
        e.setCancelled(true);
    }
}
