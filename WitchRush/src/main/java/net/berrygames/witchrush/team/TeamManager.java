package net.berrygames.witchrush.team;

import net.berrygames.witchrush.WitchPlayer;
import net.berrygames.witchrush.tools.Locations;
import net.berrygames.witchrush.tools.WitchBoss;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class TeamManager {

    private Map<TeamInfos, List<Player>> playerTeamList;
    private Map<TeamInfos, WitchBoss> bossEntityMap;

    public TeamManager() {
        this.playerTeamList = new HashMap<>();
        this.bossEntityMap = new HashMap<>();
    }

    public void addPlayerTeam(final Player player, final TeamInfos teamInfos) {
        if (this.playerTeamList.get(teamInfos) == null) {
            this.playerTeamList.put(teamInfos, new ArrayList<>());
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
        Arrays.stream(TeamInfos.values())
                .filter(teams -> teamIsFull(teams))
                .filter(teams -> (WitchPlayer.getwitchMap().size() / 4) > getTeamPlayerCount(teams)/)
    }

    public boolean isInLife(final TeamInfos teamInfos) {
        if(getBossEntityMap().containsKey(teamInfos)){
            return true;
        }
        return false;
    }

    public Location getBossLocation(final TeamInfos teamInfos) {
        switch (teamInfos){
            case ROUGE:
                return Locations.WITCH_ROUGE.getLocation();
            case BLEU:
                return Locations.WITCH_BLEU.getLocation();
            case VERT:
                return Locations.WITCH_VERT.getLocation();
            case JAUNE:
                return Locations.WITCH_JAUNE.getLocation();
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
