package net.berrygames.witchrush.game;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.tools.Locations;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartTask extends BukkitRunnable {

    private int timer = 120;

    @Override
    public void run() {

        if(timer == 0){
            new NoPVPTask().runTaskTimer(WitchRush.get(), 0, 20);
            Bukkit.broadcastMessage(WitchRush.prefix()+"§CLa partie commence ! Vous avez 3minutes pour vous preparer !");
            cancel();

            //TP Player

            //Spawn SHOP
            new SpawnPNJ("S bleu", Locations.SHOP_BLEU.getLocation()).spawn();
            new SpawnPNJ("S rouge", Locations.SHOP_ROUGE.getLocation()).spawn();
            new SpawnPNJ("S vert", Locations.SHOP_VERT.getLocation()).spawn();
            new SpawnPNJ("S jaune", Locations.SHOP_JAUNE.getLocation()).spawn();

            //Spawn UPGRADE
            new SpawnPNJ("U bleu", Locations.UPGRADE_BLEU.getLocation()).spawn();
            new SpawnPNJ("U rouge", Locations.UPGRADE_ROUGE.getLocation()).spawn();
            new SpawnPNJ("U vert", Locations.UPGRADE_VERT.getLocation()).spawn();
            new SpawnPNJ("U jaune", Locations.UPGRADE_JAUNE.getLocation()).spawn();

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
