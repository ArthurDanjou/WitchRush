package net.berrygames.witchrush.game.task;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.tools.Locations;
import net.berrygames.witchrush.tools.WitchBoss;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class NoPVPTask extends BukkitRunnable {

    private int sec = 0;
    private int min = 3 * 60;

    @Override
    public void run() {

        if(this.sec == 0){
            this.sec = 60;
            this.min -= min;
        }

        if(this.min == 0 && this.sec == 0){

            new PVPTask().runTaskTimer(WitchRush.get(), 0, 20);
            WitchRush.get().setState(GameState.PVP);

            for(Player pls : Bukkit.getOnlinePlayers()){
                WitchPlayer.get(pls).sendGameScoreboard();
            }

            Bukkit.broadcastMessage(WitchRush.prefix()+"Les §6Witchs §dsont apparues, §d§nBonne chance à vous !");
            new WitchBoss(TeamInfos.ROUGE, Locations.WITCH_ROUGE.getLocation());
            new WitchBoss(TeamInfos.BLEU, Locations.WITCH_BLEU.getLocation());
            new WitchBoss(TeamInfos.VERT, Locations.WITCH_VERT.getLocation());
            new WitchBoss(TeamInfos.JAUNE, Locations.WITCH_JAUNE.getLocation());
            new HealthRunnable().runTaskTimer(WitchRush.get(), 0L, 20L);
            cancel();
        }
        sec--;
    }

    public String getTimer() {
        return this.min+":"+this.sec;
    }
}
