package net.berrygames.witchrush.listeners;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.listeners.players.PlayerJoin;
import net.berrygames.witchrush.listeners.players.PlayerQuit;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class ListenersManager {

    public void register(WitchRush main){
        PluginManager pm = Bukkit.getPluginManager();

        // --- PLAYERS ---//
        pm.registerEvents(new PlayerJoin(), main);
        pm.registerEvents(new PlayerQuit(), main);

        // --- WORLD ---//

        System.out.println("Events register");
    }
}
