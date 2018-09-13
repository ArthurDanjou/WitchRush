package net.berrygames.witchrush.game;

import net.berrygames.witchrush.WitchRush;
import org.bukkit.scheduler.BukkitRunnable;

public class NoPVPTask extends BukkitRunnable {

    private int timer = 60 * 3;

    @Override
    public void run() {
        WitchRush.get().setState(GameState.NOPVP);

        timer--;
    }
}
