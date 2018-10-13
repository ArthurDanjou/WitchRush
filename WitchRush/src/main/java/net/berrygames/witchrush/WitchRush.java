package net.berrygames.witchrush;

import net.berrygames.witchrush.commands.CommandsManager;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.listeners.ListenersManager;
import net.berrygames.witchrush.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class WitchRush extends JavaPlugin {

    private static WitchRush instance;

    private TeamManager teamManager;
    private boolean forcedStart;
    private File file;
    private FileConfiguration conf;

    @Override
    public void onEnable() {
        instance = this;

        GameState.setStatus(GameState.LOBBY);
        this.teamManager = new TeamManager();
        this.forcedStart = false;

        new CommandsManager().register(this);
        new ListenersManager().register(this);

        saveDefaultConfig();

        this.createYML(getConfig().getString("game.mode"));

        System.out.println("*-*-*-*-*-*-*-*");
        System.out.println("WitchRush");
        System.out.println("by BunS");
        System.out.println("Active");
        System.out.println("*-*-*-*-*-*-*-*");

        super.onEnable();
    }

    private void createYML(String name) {
        this.file = new File(getDataFolder(), name+".yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            saveResource(name+".yml", false);
        }

        this.conf = new YamlConfiguration();
        try {
            conf.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void setConf(String path, Object value){
        this.conf.set(path, value);
        try {
            this.conf.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConf(){
        return conf;
    }

    @Override
    public void onDisable() {
        System.out.println("*-*-*-*-*-*-*-*");
        System.out.println("WitchRush");
        System.out.println("by BunS");
        System.out.println("Desactive");
        System.out.println("*-*-*-*-*-*-*-*");
        super.onDisable();

        Bukkit.getOnlinePlayers().forEach(pls -> pls.kickPlayer(ChatColor.RED+"Le serveur redémarre"));
        Bukkit.getWorld("world").getEntities().forEach(en -> en.remove());
    }

    @Override
    public File getFile() {
        return file;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }
    public boolean isForcedStart() {
        return forcedStart;
    }
    public void setForcedStart(boolean forcedStart) {
        this.forcedStart = forcedStart;
    }

    public static WitchRush get() {
        return instance;
    }

    public static String prefix(){
        return "§5§lWitchRush §d§l\u258f §d";
    }
}
