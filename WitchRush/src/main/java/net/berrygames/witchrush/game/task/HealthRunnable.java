package net.berrygames.witchrush.game.task;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class HealthRunnable extends BukkitRunnable {

    public Map<TeamInfos, ArmorStand> arMap;
    private TeamManager teamManager;

    public HealthRunnable() {
        this.arMap = new HashMap<>();
        teamManager = WitchRush.get().getTeamManager();
    }

    public void run() {
        for(TeamInfos teamInfos : TeamInfos.values()){
            if(arMap.get(teamInfos) == null){
                final ArmorStand armorStand = (ArmorStand) Bukkit.getWorld("world").spawnEntity(teamManager.getBossLocation(teamInfos), EntityType.ARMOR_STAND);
                armorStand.setAI(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setGravity(false);
                armorStand.setVisible(false);
                armorStand.setCustomName("Loading..");
                this.arMap.put(teamInfos, armorStand);
                return;
            }
            if(teamManager.isInLife(teamInfos)){
                arMap.get(teamInfos).setCustomName(
                        teamInfos.getChatColor()+teamInfos.getTeamName()
                                + "§7 - "+teamInfos.getChatColor()+teamManager.getTeamBoss(teamInfos).getLife()
                );
            }
        }
    }
}
