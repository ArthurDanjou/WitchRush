package net.berrygames.witchrush.commands.admins;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocationsCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if(!player.isOp())return false;

        if(!GameState.getStatus().equals(GameState.LOBBY)) return false;

        if(args.length == 2){
            switch (args[0].toLowerCase()){
                case "bleu":
                    switch (args[1].toLowerCase()){
                        case "shop":
                            WitchRush.get().setConf("teams.bleu.shop.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.bleu.shop.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.bleu.shop.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.bleu.shop.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.bleu.shop.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        case "spawn":
                            WitchRush.get().setConf("teams.bleu.spawn.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.bleu.spawn.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.bleu.spawn.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.bleu.spawn.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.bleu.spawn.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        case "upgrade":
                            WitchRush.get().setConf("teams.bleu.upgrade.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.bleu.upgrade.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.bleu.upgrade.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.bleu.upgrade.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.bleu.upgrade.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        case "boss":
                            WitchRush.get().setConf("teams.bleu.boss.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.bleu.boss.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.bleu.boss.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.bleu.boss.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.bleu.boss.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        default:
                            player.sendMessage("§cUsage: /setLocation [team] [shop|spawn|upgrade|boss]");
                            break;
                    }
                    break;
                case "vert":
                    switch (args[1].toLowerCase()){
                        case "shop":
                            WitchRush.get().setConf("teams.vert.shop.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.vert.shop.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.vert.shop.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.vert.shop.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.vert.shop.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        case "spawn":
                            WitchRush.get().setConf("teams.vert.spawn.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.vert.spawn.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.vert.spawn.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.vert.spawn.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.vert.spawn.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        case "upgrade":
                            WitchRush.get().setConf("teams.vert.upgrade.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.vert.upgrade.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.vert.upgrade.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.vert.upgrade.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.vert.upgrade.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        case "boss":
                            WitchRush.get().setConf("teams.vert.boss.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.vert.boss.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.vert.boss.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.vert.boss.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.vert.boss.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        default:
                            player.sendMessage("§cUsage: /setLocation [team] [shop|spawn|upgrade|boss]");
                            break;
                    }
                    break;
                case "jaune":
                    switch (args[1].toLowerCase()){
                        case "shop":
                            WitchRush.get().setConf("teams.jaune.shop.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.jaune.shop.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.jaune.shop.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.jaune.shop.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.jaune.shop.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        case "spawn":
                            WitchRush.get().setConf("teams.jaune.spawn.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.jaune.spawn.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.jaune.spawn.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.jaune.spawn.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.jaune.spawn.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        case "upgrade":
                            WitchRush.get().setConf("teams.jaune.upgrade.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.jaune.upgrade.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.jaune.upgrade.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.jaune.upgrade.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.jaune.upgrade.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        case "boss":
                            WitchRush.get().setConf("teams.jaune.boss.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.jaune.boss.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.jaune.boss.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.jaune.boss.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.jaune.boss.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        default:
                            player.sendMessage("§cUsage: /setLocation [team] [shop|spawn|upgrade|boss]");
                            break;
                    }
                    break;
                case "rouge":
                    switch (args[1].toLowerCase()){
                        case "shop":
                            WitchRush.get().setConf("teams.rouge.shop.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.rouge.shop.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.rouge.shop.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.rouge.shop.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.rouge.shop.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        case "spawn":
                            WitchRush.get().setConf("teams.rouge.spawn.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.rouge.spawn.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.rouge.spawn.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.rouge.spawn.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.rouge.spawn.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        case "upgrade":
                            WitchRush.get().setConf("teams.rouge.upgrade.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.rouge.upgrade.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.rouge.upgrade.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.rouge.upgrade.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.rouge.upgrade.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        case "boss":
                            WitchRush.get().setConf("teams.rouge.boss.x", player.getLocation().getX());
                            WitchRush.get().setConf("teams.rouge.boss.y", player.getLocation().getY());
                            WitchRush.get().setConf("teams.rouge.boss.z", player.getLocation().getZ());
                            WitchRush.get().setConf("teams.rouge.boss.yaw", player.getLocation().getYaw());
                            WitchRush.get().setConf("teams.rouge.boss.pitch", player.getLocation().getPitch());
                            player.sendMessage("Location ajoutée !");
                            break;
                        default:
                            player.sendMessage("§cUsage: /setLocation [team] [shop|spawn|upgrade|boss]");
                            break;
                    }
                    break;
                    default:
                        player.sendMessage("§cUsage: /setLocation [team] [shop|spawn|upgrade|boss]");
                        break;
            }
        }
        return false;
    }

}
