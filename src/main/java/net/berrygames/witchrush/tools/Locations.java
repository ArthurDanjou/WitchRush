package net.berrygames.witchrush.tools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public enum Locations {

    //PLAYERS
    PLAYER_SPAWN_WAITING_ROOM(new Location(Bukkit.getWorld("world"), 0, 0, 0)),
    PLAYER_SPAWN_BLEU(new Location(Bukkit.getWorld("world"), 0, 0, 0)),
    PLAYER_SPAWN_ROUGE(new Location(Bukkit.getWorld("world"), 0, 0, 0)),
    PLAYER_SPAWN_JAUNE(new Location(Bukkit.getWorld("world"), 0, 0, 0)),
    PLAYER_SPAWN_VERT(new Location(Bukkit.getWorld("world"), 0, 0, 0)),

    SPAWN_SPECTATORS(new Location(Bukkit.getWorld("world"), 0, 0, 0)),

    //WITCH
    WITCH_BLEU(new Location(Bukkit.getWorld("world"), 0, 0, 0)),
    WITCH_ROUGE(new Location(Bukkit.getWorld("world"), 0, 0, 0)),
    WITCH_JAUNE(new Location(Bukkit.getWorld("world"), 0, 0, 0)),
    WITCH_ERT(new Location(Bukkit.getWorld("world"), 0, 0, 0)),

    //PNJ SHOP
    SHOP_BLEU(new Location(Bukkit.getWorld("world"), 0, 0, 0)),
    SHOP_ROUGE(new Location(Bukkit.getWorld("world"), 0, 0, 0)),
    SHOP_JAUNE(new Location(Bukkit.getWorld("world"), 0, 0, 0)),
    SHOP_VERT(new Location(Bukkit.getWorld("world"), 0, 0, 0)),

    //PNJ SOLO UPGRADE
    UPGRADE_BLEU(new Location(Bukkit.getWorld("world"), 0, 0, 0)),
    UPGRADE_ROUGE(new Location(Bukkit.getWorld("world"), 0, 0, 0)),
    UPGRADE_JAUNE(new Location(Bukkit.getWorld("world"), 0, 0, 0)),
    UPGRADE_VERT(new Location(Bukkit.getWorld("world"), 0, 0, 0)),

    ;

    private Location location;

    Locations(Location location) {
        this.location = location;
    }
}
