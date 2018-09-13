package net.berrygames.witchrush.commands.admins;

import net.berrygames.witchrush.game.SpawnPNJ;
import net.berrygames.witchrush.tools.Locations;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equals("shop")){
            //Spawn SHOP
            new SpawnPNJ("S bleu", Locations.SHOP_BLEU.getLocation()).spawn();
            new SpawnPNJ("S rouge", Locations.SHOP_ROUGE.getLocation()).spawn();
            new SpawnPNJ("S vert", Locations.SHOP_VERT.getLocation()).spawn();
            new SpawnPNJ("S jaune", Locations.SHOP_JAUNE.getLocation()).spawn();


        }
        if(args[0].equals("up")){
            //Spawn UPGRADE
            new SpawnPNJ("U bleu", Locations.UPGRADE_BLEU.getLocation()).spawn();
            new SpawnPNJ("U rouge", Locations.UPGRADE_ROUGE.getLocation()).spawn();
            new SpawnPNJ("U vert", Locations.UPGRADE_VERT.getLocation()).spawn();
            new SpawnPNJ("U jaune", Locations.UPGRADE_JAUNE.getLocation()).spawn();
        }

        return false;
    }
}
