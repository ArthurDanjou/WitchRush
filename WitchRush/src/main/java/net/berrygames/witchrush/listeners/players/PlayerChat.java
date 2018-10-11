package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.team.TeamsInfos;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler
    public void chat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        WitchPlayer witchPlayer = WitchPlayer.get(player);

        switch (GameState.getStatus()){
            case LOBBY:
                e.setFormat("§7%1$s §7» §f%2$s");
                if(witchPlayer.isSpectator()){
                    e.setCancelled(true);
                    for(Player players : Bukkit.getOnlinePlayers()){
                        WitchPlayer pls = WitchPlayer.get(players);
                        if(pls.isSpectator()){
                            players.sendMessage("§7[Spectateur] §8"+player.getName()+" §7» "+e.getMessage());
                        }
                    }
                }
                break;
            case GAME:
                String message = e.getMessage();
                if(message.startsWith("@") || message.startsWith("!")){
                    e.setFormat("§7[§6Global§7] "+WitchRush.get().getTeamManager().getPlayerTeam(player).getChatColor()+"%1$s §7» §f%2$s");
                } else {
                    e.setCancelled(true);
                    Bukkit.getOnlinePlayers().forEach(playerOnline -> {
                        final TeamsInfos teamInfos = WitchRush.get().getTeamManager().getPlayerTeam(player);
                        if(WitchRush.get().getTeamManager().getPlayerTeam(playerOnline).equals(teamInfos)){
                            playerOnline.sendMessage("§7["+teamInfos.getChatColor()+teamInfos.getTeamName()+"§7] "
                                    +WitchRush.get().getTeamManager().getPlayerTeam(playerOnline).getChatColor()
                                    +player.getDisplayName()+" §7» §f"+message.replace("!","§r"));
                        }
                    });
                }
                break;
            case END:
                e.setFormat("§7%1$s §7» §f%2$s");
                if(witchPlayer.isSpectator()){
                    e.setCancelled(true);
                    for(Player players : Bukkit.getOnlinePlayers()){
                        WitchPlayer pls = WitchPlayer.get(players);
                        if(pls.isSpectator()){
                            players.sendMessage("§7[Spectateur] §8"+player.getName()+" §7» "+e.getMessage());
                        }
                    }
                }
                break;
        }
    }

}
