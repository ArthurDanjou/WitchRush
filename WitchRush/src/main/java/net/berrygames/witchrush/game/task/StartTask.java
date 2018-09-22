package net.berrygames.witchrush.game.task;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameManager;
import net.berrygames.witchrush.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartTask extends BukkitRunnable {

    private int sec = 0;
    private int min = WitchRush.get().isForcedStart() ? 0 : 2 * 60;

    @Override
    public void run() {

        if(this.sec == 0){
            this.sec = 60;
            this.min -= min;
        }

        if (Bukkit.getOnlinePlayers().size() < 4 && !WitchRush.get().isForcedStart()) {
            Bukkit.broadcastMessage(WitchRush.prefix()+"Il n'y a pas assez de joueurs pour lancer la partie !");
            WitchRush.get().setState(GameState.WAITING);
            this.cancel();
            return;
        }

        if(this.min == 0 && this.sec == 0){
            WitchRush.get().getTeamManager().checkNoTeamPlayers();
            new GameManager();
            cancel();
        }

        while(this.min == 2){
            if(this.sec == 0){
                Bukkit.broadcastMessage(WitchRush.prefix()+"Lancement de la partie dans §5"+this.sec+" §dsecondes");
                for(Player pls : Bukkit.getOnlinePlayers()){
                    pls.playSound(pls.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
                }
            }
        }
        while(this.min == 1){
            if(this.sec == 30){
                Bukkit.broadcastMessage(WitchRush.prefix()+"Lancement de la partie dans §5"+this.sec+" §dsecondes");
                for(Player pls : Bukkit.getOnlinePlayers()){
                    pls.playSound(pls.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
                }
            }
        }
        while(this.min == 0){
            if(this.sec == 30 || this.sec == 15 || this.sec == 10 || this.sec == 5 || this.sec == 4
                    || this.sec == 3 || this.sec == 2){
                Bukkit.broadcastMessage(WitchRush.prefix()+"Lancement de la partie dans §5"+this.sec+" §dsecondes");
                for(Player pls : Bukkit.getOnlinePlayers()){
                    pls.playSound(pls.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
                }
            }
            if(this.sec == 1){
                Bukkit.broadcastMessage(WitchRush.prefix()+"Lancement de la partie dans §5"+this.sec+" §dseconde");
                for(Player pls : Bukkit.getOnlinePlayers()){
                    pls.playSound(pls.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
                }
            }
        }
        this.sec--;
    }

    public String getTimer() {
        return min+":"+sec;
    }
}
