package net.berrygames.witchrush.game;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.team.TeamsInfos;
import net.berrygames.witchrush.team.TeamManager;
import org.bukkit.Bukkit;

public class WinManager {

    public WinManager() {
        final TeamManager teamManager = WitchRush.get().getTeamManager();
        if (GameState.getStatus().equals(GameState.GAME)) {
            int teamLeft = 0;
            for (final TeamsInfos teamInfos : TeamsInfos.values()) {
                if (teamManager.getTeamPlayerCount(teamInfos) >= 1) {
                    ++teamLeft;
                }
            }
            if (teamLeft == 1) {
                for (final TeamsInfos teamInfos : TeamsInfos.values()) {
                    if (!teamManager.getPlayerTeamList(teamInfos).isEmpty()) {
                        GameState.setStatus(GameState.END);
                        Bukkit.broadcastMessage(WitchRush.prefix()+"Victoire de l'équipe "+teamInfos.getChatColor()+teamInfos.getTeamName());
                        this.endGame();
                    }
                }
            }
        }
    }

    private void endGame() {
        Bukkit.getScheduler().runTaskLater(WitchRush.get(), ()->{
            Bukkit.getOnlinePlayers().forEach(players -> players.kickPlayer("§CPartie terminée"));
            Bukkit.getServer().reload();
        }, 20 * 10);
    }
}
