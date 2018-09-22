package net.berrygames.witchrush.listeners.entities;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDeath implements Listener {

    private TeamManager teamManager = WitchRush.get().getTeamManager();

    @EventHandler
    public void onEntityDeath(final EntityDeathEvent e) {
        if(e.getEntity() instanceof Witch){
            if(!WitchRush.get().getState().equals(GameState.PVP) || !WitchRush.get().getState().equals(GameState.DEATH_MATCH)) return;
            final Witch witch = (Witch) e.getEntity();
            for (final ItemStack itemStack : e.getDrops()) {
                itemStack.setType(Material.AIR);
            }
            for(TeamInfos infos : TeamInfos.values()){
                if(teamManager.getTeamBoss(infos).getWitch().equals(witch)){
                    Bukkit.broadcastMessage(WitchRush.prefix()+"L'équipe "+infos.getChatColor()+infos.getTeamName()+" §dest morte !");
                    for (final Player playerOnline : Bukkit.getOnlinePlayers()) {
                          playerOnline.playSound(playerOnline.getLocation(), Sound.ENTITY_WITHER_DEATH, 1.0f, 1.0f);
                          if (teamManager.isPlayerInTeam(playerOnline, infos)) {
                              playerOnline.sendMessage(WitchRush.prefix()+"Votre Boss est mort! Ne mourrez pas !");
                              playerOnline.sendTitle("§cAttention","Votre boss est mort !");
                              teamManager.killTeamBoss(infos);
                          }
                    }
                }
            }
        }
    }
}
