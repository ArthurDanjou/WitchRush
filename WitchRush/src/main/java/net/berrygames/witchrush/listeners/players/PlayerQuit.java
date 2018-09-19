package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.game.task.StartTask;
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

        e.setQuitMessage(null);

        witchPlayer.removePlayer();
        WitchRush.get().getTeamManager().removePlayerAllTeam(player);

        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setQuitMessage(
                    WitchRush.prefix()+"§e"+player.getName()+
                            " §da rejoint la partie §7(§d"+
                            WitchPlayer.getwitchMap().size()+
                            "§8/§d16§7)");
        } else {
            e.setQuitMessage(null);
        }

        if(WitchPlayer.getwitchMap().size() < 4 && WitchRush.get().getState().equals(GameState.STARTING)){
            Bukkit.broadcastMessage(WitchRush.prefix()+"§CLe lancement de la partie est annulé. Il n'y a pas assez de joueurs !");
            if(!new StartTask().isCancelled()){
                new StartTask().cancel();
            }
        }
    }

}
