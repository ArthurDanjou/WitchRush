package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.kits.Kits;
import net.berrygames.witchrush.team.TeamsInfos;
import net.berrygames.witchrush.team.TeamManager;
import net.berrygames.witchrush.tools.DeadPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
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

        final TeamsInfos teamvictim;
        final TeamsInfos teamKiller;

        switch (GameState.getStatus()){
            case LOBBY:
                e.setDeathMessage(null);
                player.kickPlayer("ENFAIT TOI T'ES FORT PCQ NORMALEMENT YA PAS CA");
                break;
            case GAME:
                if(killer instanceof Player){
                    if(!teamManager.isInLife(teamManager.getPlayerTeam(player))){
                        checkDrop(e);
                        TeamsInfos team = WitchRush.get().getTeamManager().getPlayerTeam(player);
                        e.setDeathMessage(team.getChatColor()+player.getName()+"§c est éliminé");
                        witchPlayer.setDeath(witchPlayer.getDeath()+1);
                        witchPlayer.setSpectator(true);
                        player.teleport(new Location(Bukkit.getWorld("world"), 0, 66, 0));
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
                } else {
                    TeamsInfos team = WitchRush.get().getTeamManager().getPlayerTeam(player);
                    e.setDeathMessage(team.getChatColor()+player.getName()+"§d est mort");
                    checkDrop(e);
                    new DeadPlayer(player);
                    player.setHealth(20);
                    witchPlayer.setDeath(witchPlayer.getDeath()+1);
                }
                break;
            case END:
                e.setDeathMessage(null);
                player.kickPlayer("ENFAIT TOI T'ES FORT PCQ NORMALEMENT YA PAS CA");
                break;
        }
    }

    private void checkDrop(PlayerDeathEvent e){
        for(final ItemStack itemStack : e.getDrops()){
            for(Kits kits : Kits.values()){
                if(itemStack.getType().equals(kits.getItems())) itemStack.setType(Material.AIR);
            }
        }
    }
}
