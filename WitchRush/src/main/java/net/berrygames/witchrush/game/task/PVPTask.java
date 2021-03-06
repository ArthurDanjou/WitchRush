package net.berrygames.witchrush.game.task;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.game.WinManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class PVPTask extends BukkitRunnable {

    private int timer = 20 * 60;

    @Override
    public void run() {

        if(this.timer == 0){

            //DeathMatch

            Bukkit.broadcastMessage(WitchRush.prefix()+"Le DeathMatch commence.");
            Bukkit.broadcastMessage(WitchRush.prefix()+"Les §5§lWitchs §dcommencent à perdre de la vie !");
            cancel();
        }

        new WinManager();

        this.timer--;
    }
}
