package net.berrygames.witchrush.game.task;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.WinManager;
import net.berrygames.witchrush.team.TeamInfos;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathMatchTask extends BukkitRunnable {

    private int sec = 0;
    private int min = 3 * 60;

    @Override
    public void run() {

        if(this.sec == 60){
            this.sec = 0;
            this.min += min;
        }

        for(Player pls : Bukkit.getOnlinePlayers()){
            WitchPlayer.get(pls).sendGameScoreboard();
        }

        for(TeamInfos infos : TeamInfos.values()){
            if(WitchRush.get().getTeamManager().isInLife(infos)){
                if(WitchRush.get().getTeamManager().getBossEntityMap().get(infos).getLife() > 0){
                    int life = (WitchRush.get().getTeamManager().getBossEntityMap().get(infos).getLife() - 10);
                    WitchRush.get().getTeamManager().getBossEntityMap().get(infos).getWitch().setHealth(life);
                }
                return;
            }
        }

        new WinManager();
        this.sec++;
    }

    public String getTimer() {
        return this.min+":"+this.sec;
    }
}
