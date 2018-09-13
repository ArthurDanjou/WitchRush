package net.berrygames.witchrush.commands.admins;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.game.StartTask;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        WitchPlayer witchPlayer = WitchPlayer.get(player);

        if(args.length == 1 && args[0].equals("start")){
            if(!player.isOp())return false;

            new StartTask().runTaskTimer(WitchRush.get(), 0, 20);
            WitchRush.get().setState(GameState.STARTING);
            Bukkit.broadcastMessage(WitchRush.prefix()+"§c"+player.getName()+" a forcé le démarrage de la partie !");
        }
        return false;
    }
}
