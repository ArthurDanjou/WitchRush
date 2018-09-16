package net.berrygames.witchrush.game.task;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.DeathMatchTask;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class PVPTask extends BukkitRunnable {

    int timer = 20 * 60;

    @Override
    public void run() {

        if(timer == 0){
            new DeathMatchTask();
            Bukkit.broadcastMessage(WitchRush.prefix()+"Le DeathMatch commence.");
            Bukkit.broadcastMessage(WitchRush.prefix()+"Les §5§lWitchs §dcommencent à perdre de la vie !");
            cancel();
        }

        timer--;
    }
}
