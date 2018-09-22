package net.berrygames.witchrush.listeners;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.listeners.entities.DamageEvent;
import net.berrygames.witchrush.listeners.entities.EntityDeath;
import net.berrygames.witchrush.listeners.players.*;
import net.berrygames.witchrush.listeners.servers.ServerPing;
import net.berrygames.witchrush.listeners.world.WorldEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class ListenersManager {

    public void register(WitchRush main){
        PluginManager pm = Bukkit.getPluginManager();

        // --- PLAYERS ---//
        pm.registerEvents(new PlayerJoin(), main);
        pm.registerEvents(new PlayerQuit(), main);
        pm.registerEvents(new PlayerInteract(), main);
        pm.registerEvents(new PlayerChat(), main);
        pm.registerEvents(new PlayerDeath(), main);
        pm.registerEvents(new PlayerFood(), main);
        pm.registerEvents(new InventoryClick(), main);
        pm.registerEvents(new DamageEvent(), main);

        // --- WORLD ---//
        pm.registerEvents(new WorldEvents(), main);
        pm.registerEvents(new ServerPing(), main);

        // --- ENTITIES ---//
        pm.registerEvents(new EntityDeath(), main);
    }
}
