package net.berrygames.witchrush.listeners.players;

import net.berrygames.witchrush.WitchRush;
import net.berrygames.witchrush.game.GameState;
import net.berrygames.witchrush.game.task.StartTask;
import net.berrygames.witchrush.shop.ShopGui;
import net.berrygames.witchrush.shop.UpgradeGUI;
import net.berrygames.witchrush.team.TeamsMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {

    @EventHandler
    public void pvp(EntityDamageEvent e){
        switch (GameState.getStatus()){
            case LOBBY:
                e.setCancelled(true);
                break;
            case GAME:
                e.setCancelled(false);
                break;
            case END:
                e.setCancelled(true);
                break;
        }
    }

    @EventHandler
    public void pvp(EntityDamageByBlockEvent e){
        switch (GameState.getStatus()){
            case LOBBY:
                e.setCancelled(true);
                break;
            case GAME:
                e.setCancelled(false);
                break;
            case END:
                e.setCancelled(true);
                break;
        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent e){
        switch (GameState.getStatus()){
            case LOBBY:
                e.setCancelled(true);
                break;
            case GAME:
                e.setCancelled(false);
                break;
            case END:
                e.setCancelled(true);
                break;
        }
    }

    @EventHandler
    public void pvp(EntityDamageByEntityEvent e){
        switch (GameState.getStatus()){
            case LOBBY:
                e.setCancelled(true);
                break;
            case GAME:
                e.setCancelled(false);
                break;
            case END:
                e.setCancelled(true);
                break;
        }
    }

    @EventHandler
    public void click(PlayerInteractAtEntityEvent e){
        Player player = e.getPlayer();
        switch (GameState.getStatus()){
            case LOBBY:
                e.setCancelled(true);
                break;
            case GAME:
                e.setCancelled(true);
                if(e.getRightClicked().getName().equals("§6§lSHOP")){
                    player.closeInventory();
                    new ShopGui(player);
                }
                if(e.getRightClicked().getName().equals("§3§lUPGRADE")){
                    player.closeInventory();
                    new UpgradeGUI(player);
                }
                break;
            case END:
                e.setCancelled(true);
                break;
        }
    }

    @EventHandler
    public void click(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack item = e.getItem();
        if(item == null || item.getType() == Material.AIR) return;

        if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)){
            switch (item.getType()){
                case ARMOR_STAND:
                    e.setCancelled(true);
                    new TeamsMenu(player);
                    break;
                case BED:
                    e.setCancelled(true);
                    player.sendMessage("retour au hub soon");
                    break;
                case FEATHER:
                    if(WitchRush.get().isForcedStart())return;
                    if(player.isOp()){
                        switch (GameState.getStatus()){
                            case LOBBY:
                                WitchRush.get().setForcedStart(true);
                                new StartTask().runTaskTimer(WitchRush.get(), 0, 20);
                                Bukkit.broadcastMessage(WitchRush.prefix()+"§c"+player.getName()+" a forcé le démarrage de la partie !");
                                break;
                        }
                    }
                    break;
                    default:break;
            }
        }
    }
}
