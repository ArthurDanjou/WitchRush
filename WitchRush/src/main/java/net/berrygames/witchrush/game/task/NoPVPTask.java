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

    private int timer = 3 * 20;

    @Override
    public void run() {

        if(timer == 0){
            new PVPTask().runTaskTimer(WitchRush.get(), 0, 20);
            WitchRush.get().setState(GameState.PVP);

            Bukkit.broadcastMessage(WitchRush.prefix()+"Les §6Witchs §dsont apparues, §d§nBonne chance à vous !");

            new WitchBoss(TeamInfos.ROUGE, Locations.WITCH_ROUGE.getLocation());
            new WitchBoss(TeamInfos.BLEU, Locations.WITCH_BLEU.getLocation());
            new WitchBoss(TeamInfos.VERT, Locations.WITCH_VERT.getLocation());
            new WitchBoss(TeamInfos.JAUNE, Locations.WITCH_JAUNE.getLocation());
            new HealthRunnable().runTaskTimer(WitchRush.get(), 0L, 20L);
            cancel();
        }
        this.timer--;
    }
}
