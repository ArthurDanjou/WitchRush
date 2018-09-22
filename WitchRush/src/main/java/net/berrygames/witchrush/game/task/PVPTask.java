package net.berrygames.witchrush.game.task;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.game.WinManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PVPTask extends BukkitRunnable {

    private int sec = 0;
    private int min = 20 * 60;

    @Override
    public void run() {

        if(this.sec == 0){
            this.sec = 60;
            this.min -= min;
        }

        if(this.min == 0 && this.sec == 0){
            new DeathMatchTask().runTaskTimer(WitchRush.get(), 0, 20 * 5);
            for(Player pls : Bukkit.getOnlinePlayers()){
                WitchPlayer.get(pls).sendGameScoreboard();
            }
            WitchRush.get().setState(GameState.DEATH_MATCH);
            Bukkit.broadcastMessage(WitchRush.prefix()+"Le DeathMatch commence.");
            Bukkit.broadcastMessage(WitchRush.prefix()+"Les §5§lWitchs §dcommencent à perdre de la vie !");
            cancel();
        }

        new WinManager();

        this.sec--;
    }

    public String getTimer() {
        return this.min+":"+this.sec;
    }

}
