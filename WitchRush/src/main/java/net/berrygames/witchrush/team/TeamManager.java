package net.berrygames.witchrush.team;

import net.berrygames.witchrush.tools.Locations;
import net.berrygames.witchrush.tools.WitchBoss;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamManager {

    private Map<TeamInfos, List<Player>> playerTeamList;
    private Map<TeamInfos, WitchBoss> bossEntityMap;

    public TeamManager() {
        this.playerTeamList = new HashMap<>();
        this.bossEntityMap = new HashMap<>();
    }

    public void addPlayerTeam(final Player player, final TeamInfos teamInfos) {
        if (this.playerTeamList.get(teamInfos) == null) {
            this.playerTeamList.put(teamInfos, new ArrayList<Player>());
        }
        if (!this.teamIsFull(teamInfos)) {
            this.removePlayerAllTeam(player);
            this.playerTeamList.get(teamInfos).add(player);
        }
    }

    public void removePlayerAllTeam(final Player player){
        for (final TeamInfos teamInfos : TeamInfos.values()) {
            if (this.playerTeamList.get(teamInfos) != null && this.playerTeamList.get(teamInfos).contains(player)) {
                this.playerTeamList.get(teamInfos).remove(player);
            }
        }
    }

    public void removePlayerTeam(final Player player, final TeamInfos teamInfos) {
        if (this.playerTeamList.get(teamInfos) != null && this.playerTeamList.get(teamInfos).contains(player)) {
            this.playerTeamList.get(teamInfos).remove(player);
        }
    }

    public void addPlayerInRandomTeam(final Player player) {

    }

    public Locations getBossLocation(final TeamInfos teamInfos) {
        switch (teamInfos){
            case ROUGE:
                return Locations.WITCH_ROUGE;
            case BLEU:
                return Locations.WITCH_BLEU;
            case VERT:
                return Locations.WITCH_VERT;
            case JAUNE:
                return Locations.WITCH_JAUNE;
        }

        return null;
    }

    public boolean isPlayerInTeam(final Player player, final TeamInfos teamInfos) {
        return this.playerTeamList.get(teamInfos) != null && this.playerTeamList.get(teamInfos).contains(player);
    }

    public boolean playerHaveTeam(final Player player) {
        for (final TeamInfos teamInfos : TeamInfos.values()) {
            if (this.playerTeamList.get(teamInfos) != null && this.playerTeamList.get(teamInfos).contains(player)) {
                return true;
            }
        }
        return false;
    }

    public boolean teamIsFull(final TeamInfos teamInfos) {
        return this.playerTeamList.get(teamInfos) != null && this.playerTeamList.get(teamInfos).size() >= this.getPlayerTeamLimit();
    }

    public TeamInfos getPlayerTeam(final Player player) {
        for (final TeamInfos teamInfos : TeamInfos.values()) {
            if (this.playerTeamList.get(teamInfos) != null && this.playerTeamList.get(teamInfos).contains(player)) {
                return teamInfos;
            }
        }
        return null;
    }

    public Integer getTeamPlayerCount(final TeamInfos teamInfos) {
        if (this.playerTeamList.get(teamInfos) != null) {
            return this.playerTeamList.get(teamInfos).size();
        }
        return 0;
    }

    public List<Player> getPlayerTeamList(final TeamInfos teamInfos) {
        if (this.playerTeamList.get(teamInfos) != null) {
            return this.playerTeamList.get(teamInfos);
        }
        return new ArrayList<Player>();
    }

    public Integer getPlayerTeamLimit() {
        return 4;
    }

    public WitchBoss getTeamBoss(final TeamInfos teamInfos) {
        return this.bossEntityMap.get(teamInfos);
    }

    public Map<TeamInfos, List<Player>> getPlayerTeamList() {
        return this.playerTeamList;
    }

    public Map<TeamInfos, WitchBoss> getBossEntityMap() {
        return this.bossEntityMap;
    }


}
