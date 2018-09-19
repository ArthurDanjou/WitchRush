package net.berrygames.witchrush.listeners.custom;

import net.berrygames.witchrush.team.TeamInfos;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BossDeathEvent extends Event {

    public static final HandlerList handlers;
    private TeamInfos teamInfos;
    private Player player;

    public BossDeathEvent(final TeamInfos teamInfos, final Player player) {
        this.teamInfos = teamInfos;
        this.player = player;
    }

    public HandlerList getHandlers() {
        return BossDeathEvent.handlers;
    }

    public TeamInfos getTeamKiller() {
        return this.teamInfos;
    }

    public Player getPlayerKiller() {
        return this.player;
    }

    static {
        handlers = new HandlerList();
    }
}
