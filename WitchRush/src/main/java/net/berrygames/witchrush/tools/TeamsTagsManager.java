package net.berrygames.witchrush.tools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Collection;
import java.util.UUID;

public class TeamsTagsManager {


    private String prefix;
    private String suffix;
    private Team team;
    public static Scoreboard scoreboard;

    public TeamsTagsManager(final String name, final String prefix, final String suffix, final Scoreboard current) throws Exception {
        this.prefix = prefix;
        this.suffix = suffix;
        this.team = current.getTeam(name);
        if (this.team == null) {
            this.team = current.registerNewTeam(name);
        }
        TeamsTagsManager.scoreboard = current;
        this.team.setCanSeeFriendlyInvisibles(false);
        this.team.setAllowFriendlyFire(false);
        int prefixLength = 0;
        int suffixLength = 0;
        if (prefix != null) {
            prefixLength = prefix.length();
        }
        if (suffix != null) {
            suffixLength = suffix.length();
        }
        if (prefixLength + suffixLength >= 32) {
            throw new Exception("prefix and suffix lenghts are greater than 16");
        }
        if (suffix != null) {
            this.team.setSuffix(ChatColor.translateAlternateColorCodes('&', suffix));
        }
        if (prefix != null) {
            this.team.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix));
        }
    }

    public TeamsTagsManager(final String name, final String prefix, final String suffix) throws Exception {
        this(name, prefix, suffix, Bukkit.getScoreboardManager().getMainScoreboard());
    }

    public void set(final Player player) {
        this.team.addPlayer((OfflinePlayer)player);
        player.setScoreboard(TeamsTagsManager.scoreboard);
    }

    public void remove(final Player player) {
        this.team.removePlayer((OfflinePlayer)player);
    }

    public void resetTagUtils(final UUID uuid) {
        this.remove(Bukkit.getPlayer(uuid));
    }

    public void setAll(final Collection<Player> players) {
        for (final Player player : players) {
            this.set(player);
        }
    }

    public void setAll(final Player[] players) {
        for (final Player player : players) {
            this.set(player);
        }
    }

    public void setPrefix(final String prefix) {
        this.prefix = ChatColor.translateAlternateColorCodes('&', prefix);
        this.team.setPrefix(this.prefix);
    }

    public void setSuffix(final String suffix) {
        this.suffix = ChatColor.translateAlternateColorCodes('&', suffix);
        this.team.setSuffix(this.suffix);
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public Team getTeam() {
        return this.team;
    }

    public void removeTeam() {
        this.team.unregister();
    }

    public Scoreboard getScoreboard() {
        return TeamsTagsManager.scoreboard;
    }

    public static void setNameTag(final Player player, final String name, final String prefix) {
        try {
            final TeamsTagsManager golemaplayer = new TeamsTagsManager(name, prefix, null);
            golemaplayer.set(player);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
