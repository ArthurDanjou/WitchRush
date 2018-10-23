package net.berrygames.witchrush.tools;

import net.berrygames.witchrush.team.TeamsInfos;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum Locations {

    WAITING_ROOMS(new Location(Bukkit.getWorld("world"), -177, 153, 139.5, 0, 0)),
    SPECTATORS(new Location(Bukkit.getWorld("world"), -50.5, 114, 51.5, 0, 0)),

    SPAWN_JAUNE(new Location(Bukkit.getWorld("world"), 72.5, 98,  59, 90, 0)),
    SPAWN_VERT(new Location(Bukkit.getWorld("world"), -58.5, 98, 177, -180, 0)),
    SPAWN_BLEU(new Location(Bukkit.getWorld("world"), -45, 98, -74.5, 0, 0)),
    SPAWN_ROUGE(new Location(Bukkit.getWorld("world"), -49.5, 91, 116.5, 180, 0)),

    UPGRADE_JAUNE(new Location(Bukkit.getWorld("world"), 76.5, 97, 70.5, 130, 0)),
    UPGRADE_VERT(new Location(Bukkit.getWorld("world"), -70.5, 97, 179.5, -100, 0)),
    UPGRADE_BLEU(new Location(Bukkit.getWorld("world"), -34.5, 97, -78.5, 79, 0)),
    UPGRADE_ROUGE(new Location(Bukkit.getWorld("world"), -49.5, 91, 116.5, 180, 0)),

    SHOP_JAUNE(new Location(Bukkit.getWorld("world"), 66.5, 97, 72.5, -150, 0)),
    SHOP_VERT(new Location(Bukkit.getWorld("world"), -71.5, 97, 170.5, -60, 0)),
    SHOP_BLEU(new Location(Bukkit.getWorld("world"), -32.5, 97, -69.5, 120, 0)),
    SHOP_ROUGE(new Location(Bukkit.getWorld("world"), -172.5, 98, 31.5, 30, 0)),

    BOSS_JAUNE(new Location(Bukkit.getWorld("world"), 12.5, 91, 49.5, 90, 0)),
    BOSS_VERT(new Location(Bukkit.getWorld("world"), -49.5, 91, 116.5, 180, 0)),
    BOSS_BLEU(new Location(Bukkit.getWorld("world"), -54.5, 91, -15.5, 0,0)),
    BOSS_ROUGE(new Location(Bukkit.getWorld("world"), -172.5, 98, 31.5, 30, 0)),

    ;

    private Location loc;

    Locations(Location loc) {
        this.loc = loc;
    }

    public Location getLoc() {
        return loc;
    }
}
