package net.berrygames.witchrush.commands.admins;

import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.tools.PNJSpawner;
import net.berrygames.witchrush.tools.Locations;
import net.berrygames.witchrush.tools.WitchBoss;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equals("shop")){
            //Spawn SHOP
            //Spawn SHOP
            new PNJSpawner("§6§lSHOP", TeamInfos.BLEU, Locations.SHOP_BLEU.getLocation());
            new PNJSpawner("§6§lSHOP", TeamInfos.ROUGE, Locations.SHOP_ROUGE.getLocation());
            new PNJSpawner("§6§lSHOP", TeamInfos.VERT, Locations.SHOP_VERT.getLocation());
            new PNJSpawner("§6§lSHOP", TeamInfos.JAUNE, Locations.SHOP_JAUNE.getLocation());


        }
        if(args[0].equals("up")){
            //Spawn UPGRADE
            new PNJSpawner("§b§LUPGRADE", TeamInfos.JAUNE, Locations.UPGRADE_BLEU.getLocation());
            new PNJSpawner("§b§LUPGRADE", TeamInfos.ROUGE, Locations.UPGRADE_ROUGE.getLocation());
            new PNJSpawner("§b§LUPGRADE", TeamInfos.VERT, Locations.UPGRADE_VERT.getLocation());
            new PNJSpawner("§b§LUPGRADE", TeamInfos.JAUNE, Locations.UPGRADE_JAUNE.getLocation());
        }

        if(args[0].equals("w")){
            new WitchBoss(TeamInfos.ROUGE, Locations.WITCH_ROUGE.getLocation());
            new WitchBoss(TeamInfos.BLEU, Locations.WITCH_BLEU.getLocation());
            new WitchBoss(TeamInfos.VERT, Locations.WITCH_VERT.getLocation());
            new WitchBoss(TeamInfos.JAUNE, Locations.WITCH_JAUNE.getLocation());
        }

        return false;
    }
}
