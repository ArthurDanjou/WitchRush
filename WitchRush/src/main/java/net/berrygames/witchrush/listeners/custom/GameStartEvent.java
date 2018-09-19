package net.berrygames.witchrush.listeners.custom;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameStartEvent extends Event {

    public static final HandlerList handlers;
    private String message;

    public GameStartEvent(final String message) {
        this.message = message;
    }

    public HandlerList getHandlers() {
        return GameStartEvent.handlers;
    }

    public String getStartMessage() {
        return this.message;
    }

    static {
        handlers = new HandlerList();
    }
}
