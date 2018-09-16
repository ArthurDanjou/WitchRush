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

    public HealthRunnable() {
        this.arMap = new HashMap<>();
    }

    public void run() {
        final TeamManager teamManager = WitchRush.get().getTeamManager();
        for (final TeamInfos teamInfos : TeamInfos.values()) {
            if (this.arMap.get(teamInfos) == null) {
                final Location bossLocation = teamManager.getBossLocation(teamInfos).getLocation();
                final ArmorStand armorStand = (ArmorStand) Bukkit.getWorld("world").spawnEntity(bossLocation, EntityType.ARMOR_STAND);
                armorStand.setAI(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setGravity(false);
                armorStand.setVisible(false);
                armorStand.setCustomName("Loading..");
                this.arMap.put(teamInfos, armorStand);
                return;
            }
            this.arMap.get(teamInfos).setCustomName("§5§lVie: §c"+teamManager.getTeamBoss(teamInfos).getLife());
        }
    }
}
