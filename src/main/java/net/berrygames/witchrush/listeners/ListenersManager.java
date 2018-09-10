package net.berrygames.witchrush.listeners;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.listeners.players.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class ListenersManager {

    public void register(WitchRush main){
        PluginManager pm = Bukkit.getPluginManager();

        // --- PLAYERS ---//
        pm.registerEvents(new PlayerJoin(), main);

        // --- WORLD ---//

        System.out.println("Events register");
    }
}
