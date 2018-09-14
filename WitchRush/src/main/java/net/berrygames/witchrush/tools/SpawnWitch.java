package net.berrygames.witchrush.tools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Witch;

public class SpawnWitch {

    private Witch witch;
    private String name;
    private Location location;

    public SpawnWitch(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public void spawn(){
        this.witch = Bukkit.getWorld("world").spawn(location, Witch.class, w ->{
            w.setAI(false);
            w.setCustomName(name);
            w.setCustomNameVisible(true);
            w.setSilent(true);
            w.setGravity(false);
        });
    }


}
