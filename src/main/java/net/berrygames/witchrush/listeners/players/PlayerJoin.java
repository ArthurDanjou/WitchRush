package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent e){
        Player player = e.getPlayer();
        WitchPlayer witchPlayer = new WitchPlayer(player);

        if(WitchRush.get().getState().equals(GameState.WAITING) ||
                WitchRush.get().getState().equals(GameState.STARTING)){
            e.setJoinMessage(
                    WitchRush.prefix()+player.getName()
                            +" a rejoint la partie §7(§d"
                            +WitchPlayer.getwitchMap().size()
                            +"§8/§d16§7)");
            player.setGameMode(GameMode.ADVENTURE);
            player.setHealth(20);
            player.setFoodLevel(20);
            witchPlayer.teleportPlayer();
            witchPlayer.sendGameScoreboard();
        }
    }
}
