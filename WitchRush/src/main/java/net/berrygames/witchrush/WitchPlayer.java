package net.berrygames.witchrush;

import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.team.TeamInfos;
import net.berrygames.witchrush.team.TeamManager;
import net.berrygames.witchrush.tools.ItemFactory;
import net.berrygames.witchrush.tools.Locations;
import net.berrygames.witchrush.tools.ScoreboardSign;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.HashMap;
import java.util.Map;

public class WitchPlayer {

    private static Map<Player, WitchPlayer> witchMap;

    private Player player;
    private boolean spectator;
    private int kills, death;
    private TeamManager manager;

    public WitchPlayer(Player player) {
        this.player = player;
        this.spectator =
                !WitchRush.get().getState().equals(GameState.WAITING) &&
                        !WitchRush.get().getState().equals(GameState.WAITING);
        this.kills = 0;
        this.death = 0;
        this.manager = WitchRush.get().getTeamManager();
    }

    public void teleportToBase(){
        TeamManager teamManager = WitchRush.get().getTeamManager();
        switch (teamManager.getPlayerTeam(player)){
            case ROUGE:
                player.teleport(Locations.PLAYER_SPAWN_ROUGE.getLocation());
                break;
            case VERT:
                player.teleport(Locations.PLAYER_SPAWN_VERT.getLocation());
                break;
            case JAUNE:
                player.teleport(Locations.PLAYER_SPAWN_JAUNE.getLocation());
                break;
            case BLEU:
                player.teleport(Locations.PLAYER_SPAWN_BLEU.getLocation());
                break;
        }
    }

    public void giveStuff(){
        player.getInventory().clear();
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        player.getInventory().setHelmet(getHelmetColor(Material.LEATHER_HELMET, manager.getPlayerTeam(player).getColor()));
        player.getInventory().setChestplate(getHelmetColor(Material.LEATHER_CHESTPLATE, manager.getPlayerTeam(player).getColor()));
        player.getInventory().setLeggings(getHelmetColor(Material.LEATHER_LEGGINGS, manager.getPlayerTeam(player).getColor()));
        player.getInventory().setBoots(getHelmetColor(Material.LEATHER_BOOTS, manager.getPlayerTeam(player).getColor()));
        player.getInventory().setItem(0, sword);
    }

    public void sendLobby(){

    }

    public void sendWaitingStuff() {
        player.getInventory().setItem(8, new ItemFactory(Material.BED)
                .withName("§cRetour au Hub !")
                .withColor(DyeColor.RED)
                .done());
        player.getInventory().setItem(4, new ItemFactory(Material.ARMOR_STAND)
                .withName("§dTeams")
                .done());
    }

    public void sendGameScoreboard(){
        ScoreboardSign scoreboard = new ScoreboardSign(player, "§5§lWitchRush");
        scoreboard.setLine(15, WitchRush.get().getState()+":"+WitchRush.get().getStateTimer());
        scoreboard.setLine(14, "§r ");
        scoreboard.setLine(13, manager.isInLife(TeamInfos.BLEU) ? "§a✔": "§c✖"+ "§bBleu"+ manager.getTeamBoss(TeamInfos.BLEU).getLife());
        scoreboard.setLine(12, manager.isInLife(TeamInfos.ROUGE) ? "§a✔": "§c✖"+ "§cRouge"+ manager.getTeamBoss(TeamInfos.ROUGE).getLife());
        scoreboard.setLine(11, manager.isInLife(TeamInfos.JAUNE) ? "§a✔": "§c✖"+ "§eJaune"+ manager.getTeamBoss(TeamInfos.JAUNE).getLife());
        scoreboard.setLine(10, manager.isInLife(TeamInfos.VERT) ? "§a✔": "§c✖"+ "§aVert"+ manager.getTeamBoss(TeamInfos.VERT).getLife());
        scoreboard.setLine(9, "§r ");
        scoreboard.setLine(8, "§7Kills: "+WitchPlayer.get(player).getKills());
        scoreboard.setLine(7, "§7Morts: "+WitchPlayer.get(player).getDeath());
        scoreboard.setLine(6, "§r ");
        scoreboard.setLine(5, "§6play.berrygames.net");
    }

    public void sendLobbyScoreboard(){
        ScoreboardSign scoreboard = new ScoreboardSign(player, "§5§lWitchRush");
        scoreboard.setLine(14, "§r ");
        scoreboard.setLine(13, "§7Joueurs: ");
        scoreboard.setLine(12, "§d"+WitchPlayer.getwitchMap().size()+"/16");
        scoreboard.setLine(9, "§r ");
        scoreboard.setLine(8, "§6play.berrygames.net");
    }

    public static WitchPlayer get(final Player player) {
        if (WitchPlayer.witchMap.get(player) == null) {
            WitchPlayer.witchMap.put(player, new WitchPlayer(player));
        }
        return WitchPlayer.witchMap.get(player);
    }

    public boolean isSpectator() {
        return spectator;
    }

    public void setSpectator(boolean spectator) {
        this.spectator = spectator;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    private ItemStack getHelmetColor(final Material material, final Color color) {
        final ItemStack itemStack = new ItemStack(material);
        final LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta)itemStack.getItemMeta();
        leatherArmorMeta.setColor(color);
        itemStack.setItemMeta((ItemMeta)leatherArmorMeta);
        return itemStack;
    }

    public static Map<Player, WitchPlayer> getwitchMap() {
        return WitchPlayer.witchMap;
    }

    public void removePlayer(){
        if (WitchPlayer.witchMap.get(player) == null) return;
        witchMap.remove(player);
    }

    static {
        WitchPlayer.witchMap = new HashMap<>();
    }
}
