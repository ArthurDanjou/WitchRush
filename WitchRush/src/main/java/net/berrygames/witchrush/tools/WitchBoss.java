package net.berrygames.witchrush.tools;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Witch;

public class WitchBoss {

    private TeamInfos teamInfos;
    private Location location;
    private TeamManager manager;
    private Witch witch;
    private int life;

    public WitchBoss(final TeamInfos teamInfos, final Location location) {
        this.teamInfos = teamInfos;
        this.location = location;
        this.manager = WitchRush.get().getTeamManager();
        manager.getBossEntityMap().put(teamInfos, this);
        this.witch = (Witch) Bukkit.getWorld("world").spawnEntity(location, EntityType.WITCH);
        this.life = 50;
        this.witch.setMaxHealth(this.life);
        this.witch.setHealth(this.life);
        this.witch.setCustomNameVisible(false);
        this.witch.setAI(false);
        this.witch.setSilent(true);
        this.witch.setCanPickupItems(false);
        this.witch.setCollidable(false);
    }

    public TeamInfos getTeamInfos() {
        return this.teamInfos;
    }

    public Location getLocation() {
        return this.location;
    }

    public Witch getWitch() {
        return this.witch;
    }

    public int getLife() {
        return (int) this.witch.getHealth();
    }

}
