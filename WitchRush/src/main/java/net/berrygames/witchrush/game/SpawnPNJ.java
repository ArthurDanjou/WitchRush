package net.berrygames.witchrush.game;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class SpawnPNJ {

    private EntityType entity;
    private String name;
    private Location location;

    public SpawnPNJ(String name, Location location) {
        this.entity = EntityType.VILLAGER;
        this.name = name;
        this.location = location;
    }

    public void spawn(){
        Entity witch = location.getWorld().spawnEntity(location, entity);
        witch.setCustomName(name);
        witch.setCustomNameVisible(true);
        witch.setSilent(true);
        witch.setInvulnerable(true);
    }


}
