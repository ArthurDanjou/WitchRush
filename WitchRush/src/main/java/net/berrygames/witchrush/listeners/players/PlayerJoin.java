package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.game.task.StartTask;
import net.berrygames.witchrush.tools.ItemFactory;
import net.berrygames.witchrush.tools.Locations;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent e){
        Player player = e.getPlayer();
        WitchPlayer witchPlayer = WitchPlayer.get(player);

        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setJoinMessage(
                    WitchRush.prefix()+"§e"+player.getName()+
                            " §da rejoint la partie §7(§d"+
                            WitchPlayer.getwitchMap().size()+
                            "§8/§d16§7)");

            player.sendTitle("§5WitchRush", "§DLe rush avec des Witchs");
            player.getInventory().clear();
            player.setGameMode(GameMode.ADVENTURE);
            player.setLevel(0);
            player.setExp(0f);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.teleport(Locations.PLAYER_SPAWN_WAITING_ROOM.getLocation());
            witchPlayer.sendWaitingStuff();
            if(WitchRush.get().getState().equals(GameState.WAITING) && WitchPlayer.getwitchMap().size() >= 4){
                new StartTask().runTaskTimer(WitchRush.get(), 0, 20);
                WitchRush.get().setState(GameState.STARTING);
            }

            if(player.isOp()){
                player.getInventory().setItem(0, new ItemFactory(Material.FEATHER).withName("§cDémarrage forcé").done());
            }
        } else {

            player.sendMessage(" ");
            player.sendMessage("§8(Spectateur) §7Vous êtes spectateur pour cette partie.");
            player.sendMessage("§7Seuls les autres spectateurs voient vos messages !");
            player.sendMessage(" ");
            e.setJoinMessage(null);
            player.setGameMode(GameMode.SPECTATOR);
            player.setLevel(0);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.teleport(Locations.SPAWN_SPECTATORS.getLocation());
        }

    }
}
