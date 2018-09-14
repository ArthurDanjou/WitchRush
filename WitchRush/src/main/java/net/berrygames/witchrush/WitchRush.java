package net.berrygames.witchrush;

import net.berrygames.witchrush.commands.CommandsManager;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.listeners.ListenersManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class WitchRush extends JavaPlugin {

    private static WitchRush instance;

    private GameState gameState;

    @Override
    public void onEnable() {
        instance = this;

        this.gameState = GameState.WAITING;

        new CommandsManager().register(this);
        new ListenersManager().register(this);

        System.out.println("*-*-*-*-*-*-*-*");
        System.out.println("WitchRush");
        System.out.println("by BunS");
        System.out.println("Active");
        System.out.println("*-*-*-*-*-*-*-*");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        System.out.println("*-*-*-*-*-*-*-*");
        System.out.println("WitchRush");
        System.out.println("by BunS");
        System.out.println("Desactive");
        System.out.println("*-*-*-*-*-*-*-*");
        super.onDisable();

        for(Player pls : Bukkit.getOnlinePlayers()){
            pls.kickPlayer(ChatColor.RED+"Le serveur redémarre");
        }
    }

    public void setState(GameState state){
        this.gameState = state;
    }
    public GameState getState(){
        return this.gameState;
    }

    public static WitchRush get() {
        return instance;
    }

    public static String prefix(){
        return "§5§lWitchRush §d§l\u258f §d";
    }
}
