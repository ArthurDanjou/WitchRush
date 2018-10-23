package net.berrygames.witchrush.kits;

import net.berrygames.witchrush.tools.ItemFactory;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public enum Kits {

    ElfI("§eElf I", getElfIKit()),
    ElfII("§eElf II", getElfIIKit()),
    ElfIII("§eElf III", getElfIIIKit()),

    GoblinI("§aGoblin I", getGoblinIKit()),
    GoblinII("§aGoblin I", getGoblinIIKit()),
    GoblinIII("§aGoblin I", getGoblinIIIKit()),
    ;

    private String kitName;
    private ArrayList<ItemStack> items;

    Kits(String kitName, ArrayList<ItemStack> items) {
        this.kitName = kitName;
        this.items = items;
    }

    public ArrayList<ItemStack> getItems() {
        return this.items;
    }

    public static ArrayList<ItemStack> getGoblinIKit(){
        ArrayList<ItemStack> list = new ArrayList<>();

        list.add(new ItemFactory(Material.WOOD_SWORD).withName("§bSword Level I").done());
        list.add(new ItemFactory(Material.SANDSTONE, 64).done());

        return list;
    }
    public static ArrayList<ItemStack> getGoblinIIKit(){
        ArrayList<ItemStack> list = new ArrayList<>();

        list.add(new ItemFactory(Material.STONE_SWORD).withName("§bSword Level II").done());
        list.add(new ItemFactory(Material.SANDSTONE, 64).done());

        return list;
    }
    public static ArrayList<ItemStack> getGoblinIIIKit(){
        ArrayList<ItemStack> list = new ArrayList<>();

        list.add(new ItemFactory(Material.IRON_SWORD).withName("§bSword Level III").done());
        list.add(new ItemFactory(Material.SANDSTONE, 64).done());

        return list;
    }

    public static ArrayList<ItemStack> getElfIKit(){
        ArrayList<ItemStack> list = new ArrayList<>();

        list.add(new ItemFactory(Material.BOW).withName("§bArc Level I").done());
        list.add(new ItemFactory(Material.ARROW, 64).done());
        list.add(new ItemFactory(Material.SANDSTONE, 64).done());

        return list;
    }
    public static ArrayList<ItemStack> getElfIIKit(){
        ArrayList<ItemStack> list = new ArrayList<>();

        list.add(new ItemFactory(Material.BOW).withName("§bArc Level II").withEnchant(Enchantment.ARROW_DAMAGE, 1).done());
        list.add(new ItemFactory(Material.ARROW, 64).done());
        list.add(new ItemFactory(Material.SANDSTONE, 64).done());

        return list;
    }
    public static ArrayList<ItemStack> getElfIIIKit(){
        ArrayList<ItemStack> list = new ArrayList<>();

        list.add(new ItemFactory(Material.BOW).withName("§bArc Level III").withEnchant(Enchantment.ARROW_DAMAGE, 2).done());
        list.add(new ItemFactory(Material.ARROW, 64).done());
        list.add(new ItemFactory(Material.SANDSTONE, 64).done());

        return list;
    }
}
