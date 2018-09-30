package net.berrygames.witchrush.listeners.entities;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEvent implements Listener {

    private TeamManager teamManager = WitchRush.get().getTeamManager();

    @EventHandler
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent e) {
        if(!WitchRush.get().getState().equals(GameState.PVP) && !WitchRush.get().getState().equals(GameState.DEATH_MATCH) && !WitchRush.get().getState().equals(GameState.NOWITCH) )return;
        switch (e.getEntity().getType()){
            case WITCH:
                if(!(e.getDamager() instanceof Player)) return;
                final Player player = (Player) e.getDamager();
                final Witch witch = (Witch) e.getEntity();
                final TeamInfos infos = teamManager.getPlayerTeam(player);
                if(teamManager.getTeamBoss(infos).getWitch().equals(witch)){
                    e.setCancelled(true);
                    player.sendMessage("§cVous ne pouvez pas tuer votre boss !");
                    return;
                }
                for(TeamInfos teamInfos : TeamInfos.values()){
                    if(!teamManager.isInLife(teamInfos)) return;
                    if(teamManager.getTeamBoss(teamInfos).getWitch().equals(witch)){
                        teamManager.getTeamBoss(teamInfos).getWitch().teleport(teamManager.getBossLocation(teamInfos));
                        Bukkit.getOnlinePlayers().forEach(playerOnline -> {
                            WitchPlayer witchPlayer = WitchPlayer.get(playerOnline);
                            if(teamManager.isPlayerInTeam(playerOnline, teamInfos) && !witchPlayer.isSpectator()){
                                playerOnline.sendTitle("§cWitch attaquée !", " ");
                                playerOnline.playSound(playerOnline.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
                            }
                        });
                    }
                }
                break;
            case VILLAGER:
                if(e.getDamager() instanceof Player) e.setCancelled(true);
                break;
        }
    }
}
