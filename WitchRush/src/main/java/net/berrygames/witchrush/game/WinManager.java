package net.berrygames.witchrush.game;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.team.TeamManager;
import org.bukkit.Bukkit;

public class WinManager {

    public WinManager() {
        final TeamManager teamManager = WitchRush.get().getTeamManager();
        if (WitchRush.get().getState().equals(GameState.NOWITCH)
                || WitchRush.get().getState().equals(GameState.PVP)
                || WitchRush.get().getState().equals(GameState.DEATH_MATCH)) {
            int teamLeft = 0;
            for (final TeamInfos teamInfos : TeamInfos.values()) {
                if (teamManager.getTeamPlayerCount(teamInfos) >= 1) {
                    ++teamLeft;
                }
                if(teamManager.getPlayerTeamList(teamInfos).size() == 0){
                    teamManager.killTeamBoss(teamInfos);
                }
            }
            if (teamLeft == 1) {
                for (final TeamInfos teamInfos : TeamInfos.values()) {
                    if (!teamManager.getPlayerTeamList(teamInfos).isEmpty()) {
                        WitchRush.get().setState(GameState.FINISHING);
                        Bukkit.broadcastMessage(WitchRush.prefix()+"Victoire de l'équipe "+teamInfos.getChatColor()+teamInfos.getTeamName());
                    }
                }
            }
        }
    }

}
