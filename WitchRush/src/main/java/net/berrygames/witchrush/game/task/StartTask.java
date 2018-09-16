package net.berrygames.witchrush.game.task;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartTask extends BukkitRunnable {

    private int timer = 30;

    @Override
    public void run() {

        if(timer == 0){
            new GameManager();
            cancel();
        }

        if(timer == 120 || timer == 60 || timer == 30 || timer == 15
                || timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2){
            Bukkit.broadcastMessage(WitchRush.prefix()+"Lancement de la partie dans §5"+timer+" §dsecondes");
            for(Player pls : Bukkit.getOnlinePlayers()){
                pls.playSound(pls.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            }
        }
        if(timer == 1){
            Bukkit.broadcastMessage(WitchRush.prefix()+"Lancement de la partie dans §5"+timer+" §dseconde");
            for(Player pls : Bukkit.getOnlinePlayers()){
                pls.playSound(pls.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            }
        }

        for(Player pls : Bukkit.getOnlinePlayers()){
            pls.setLevel(timer);
        }

        timer--;
    }
}
