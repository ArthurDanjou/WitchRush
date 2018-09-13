package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.StartTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void quit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        WitchPlayer witchPlayer = WitchPlayer.get(player);

        witchPlayer.removePlayer();

        if(WitchPlayer.getwitchMap().size() < 4){
            Bukkit.broadcastMessage(WitchRush.prefix()+"§CLe lancement de la partie est annulé. Il n'y a pas assez de joueurs !");
            new StartTask().cancel();
        }
    }

}
