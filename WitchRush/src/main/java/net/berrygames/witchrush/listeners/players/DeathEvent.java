package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.listeners.custom.BossDeathEvent;
import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.team.TeamManager;
import net.berrygames.witchrush.tools.DeadPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Witch;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathEvent implements Listener {

    @EventHandler
    public void death(PlayerDeathEvent e) throws InterruptedException {
        Player player = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();
        WitchPlayer witchPlayer = WitchPlayer.get(player);
        WitchPlayer witchKiller = WitchPlayer.get(killer);

        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setDeathMessage(null);
            player.kickPlayer("ENFAIT TOI T'ES FORT PCQ NORMALEMENT YA PAS CA");
        } else {
            if(!killer.getType().equals(EntityType.PLAYER))return;
            if(killer.getType().equals(EntityType.PLAYER)){
                TeamInfos teamvictim = WitchRush.get().getTeamManager().getPlayerTeam(player);
                TeamInfos teamKiller = WitchRush.get().getTeamManager().getPlayerTeam(killer);

                e.setDeathMessage(WitchRush.prefix()+teamvictim.getChatColor()+player.getName()+"§d a été tué par "+teamKiller.getChatColor()+ killer.getName());
                new DeadPlayer(player);
                player.setHealth(20);

                killer.sendMessage("§dVous avez tué §e"+player.getName());
                witchKiller.setKills(witchKiller.getKills()+1);

                player.sendMessage("§e"+killer.getName()+" §dvous avez tué");
                witchPlayer.setDeath(witchPlayer.getDeath()+1);
            } else {
                TeamInfos team = WitchRush.get().getTeamManager().getPlayerTeam(player);
                e.setDeathMessage(WitchRush.prefix()+team.getChatColor()+player.getName()+"§d est mort");
                new DeadPlayer(player);
                player.setHealth(20);
                player.sendMessage("§d Vous êtes mort !");
                witchPlayer.setDeath(witchPlayer.getDeath()+1);
            }
        }
    }

    @EventHandler
    public void onEntityDeath(final EntityDeathEvent e) {
        if (e.getEntity() instanceof Witch) {
            final Witch witch = (Witch)e.getEntity();
            final TeamManager teamManager = WitchRush.get().getTeamManager();
            for (final ItemStack itemStack : e.getDrops()) {
                itemStack.setType(Material.AIR);
            }
            TeamInfos teamInfos = null;
            for (final TeamInfos teamInfosList : TeamInfos.values()) {
                if (teamManager.getTeamBoss(teamInfosList).getWitch().equals(witch)) {
                    teamInfos = teamInfosList;
                }
            }
            Bukkit.broadcastMessage(WitchRush.prefix()+"L'équipe "+teamInfos.getChatColor()+teamInfos.getTeamName()+" §dest morte !");
            if (e.getEntity().getKiller() instanceof Player) {
                Bukkit.getPluginManager().callEvent((Event)new BossDeathEvent(teamInfos, e.getEntity().getKiller()));
            }
            for (final Player playerOnline : Bukkit.getOnlinePlayers()) {
                playerOnline.playSound(playerOnline.getLocation(), Sound.ENTITY_WITHER_DEATH, 1.0f, 1.0f);
                if (teamManager.isPlayerInTeam(playerOnline, teamInfos)) {
                    playerOnline.sendMessage(WitchRush.prefix()+"Votre Boss est mort! Ne mourrez pas !");
                    playerOnline.sendTitle("§cAttention","Votre boss est mort !");
                }
            }
        }
    }

}
