package net.berrygames.witchrush.game;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.task.HealthRunnable;
import net.berrygames.witchrush.game.task.PVPTask;
import net.berrygames.witchrush.team.TeamsInfos;
import net.berrygames.witchrush.team.TeamManager;
import net.berrygames.witchrush.tools.PNJSpawner;
import net.berrygames.witchrush.tools.WitchBoss;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GameManager {

    public TeamManager teamManager;
    public boolean isStart;

    public GameManager() {
        this.teamManager = WitchRush.get().getTeamManager();
        if (!(this.isStart = false)) {
            this.isStart = true;
            GameState.setStatus(GameState.GAME);

            new PVPTask().runTaskTimer(WitchRush.get(), 0, 20);

            for(Player pls: Bukkit.getOnlinePlayers()){
                pls.setGameMode(GameMode.SURVIVAL);
                pls.playSound(pls.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, 1);
            }
            Bukkit.broadcastMessage(WitchRush.prefix()+"§dLa partie commence !");
            Bukkit.broadcastMessage(WitchRush.prefix()+"§dVous avez §63minutes §dpour vous préparez.");
            this.loadPlayer();

            for(TeamsInfos infos : TeamsInfos.values()){
                new PNJSpawner("§6§lSHOP", infos, new TeamManager().getShopLocation(infos));
                new PNJSpawner("§b§LUPGRADE", infos, new TeamManager().getUpgradeLocation(infos));
            }
            Bukkit.getScheduler().runTaskLater(WitchRush.get(), ()->{
                Bukkit.broadcastMessage(WitchRush.prefix()+"Les §6Witchs §dsont apparues, §d§nBonne chance à vous !");

                for(TeamsInfos infos : TeamsInfos.values()){
                    new WitchBoss(infos, new TeamManager().getBossLocation(infos));
                }

                new HealthRunnable().runTaskTimer(WitchRush.get(), 0L, 20L);
            }, 3 * 20 * 60);
        }
    }

    private void loadPlayer() {
        Bukkit.getOnlinePlayers().forEach(playerOnline -> {
            WitchPlayer witchPlayer = WitchPlayer.get(playerOnline);
            this.teamManager.addPlayerInRandomTeam(playerOnline);
            witchPlayer.teleportToBase();
            playerOnline.getInventory().clear();
            playerOnline.setMaxHealth(20.0);
            playerOnline.setHealth(20.0);
            playerOnline.setFoodLevel(20);
            playerOnline.setFlying(false);
            playerOnline.setAllowFlight(false);
            playerOnline.setLevel(0);
            playerOnline.setGameMode(GameMode.SURVIVAL);
            witchPlayer.giveStuff();
        });
    }

}
