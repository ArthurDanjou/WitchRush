package net.berrygames.witchrush.team;

import net.berrygames.witchrush.tools.Locations;
import net.berrygames.witchrush.tools.WitchBoss;
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
        if(this.playerTeamList.get(teamInfos) == null) {
            this.playerTeamList.put(teamInfos, new ArrayList<>());
        }
        this.removePlayerAllTeam(player);
        this.playerTeamList.get(teamInfos).add(player);
    }

    public void removePlayerAllTeam(final Player player){
        for(final TeamsInfos teamInfos : TeamsInfos.values()) {
            if (this.playerTeamList.get(teamInfos) != null && this.playerTeamList.get(teamInfos).contains(player)) {
                removePlayerTeam(player, teamInfos);
            }
        }
    }

    public void removePlayerTeam(final Player player, final TeamsInfos teamInfos) {
        if(this.playerTeamList.get(teamInfos) != null && this.playerTeamList.get(teamInfos).contains(player)) {
            this.playerTeamList.get(teamInfos).remove(player);
        }
    }

    public void addPlayerInRandomTeam(final Player player) {
        for(TeamsInfos infos : TeamsInfos.values()){
            if(isPlayerInTeam(player, infos)) return;
        }
        TreeMap<TeamsInfos, List<Player>> teamMap = new TreeMap<>();
        for(TeamsInfos infos : TeamsInfos.values()){
            teamMap.put(infos, getPlayersTeamList(infos));
        }
        addPlayerTeam(player, teamMap.firstKey());
    }

    public boolean isInLife(final TeamsInfos teamInfos) {
        return getBossEntityMap().containsValue(teamInfos);
    }

    public Location getBossLocation(final TeamsInfos teamInfos) {
        switch (teamInfos){
            case JAUNE:
                return Locations.BOSS_JAUNE.getLoc();
            case ROUGE:
                return Locations.BOSS_ROUGE.getLoc();
            case VERT:
                return Locations.BOSS_VERT.getLoc();
            case BLEU:
                return Locations.BOSS_BLEU.getLoc();
        }
        return null;
    }
    public Location getShopLocation(final TeamsInfos teamInfos) {
        switch (teamInfos){
            case JAUNE:
                return Locations.SHOP_JAUNE.getLoc();
            case ROUGE:
                return Locations.SHOP_ROUGE.getLoc();
            case VERT:
                return Locations.SHOP_VERT.getLoc();
            case BLEU:
                return Locations.SHOP_BLEU.getLoc();
        }
        return null;
    }
    public Location getUpgradeLocation(final TeamsInfos teamInfos) {
        switch (teamInfos){
            case JAUNE:
                return Locations.UPGRADE_JAUNE.getLoc();
            case ROUGE:
                return Locations.UPGRADE_ROUGE.getLoc();
            case VERT:
                return Locations.UPGRADE_VERT.getLoc();
            case BLEU:
                return Locations.UPGRADE_BLEU.getLoc();
        }
        return null;
    }

    public boolean isPlayerInTeam(final Player player, final TeamsInfos teamInfos) {
        return this.playerTeamList.get(teamInfos) != null && this.playerTeamList.get(teamInfos).contains(player);
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

    public List<Player> getPlayersTeamList(final TeamsInfos teamInfos) {
        if (this.playerTeamList.get(teamInfos) != null) {
            return this.playerTeamList.get(teamInfos);
        }
        return new ArrayList<>();
    }

    public Integer getPlayerTeamLimit() {
        return 4;
    }

    public WitchBoss getTeamBoss(final TeamsInfos teamInfos) {
        return this.getBossEntityMap().get(teamInfos);
    }

    public Map<TeamsInfos, WitchBoss> getBossEntityMap() {
        return this.bossEntityMap;
    }

}
