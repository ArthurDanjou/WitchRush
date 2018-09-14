package net.berrygames.witchrush.game;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.tools.Locations;
import net.berrygames.witchrush.tools.SpawnWitch;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class NoPVPTask extends BukkitRunnable {

    private int timer = 30;

    @Override
    public void run() {
        if(timer == 0){

            new PVPTask().runTaskTimer(WitchRush.get(), 0, 20);
            WitchRush.get().setState(GameState.PVP);

            Bukkit.broadcastMessage(WitchRush.prefix()+"Les §6Witchs §dsont apparues, §d§nBonne chance à vous !");
            new SpawnWitch("W", Locations.WITCH_ROUGE.getLocation()).spawn();
            new SpawnWitch("W", Locations.WITCH_BLEU.getLocation()).spawn();
            new SpawnWitch("W", Locations.WITCH_VERT.getLocation()).spawn();
            new SpawnWitch("W", Locations.WITCH_JAUNE.getLocation()).spawn();
            cancel();
        }
        timer--;
    }
}
