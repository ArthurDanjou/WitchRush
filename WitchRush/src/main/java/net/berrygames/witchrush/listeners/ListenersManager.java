package net.berrygames.witchrush.listeners;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.listeners.players.*;
import net.berrygames.witchrush.listeners.world.WorldEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class ListenersManager {

    public void register(WitchRush main){
        PluginManager pm = Bukkit.getPluginManager();

        // --- PLAYERS ---//
        pm.registerEvents(new PlayerJoin(), main);
        pm.registerEvents(new PlayerQuit(), main);
        pm.registerEvents(new InteractEvent(), main);
        pm.registerEvents(new ChatEvent(), main);
        pm.registerEvents(new DeathEvent(), main);
        pm.registerEvents(new FoodLevel(), main);

        // --- WORLD ---//
        pm.registerEvents(new WorldEvents(), main);

        System.out.println("Events register");
    }
}
