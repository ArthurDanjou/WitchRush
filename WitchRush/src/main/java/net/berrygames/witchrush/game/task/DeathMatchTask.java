package net.berrygames.witchrush.game.task;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.team.TeamInfos;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathMatchTask extends BukkitRunnable {

    int lifeLosed = 0;

    @Override
    public void run() {

        for(TeamInfos infos : TeamInfos.values()){
            if(WitchRush.get().getTeamManager().isInLife(infos)){
                if(WitchRush.get().getTeamManager().getBossEntityMap().get(infos).getLife() > 0){
                    int life = (WitchRush.get().getTeamManager().getBossEntityMap().get(infos).getLife() - 10);
                    WitchRush.get().getTeamManager().getBossEntityMap().get(infos).setLife(life);
                }
                return;
            }
        }

        lifeLosed++;
    }
}
