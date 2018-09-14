package net.berrygames.witchrush.game;

import org.bukkit.scheduler.BukkitRunnable;

public class PVPTask extends BukkitRunnable {

    int timer = 20 * 60;

    @Override
    public void run() {

        if(timer == 0){
            new DeathMatchTask();
            cancel();
        }

        timer--;
    }
}
