package net.berrygames.witchrush;

import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.tools.Locations;
import org.bukkit.Color;
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

    public WitchPlayer(Player player) {
        this.player = player;
        this.spectator =
                (WitchRush.get().getState().equals(GameState.WAITING) ||
                        WitchRush.get().getState().equals(GameState.WAITING)) ? false : true;
        this.kills = 0;
        this.death = 0;
    }

    public void teleportPlayer(){
        if(spectator){
            player.teleport(Locations.SPAWN_SPECTATORS.getLocation());
        } else {
            player.teleport(Locations.PLAYER_SPAWN_WAITING_ROOM.getLocation());
        }
    }

    public void giveStuff(){
        player.getInventory().clear();
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        player.getInventory().setItem(0, sword);
    }

    public void sendLobby(){

    }

    public void sendGameScoreboard(){

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
