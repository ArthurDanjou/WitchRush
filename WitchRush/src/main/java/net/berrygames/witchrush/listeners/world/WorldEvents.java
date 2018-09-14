package net.berrygames.witchrush.listeners.world;

import net.berrygames.witchrush.WitchRush;
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
        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void breake(BlockBreakEvent e){
        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void damage(BlockDamageEvent e){
        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWeatherChange(final WeatherChangeEvent e) {
        e.setCancelled(true);
    }
}
