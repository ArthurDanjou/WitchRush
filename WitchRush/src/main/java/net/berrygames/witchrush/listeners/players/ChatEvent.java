package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.team.TeamInfos;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void chat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        WitchPlayer witchPlayer = WitchPlayer.get(player);

        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setFormat("§7%1$s §7» §f%2$s");
        } else if(witchPlayer.isSpectator()){
            e.setCancelled(true);
            for(WitchPlayer pls : WitchPlayer.getwitchMap().values()){
                for(Player players : Bukkit.getOnlinePlayers()){
                    if(pls.isSpectator()){
                        players.sendMessage("§7[Spectateur] §8"+player.getName()+" §7» "+e.getMessage());
                    }
                }
            }
        } else {
            String message = e.getMessage();
            if(message.toLowerCase().startsWith("!")){
                e.setFormat("§7[§6Global§7] "+WitchRush.get().getTeamManager().getPlayerTeam(player).getChatColor()+"%1$s §7» §f%2$s");
            } else {
                e.setCancelled(true);
                Bukkit.getOnlinePlayers().forEach(playerOnline -> {
                    final TeamInfos teamInfos = WitchRush.get().getTeamManager().getPlayerTeam(player);
                    if(WitchRush.get().getTeamManager().getPlayerTeam(playerOnline).equals(teamInfos)){
                        playerOnline.sendMessage("§7["+teamInfos.getChatColor()+teamInfos.getTeamName()+"§7] "
                                +WitchRush.get().getTeamManager().getPlayerTeam(playerOnline).getChatColor()
                                +player.getDisplayName()+" §7» §f"+message.replace("!",""));
                    }
                });
            }
        }
    }

}
