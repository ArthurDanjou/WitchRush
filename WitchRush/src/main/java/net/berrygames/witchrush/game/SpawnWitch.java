package net.berrygames.witchrush.game;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class SpawnWitch {

    private EntityType entity;
    private String name;
    private Location location;

    public SpawnWitch(String name, Location location) {
        this.entity = EntityType.WITCH;
        this.name = name;
        this.location = location;
    }

    public void spawn(){
        Entity witch = location.getWorld().spawnEntity(location, entity);
        witch.setCustomName(name);
        witch.setCustomNameVisible(true);
        witch.setSilent(true);
        witch.setInvulnerable(true);
        net.m
    }


}
