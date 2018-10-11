package net.berrygames.witchrush.listeners.servers;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPing implements Listener {

    @EventHandler
    public void ping(ServerListPingEvent e){
        e.setMotd(WitchRush.prefix()+ GameState.getStatus());
        e.setMaxPlayers(16);
    }

}
