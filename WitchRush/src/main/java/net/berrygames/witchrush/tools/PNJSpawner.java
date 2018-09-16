package net.berrygames.witchrush.tools;

import net.berrygames.witchrush.team.TeamInfos;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class PNJSpawner {

    private TeamInfos teamInfos;
    private Location location;
    private Villager villager;
    private double life;
    private String name;

    public PNJSpawner(String name, final TeamInfos teamInfos, final Location location) {
        this.teamInfos = teamInfos;
        this.location = location;
        this.villager = (Villager) Bukkit.getWorld("world").spawnEntity(location, EntityType.VILLAGER);
        this.life = 100;
        this.villager.setMaxHealth(this.life);
        this.villager.setHealth(this.life);
        this.name = name;
        this.villager.setCustomName(this.name);
        this.villager.setCustomNameVisible(true);
        this.villager.setAI(false);
        this.villager.setSilent(true);
        this.villager.setCanPickupItems(false);
        this.villager.setCollidable(false);
    }

    public TeamInfos getTeamInfos() {
        return this.teamInfos;
    }

    public Location getLocation() {
        return this.location;
    }

    public Villager getvillager() {
        return this.villager;
    }

    public double getLife() {
        return this.life;
    }

    public String getName() {
        return name;
    }
}
