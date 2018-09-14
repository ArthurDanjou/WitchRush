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
    //team


    public DeadPlayer(Player player) {
        this.player = player;
        this.witchPlayer = WitchPlayer.get(player);
        this.witchPlayer.setSpectator(true);

        becomeSpec();
    }

    private void becomeSpec(){
        player.setGameMode(GameMode.SPECTATOR);
        witchPlayer.teleportPlayer();
        sendTitle();
    }

    private void sendTitle(){
        player.sendTitle("§cVous êtes mort", null);
        task = Bukkit.getScheduler().runTaskTimer(WitchRush.get(), new Runnable() {
            @Override
            public void run() {
                if(timer == 0){
                    player.sendTitle("§cVous êtes mort", "§c§oRespawn...");
                    respawn();
                    Bukkit.getScheduler().cancelTask(task.getTaskId());
                }
                player.sendTitle("§cVous êtes mort", "§c§oRespawn dans "+timer+"...");
                timer--;
            }
        }, 20, 20);
    }

    private void respawn(){
        witchPlayer.setSpectator(false);
        player.setGameMode(GameMode.SURVIVAL);
        //TP BASE EQUIPE
        witchPlayer.giveStuff();
    }
}
