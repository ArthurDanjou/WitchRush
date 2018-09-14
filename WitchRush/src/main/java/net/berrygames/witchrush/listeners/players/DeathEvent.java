package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.tools.DeadPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    @EventHandler
    public void death(PlayerDeathEvent e) throws InterruptedException {
        Player player = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();
        WitchPlayer witchPlayer = WitchPlayer.get(player);
        WitchPlayer witchKiller = WitchPlayer.get(killer);

        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setDeathMessage(null);
            player.kickPlayer("ENFAIT TOI T'ES FORT PCQ NORMALEMENT YA PAS CA");
        } else {
            e.setDeathMessage(WitchRush.prefix()+"§e"+player.getName()+"§d a été tué par §e"+ killer.getName());
            new DeadPlayer(player);
            player.setHealth(20);

            killer.sendMessage("§dVous avez tué §e"+player.getName());
            witchKiller.setKills(witchKiller.getKills()+1);

            player.sendMessage("§e"+killer.getName()+" §dvous avez tué");
            witchPlayer.setDeath(witchPlayer.getDeath()+1);
        }
    }

}
