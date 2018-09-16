package net.berrygames.witchrush.listeners.players;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevel implements Listener {

    @EventHandler
    public void onFoodLevelChange(final FoodLevelChangeEvent e) {
        e.setFoodLevel(20);
    }

}
