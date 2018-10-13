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

        witchPlayer.removePlayer();
        WitchRush.get().getTeamManager().removePlayerAllTeam(player);

        switch (GameState.getStatus()){
            case LOBBY:
                e.setQuitMessage(null);
                break;
            case GAME:
                if(witchPlayer.isSpectator()) return;
                e.setQuitMessage(
                        WitchRush.prefix()+"§e"+player.getName()+
                                " §da quitté la partie §7(§d"+
                                WitchPlayer.getwitchMap().size()+
                                "§8/§d16§7)");
                break;
            case END:
                e.setQuitMessage(null);
                break;
        }
    }

}
