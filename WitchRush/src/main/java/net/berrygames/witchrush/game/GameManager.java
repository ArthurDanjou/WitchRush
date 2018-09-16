package net.berrygames.witchrush.game;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.task.HealthRunnable;
import net.berrygames.witchrush.game.task.NoPVPTask;
import net.berrygames.witchrush.listeners.custom.GAGameStartEvent;
import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.team.TeamManager;
import net.berrygames.witchrush.tools.Locations;
import net.berrygames.witchrush.tools.PNJSpawner;
import net.berrygames.witchrush.tools.TeamsTagsManager;
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
            WitchRush.get().setState(GameState.NOWITCH);
            String startMessage = WitchRush.prefix()+"§dVous avez §63minutes §dpour vous préparez.";
            Bukkit.broadcastMessage(WitchRush.prefix()+"§dLa partie commence !");
            Bukkit.broadcastMessage(startMessage);
            Bukkit.getServer().getPluginManager().callEvent(new GAGameStartEvent(startMessage));
            this.loadPlayer();

            for(WitchPlayer witchPlayer : WitchPlayer.getwitchMap().values()){
                witchPlayer.giveStuff();
            }
            for(Player pls: Bukkit.getOnlinePlayers()){
                pls.setGameMode(GameMode.SURVIVAL);

                pls.playSound(pls.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, 1);

                //Join si il a pas de team
                if(WitchRush.get().getTeamManager().getPlayerTeam(pls) == null){
                    WitchRush.get().getTeamManager().addPlayerInRandomTeam(pls);
                }
            }

            //Spawn SHOP
            new PNJSpawner("§6§lSHOP", TeamInfos.BLEU, Locations.SHOP_BLEU.getLocation());
            new PNJSpawner("§6§lSHOP", TeamInfos.ROUGE, Locations.SHOP_ROUGE.getLocation());
            new PNJSpawner("§6§lSHOP", TeamInfos.VERT, Locations.SHOP_VERT.getLocation());
            new PNJSpawner("§6§lSHOP", TeamInfos.JAUNE, Locations.SHOP_JAUNE.getLocation());

            //Spawn UPGRADE
            new PNJSpawner("§b§LUPGRADE", TeamInfos.JAUNE, Locations.UPGRADE_BLEU.getLocation());
            new PNJSpawner("§b§LUPGRADE", TeamInfos.ROUGE, Locations.UPGRADE_ROUGE.getLocation());
            new PNJSpawner("§b§LUPGRADE", TeamInfos.VERT, Locations.UPGRADE_VERT.getLocation());
            new PNJSpawner("§b§LUPGRADE", TeamInfos.JAUNE, Locations.UPGRADE_JAUNE.getLocation());

        }
        new NoPVPTask().runTaskTimer(WitchRush.get(), 0, 20);
    }

    private void loadPlayer() {
        Bukkit.getOnlinePlayers().forEach(playerOnline -> {
            WitchPlayer witchPlayer = WitchPlayer.get(playerOnline);
            this.teamManager.addPlayerInRandomTeam(playerOnline);
            TeamInfos teamInfos = this.teamManager.getPlayerTeam(playerOnline);
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
            TeamsTagsManager.setNameTag(playerOnline, teamInfos.getIDName(), teamInfos.getChatColor()+teamInfos.getTeamName());
            witchPlayer.sendGameScoreboard();
        });
    }

}
