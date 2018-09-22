package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.team.TeamManager;
import net.berrygames.witchrush.tools.DeadPlayer;
import net.berrygames.witchrush.tools.Locations;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeath implements Listener {

    private TeamManager teamManager = WitchRush.get().getTeamManager();

    @EventHandler
    public void death(PlayerDeathEvent e) {

        Player player = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();
        WitchPlayer witchPlayer = WitchPlayer.get(player);
        WitchPlayer witchKiller = WitchPlayer.get(killer);

        final TeamInfos teamvictim;
        final TeamInfos teamKiller;

        if(WitchRush.get().getState().equals(GameState.WAITING) || WitchRush.get().getState().equals(GameState.STARTING)){
            e.setDeathMessage(null);
            player.kickPlayer("ENFAIT TOI T'ES FORT PCQ NORMALEMENT YA PAS CA");
        } else {
            if(killer instanceof Player){
                switch (WitchRush.get().getState()){
                    case PVP:
                        if(!teamManager.isInLife(teamManager.getPlayerTeam(player))){
                            checkDrop(e);
                            TeamInfos team = WitchRush.get().getTeamManager().getPlayerTeam(player);
                            e.setDeathMessage(team.getChatColor()+player.getName()+"§c est éliminé");
                            witchPlayer.setDeath(witchPlayer.getDeath()+1);
                            witchPlayer.setSpectator(true);
                            player.teleport(Locations.SPAWN_SPECTATORS.getLocation());
                            player.setGameMode(GameMode.SPECTATOR);
                            teamManager.removePlayerAllTeam(player);

                            player.sendMessage(" ");
                            player.sendMessage("§8(Spectateur) §7Vous êtes éliminé.");
                            player.sendMessage("§7Seuls les autres spectateurs voient vos messages !");
                            player.sendMessage(" ");
                        } else {
                            teamvictim = WitchRush.get().getTeamManager().getPlayerTeam(player);
                            teamKiller = WitchRush.get().getTeamManager().getPlayerTeam(killer);
                            e.setDeathMessage(teamvictim.getChatColor()+player.getName()+"§d a été tué par "+teamKiller.getChatColor()+ killer.getName());
                            checkDrop(e);
                            new DeadPlayer(player);
                            witchKiller.setKills(witchKiller.getKills()+1);
                            witchPlayer.setDeath(witchPlayer.getDeath()+1);
                        }
                        break;
                    case NOWITCH:
                        teamvictim = WitchRush.get().getTeamManager().getPlayerTeam(player);
                        teamKiller = WitchRush.get().getTeamManager().getPlayerTeam(killer);

                        e.setDeathMessage(teamvictim.getChatColor()+player.getName()+"§d a été tué par "+teamKiller.getChatColor()+ killer.getName());
                        checkDrop(e);
                        new DeadPlayer(player);
                        witchKiller.setKills(witchKiller.getKills()+1);
                        witchPlayer.setDeath(witchPlayer.getDeath()+1);
                        break;
                }
            } else {
                TeamInfos team = WitchRush.get().getTeamManager().getPlayerTeam(player);
                e.setDeathMessage(team.getChatColor()+player.getName()+"§d est mort");
                checkDrop(e);
                new DeadPlayer(player);
                player.setHealth(20);
                witchPlayer.setDeath(witchPlayer.getDeath()+1);
            }
        }
    }

    private void checkDrop(PlayerDeathEvent e){
        for(final ItemStack itemStack : e.getDrops()){
            switch (itemStack.getType()){
                case LEATHER_BOOTS:
                    itemStack.setType(Material.AIR);
                    break;
                case LEATHER_LEGGINGS:
                    itemStack.setType(Material.AIR);
                    break;
                case LEATHER_CHESTPLATE:
                    itemStack.setType(Material.AIR);
                    break;
                case LEATHER_HELMET:
                    itemStack.setType(Material.AIR);
                    break;
                case DIAMOND_SWORD:
                    itemStack.setType(Material.AIR);
                    break;
            }
        }
    }
}
