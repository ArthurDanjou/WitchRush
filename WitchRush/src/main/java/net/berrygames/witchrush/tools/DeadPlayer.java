package net.berrygames.witchrush.tools;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class DeadPlayer {

    private Player player;
    private WitchPlayer witchPlayer;
    private int timer = 5;
    private BukkitTask task;


    public DeadPlayer(Player player) {
        this.player = player;
        this.witchPlayer = WitchPlayer.get(player);
        this.witchPlayer.setSpectator(true);
        player.setHealth(20);
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(Locations.SPECTATORS.getLoc());
        player.sendTitle("§cVous êtes mort", " ");
        task = Bukkit.getScheduler().runTaskTimer(WitchRush.get(), new Runnable() {
            @Override
            public void run() {
                if(timer == 0){
                    player.sendTitle("§cVous êtes mort", "§c§oRespawn...");
                    witchPlayer.setSpectator(false);
                    player.setGameMode(GameMode.SURVIVAL);
                    witchPlayer.giveStuff();
                    witchPlayer.teleportToBase();
                    Bukkit.getScheduler().cancelTask(task.getTaskId());
                }
                player.sendTitle("§cVous êtes mort", "§c§oRespawn dans "+timer+"...");
                timer--;
            }
        }, 20, 20);
    }
}
