package net.berrygames.witchrush.tools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Villager;

public class SpawnPNJ {

    private Villager villager;
    private String name;
    private Location location;

    public SpawnPNJ(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public void spawn(){
        this.villager = Bukkit.getWorld("world").spawn(location, Villager.class, v ->{
            v.setAI(false);
            v.setCustomName(name);
            v.setCustomNameVisible(true);
            v.setSilent(true);
            v.setGravity(false);
            v.setInvulnerable(true);
        });
    }



}
