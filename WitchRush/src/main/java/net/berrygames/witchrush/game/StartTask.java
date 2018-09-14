package net.berrygames.witchrush.game;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.team.TeamManager;
import net.berrygames.witchrush.tools.Locations;
import net.berrygames.witchrush.tools.PNJSpawner;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartTask extends BukkitRunnable {

    private int timer = 30;

    @Override
    public void run() {

        if(timer == 0){
            new NoPVPTask().runTaskTimer(WitchRush.get(), 0, 20);
            WitchRush.get().setState(GameState.NOWITCH);

            Bukkit.broadcastMessage(WitchRush.prefix()+"§dLa partie commence !");
            Bukkit.broadcastMessage(WitchRush.prefix()+"§dVous avez §63minutes §dpour vous préparez.");
            cancel();

            for(WitchPlayer witchPlayer : WitchPlayer.getwitchMap().values()){
                witchPlayer.giveStuff();
            }
            for(Player pls: Bukkit.getOnlinePlayers()){
                pls.setGameMode(GameMode.SURVIVAL);

                //Join si il a pas de team
                if(WitchRush.get().getTeamManager().getPlayerTeam(pls) == null){
                    WitchRush.get().getTeamManager().addPlayerInRandomTeam(pls);
                }

                //Tp aux bases
                TeamInfos teamInfos = WitchRush.get().getTeamManager().getPlayerTeam(pls);
                WitchPlayer.get(pls).teleportToBase();
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

        if(timer == 120 || timer == 60 || timer == 30 || timer == 15
                || timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2){
            Bukkit.broadcastMessage(WitchRush.prefix()+"Lancement de la partie dans §5"+timer+" §dsecondes");
        }
        if(timer == 1){
            Bukkit.broadcastMessage(WitchRush.prefix()+"Lancement de la partie dans §5"+timer+" §dseconde");
        }

        for(Player pls : Bukkit.getOnlinePlayers()){
            pls.setLevel(timer);
        }

        timer--;
    }
}
