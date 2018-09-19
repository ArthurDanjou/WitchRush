package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.game.task.HealthRunnable;
import net.berrygames.witchrush.listeners.custom.BossDeathEvent;
import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.team.TeamManager;
import net.berrygames.witchrush.tools.DeadPlayer;
import net.berrygames.witchrush.tools.Locations;
import net.berrygames.witchrush.tools.TeamsTagsManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathEvent implements Listener {

    TeamManager teamManager = WitchRush.get().getTeamManager();

    @EventHandler
    public void death(PlayerDeathEvent e) {

        Player player = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();
        WitchPlayer witchPlayer = WitchPlayer.get(player);
        WitchPlayer witchKiller = WitchPlayer.get(killer);

        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setDeathMessage(null);
            player.kickPlayer("ENFAIT TOI T'ES FORT PCQ NORMALEMENT YA PAS CA");
        } else {
            if(killer instanceof Player){
                switch (WitchRush.get().getState()){
                    case PVP:
                        if(teamManager.isInLife(teamManager.getPlayerTeam(player))){
                            TeamInfos teamvictim = WitchRush.get().getTeamManager().getPlayerTeam(player);
                            TeamInfos teamKiller = WitchRush.get().getTeamManager().getPlayerTeam(killer);

                            e.setDeathMessage(WitchRush.prefix()+teamvictim.getChatColor()+player.getName()+"§d a été tué par "+teamKiller.getChatColor()+ killer.getName());
                            new DeadPlayer(player);
                            witchKiller.setKills(witchKiller.getKills()+1);
                            witchPlayer.setDeath(witchPlayer.getDeath()+1);
                        } else {
                            TeamInfos team = WitchRush.get().getTeamManager().getPlayerTeam(player);
                            e.setDeathMessage(WitchRush.prefix()+team.getChatColor()+player.getName()+"§c est éliminé");
                            witchPlayer.setDeath(witchPlayer.getDeath()+1);
                            witchPlayer.setSpectator(true);
                            player.teleport(Locations.SPAWN_SPECTATORS.getLocation());
                            player.setGameMode(GameMode.SPECTATOR);
                            teamManager.removePlayerAllTeam(player);

                            player.sendMessage(" ");
                            player.sendMessage("§8(Spectateur) §7Vous êtes éliminé.");
                            player.sendMessage("§7Seuls les autres spectateurs voient vos messages !");
                            player.sendMessage(" ");

                            TeamsTagsManager.setNameTag(player, player.getName(), "§8(Spec)§7 ");

                        }
                        break;
                    case NOWITCH:
                        TeamInfos teamvictim = WitchRush.get().getTeamManager().getPlayerTeam(player);
                        TeamInfos teamKiller = WitchRush.get().getTeamManager().getPlayerTeam(killer);

                        e.setDeathMessage(WitchRush.prefix()+teamvictim.getChatColor()+player.getName()+"§d a été tué par "+teamKiller.getChatColor()+ killer.getName());
                        new DeadPlayer(player);
                        witchKiller.setKills(witchKiller.getKills()+1);
                        witchPlayer.setDeath(witchPlayer.getDeath()+1);
                        break;
                }


            } else {
                TeamInfos team = WitchRush.get().getTeamManager().getPlayerTeam(player);
                e.setDeathMessage(WitchRush.prefix()+team.getChatColor()+player.getName()+"§d est mort");
                new DeadPlayer(player);
                player.setHealth(20);
                witchPlayer.setDeath(witchPlayer.getDeath()+1);
            }
        }
    }

    @EventHandler
    public void onEntityDeath(final EntityDeathEvent e) {
        if (e.getEntity() instanceof Witch) {
            if(e.getEntity().getKiller() instanceof Player){
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
                    Bukkit.getPluginManager().callEvent(new BossDeathEvent(teamInfos, e.getEntity().getKiller()));
                    new HealthRunnable().arMap.get(teamInfos).setCustomNameVisible(false);
                    new HealthRunnable().arMap.remove(teamInfos);
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
}
