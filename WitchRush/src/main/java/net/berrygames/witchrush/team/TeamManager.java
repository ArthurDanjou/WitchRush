package net.berrygames.witchrush.team;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.tools.WitchBoss;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class TeamManager {

    private Map<TeamsInfos, List<Player>> playerTeamList;
    private Map<TeamsInfos, WitchBoss> bossEntityMap;

    public TeamManager() {
        this.playerTeamList = new HashMap<>();
        this.bossEntityMap = new HashMap<>();
    }

    public void addPlayerTeam(final Player player, final TeamsInfos teamInfos) {
        if (this.playerTeamList.get(teamInfos) == null) {
            this.playerTeamList.put(teamInfos, new ArrayList<>());
        }
        if (!this.teamIsFull(teamInfos)) {
            this.removePlayerAllTeam(player);
            this.playerTeamList.get(teamInfos).add(player);
        }
    }

    public void removePlayerAllTeam(final Player player){
        for (final TeamsInfos teamInfos : TeamsInfos.values()) {
            if (this.playerTeamList.get(teamInfos) != null && this.playerTeamList.get(teamInfos).contains(player)) {
                this.playerTeamList.get(teamInfos).remove(player);
            }
        }
    }

    public void removePlayerTeam(final Player player, final TeamsInfos teamInfos) {
        if (this.playerTeamList.get(teamInfos) != null && this.playerTeamList.get(teamInfos).contains(player)) {
            this.playerTeamList.get(teamInfos).remove(player);
        }
    }

    public void addPlayerInRandomTeam(final Player player) {
        TreeMap<TeamsInfos, List<Player>> teamMap = new TreeMap<>();
        for(TeamsInfos infos : TeamsInfos.values()){
            for(Player pls: Bukkit.getOnlinePlayers()){
                if(!isPlayerInTeam(pls , infos)) teamMap.put(infos, getPlayerTeamList(infos));
            }
        }
        if(!playerHaveTeam(player)){
            addPlayerTeam(player, teamMap.firstKey());
        }
    }

    public boolean isInLife(final TeamsInfos teamInfos) {
        return getBossEntityMap().containsKey(teamInfos);
    }

    public Location getBossLocation(final TeamsInfos teamInfos) {
        return new Location(Bukkit.getWorld("world"),
                WitchRush.get().getConf().getDouble("teams."+teamInfos.getTeamName()+".boss.x"),
                WitchRush.get().getConf().getDouble("teams."+teamInfos.getTeamName()+".boss.y"),
                WitchRush.get().getConf().getDouble("teams."+teamInfos.getTeamName()+".boss.z"),
                WitchRush.get().getConf().getLong("teams."+teamInfos.getTeamName()+".boss.yaw"),
                WitchRush.get().getConf().getLong("teams."+teamInfos.getTeamName()+".boss.pitch"));
    }
    public Location getShopLocation(final TeamsInfos teamInfos) {
        return new Location(Bukkit.getWorld("world"),
                WitchRush.get().getConf().getDouble("teams."+teamInfos.getTeamName()+".shop.x"),
                WitchRush.get().getConf().getDouble("teams."+teamInfos.getTeamName()+".shop.y"),
                WitchRush.get().getConf().getDouble("teams."+teamInfos.getTeamName()+".shop.z"),
                WitchRush.get().getConf().getLong("teams."+teamInfos.getTeamName()+".shop.yaw"),
                WitchRush.get().getConf().getLong("teams."+teamInfos.getTeamName()+".shop.pitch"));
    }
    public Location getUpgradeLocation(final TeamsInfos teamInfos) {
        return new Location(Bukkit.getWorld("world"),
                WitchRush.get().getConf().getDouble("teams."+teamInfos.getTeamName()+".upgrade.x"),
                WitchRush.get().getConf().getDouble("teams."+teamInfos.getTeamName()+".upgrade.y"),
                WitchRush.get().getConf().getDouble("teams."+teamInfos.getTeamName()+".upgrade.z"),
                WitchRush.get().getConf().getLong("teams."+teamInfos.getTeamName()+".upgrade.yaw"),
                WitchRush.get().getConf().getLong("teams."+teamInfos.getTeamName()+".upgrade.pitch"));
    }
    public Location getTeamLocation(final TeamsInfos teamInfos) {
        return new Location(Bukkit.getWorld("world"),
                WitchRush.get().getConf().getDouble("teams."+teamInfos.getTeamName()+".spawn.x"),
                WitchRush.get().getConf().getDouble("teams."+teamInfos.getTeamName()+".spawn.y"),
                WitchRush.get().getConf().getDouble("teams."+teamInfos.getTeamName()+".spawn.z"),
                WitchRush.get().getConf().getLong("teams."+teamInfos.getTeamName()+".spawn.yaw"),
                WitchRush.get().getConf().getLong("teams."+teamInfos.getTeamName()+".spawn.pitch"));
    }

    public boolean isPlayerInTeam(final Player player, final TeamsInfos teamInfos) {
        return this.playerTeamList.get(teamInfos) != null && this.playerTeamList.get(teamInfos).contains(player);
    }

    public boolean playerHaveTeam(final Player player) {
        for (final TeamsInfos teamInfos : TeamsInfos.values()) {
            return isPlayerInTeam(player, teamInfos);
        }
        return false;
    }

    public boolean teamIsFull(final TeamsInfos teamInfos) {
        return this.playerTeamList.get(teamInfos) != null && this.playerTeamList.get(teamInfos).size() >= this.getPlayerTeamLimit();
    }

    public TeamsInfos getPlayerTeam(final Player player) {
        for (final TeamsInfos teamInfos : TeamsInfos.values()) {
            if (this.playerTeamList.get(teamInfos) != null && this.playerTeamList.get(teamInfos).contains(player)) {
                return teamInfos;
            }
        }
        return null;
    }

    public Integer getTeamPlayerCount(final TeamsInfos teamInfos) {
        if (this.playerTeamList.get(teamInfos) != null) {
            return this.playerTeamList.get(teamInfos).size();
        }
        return 0;
    }

    public List<Player> getPlayerTeamList(final TeamsInfos teamInfos) {
        if (this.playerTeamList.get(teamInfos) != null) {
            return this.playerTeamList.get(teamInfos);
        }
        return new ArrayList<>();
    }

    public Integer getPlayerTeamLimit() {
        return 4;
    }

    public WitchBoss getTeamBoss(final TeamsInfos teamInfos) {
        return this.bossEntityMap.get(teamInfos);
    }

    public Map<TeamsInfos, WitchBoss> getBossEntityMap() {
        return this.bossEntityMap;
    }

}
