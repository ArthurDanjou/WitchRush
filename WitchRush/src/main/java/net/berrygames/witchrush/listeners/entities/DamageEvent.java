package net.berrygames.witchrush.listeners.entities;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.team.TeamsInfos;
import net.berrygames.witchrush.team.TeamManager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEvent implements Listener {

    private TeamManager teamManager = WitchRush.get().getTeamManager();

    @EventHandler
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent e) {
        switch (GameState.getStatus()){
            case LOBBY:
                break;
            case GAME:
                switch (e.getEntity().getType()){
                    case WITCH:
                        if(!(e.getDamager() instanceof Player)) return;
                        final Player player = (Player) e.getDamager();
                        final Witch witch = (Witch) e.getEntity();
                        final TeamsInfos playerTeam = teamManager.getPlayerTeam(player);
                        if(teamManager.getTeamBoss(playerTeam).getWitch().equals(witch)){
                            e.setCancelled(true);
                            player.sendMessage("§cVous ne pouvez pas tuer votre boss !");
                            return;
                        }
                /*for(TeamsInfos infos : TeamsInfos.values()){
                    if(teamManager.getTeamBoss(infos).getWitch().equals(witch)){
                        Bukkit.getOnlinePlayers().forEach(pls -> {
                            if(teamManager.getPlayerTeam(pls).equals(infos)){
                                pls.sendTitle("§cWitch attaquée !", " ");
                                pls.playSound(pls.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
                            }
                        });
                    }
                }*/
                        break;
                    case VILLAGER:
                        if(e.getDamager() instanceof Player) e.setCancelled(true);
                        break;
                }
                break;
            case END:
                break;
        }
    }
}
