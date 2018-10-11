package net.berrygames.witchrush.game.task;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameManager;
import net.berrygames.witchrush.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartTask extends BukkitRunnable {

    private int timer = WitchRush.get().isForcedStart() ? 30 : 2 * 60;

    @Override
    public void run() {

        if (Bukkit.getOnlinePlayers().size() < 4 && !WitchRush.get().isForcedStart()) {
            Bukkit.broadcastMessage(WitchRush.prefix()+"Il n'y a pas assez de joueurs pour lancer la partie !");
            GameState.setStatus(GameState.LOBBY);
            this.cancel();
            return;
        }

        if(this.timer > 10 && Bukkit.getOnlinePlayers().size() > 4 && !WitchRush.get().isForcedStart()){
            this.timer = 10;
        }

        if(this.timer == 0){
            new GameManager();
            cancel();
        }
        if(this.timer == 1){
            Bukkit.broadcastMessage(WitchRush.prefix()+"Lancement de la partie dans §5"+this.timer+" §dseconde");
            for(Player pls : Bukkit.getOnlinePlayers()){
                pls.playSound(pls.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            }
        }
        if(this.timer == 120 || this.timer == 60 ||this.timer == 30 || this.timer == 15 || this.timer == 10 || this.timer == 5 || this.timer == 4
                || this.timer == 3 || this.timer == 2 ){
            Bukkit.broadcastMessage(WitchRush.prefix()+"Lancement de la partie dans §5"+this.timer+" §dsecondes");
            for(Player pls : Bukkit.getOnlinePlayers()){
                pls.playSound(pls.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            }
        }

        for(Player pls : Bukkit.getOnlinePlayers()){
            pls.setLevel(timer);
        }

        this.timer--;
    }
}
