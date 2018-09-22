package net.berrygames.witchrush.tools;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum Locations {

    //PLAYERS
    PLAYER_SPAWN_WAITING_ROOM(new Location(Bukkit.getWorld("world"), 0.5, 65, 0.5, 0, 0)),
    PLAYER_SPAWN_BLEU(new Location(Bukkit.getWorld("world"), 5.5, 65, -4.5, 50, 0)),
    PLAYER_SPAWN_ROUGE(new Location(Bukkit.getWorld("world"), -4.5, 65, 5.5, -120, 0)),
    PLAYER_SPAWN_JAUNE(new Location(Bukkit.getWorld("world"), 5.5, 65, 5.5, 120, 0)),
    PLAYER_SPAWN_VERT(new Location(Bukkit.getWorld("world"), -4.5, 65, -4.5, -50, 0)),

    SPAWN_SPECTATORS(new Location(Bukkit.getWorld("world"), 0.5, 70, 0.5, 0, 0)),

    //WITCH
    WITCH_BLEU(new Location(Bukkit.getWorld("world"), 9.5, 65, -8.5, 50, 0)),
    WITCH_ROUGE(new Location(Bukkit.getWorld("world"), -8.5, 65, 9.5, -120, 0)),
    WITCH_JAUNE(new Location(Bukkit.getWorld("world"), 9.5, 65, 9.5, 120, 0)),
    WITCH_VERT(new Location(Bukkit.getWorld("world"), -8.5, 65, -8.5, -50, 0)),

    //PNJ SHOP
    SHOP_BLEU(new Location(Bukkit.getWorld("world"), 9.5, 65, -5.5, 90, 0)),
    SHOP_ROUGE(new Location(Bukkit.getWorld("world"), -8.5, 65, 6.5, -90, 0)),
    SHOP_JAUNE(new Location(Bukkit.getWorld("world"), 6.5, 65, 9.5, 190, 0)),
    SHOP_VERT(new Location(Bukkit.getWorld("world"), -5.5, 65, -8.5, 0, 0)),

    //PNJ SOLO UPGRADE
    UPGRADE_BLEU(new Location(Bukkit.getWorld("world"), 6.5, 65, -8.5, 0, 0)),
    UPGRADE_ROUGE(new Location(Bukkit.getWorld("world"), -5.5, 65, 9.5, -180, 0)),
    UPGRADE_JAUNE(new Location(Bukkit.getWorld("world"), 9.5, 65, 6.5, 90, 0)),
    UPGRADE_VERT(new Location(Bukkit.getWorld("world"), -8.5, 65, -5.5, -90, 0)),

    ;

    private Location location;

    Locations(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
