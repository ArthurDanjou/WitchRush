package net.berrygames.witchrush.tools;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
import java.util.List;

public class ItemFactory {

    private ItemStack item;

    public ItemFactory(final ItemStack item) {
        this.item = item;
    }

    public ItemFactory(final Material mat) {
        this.item = new ItemStack(mat);
    }

    public ItemFactory(final Material mat, final int amount) {
        this.item = new ItemStack(mat, amount);
    }

    public ItemFactory withName(final String name) {
        final ItemMeta meta = this.item.getItemMeta();
        meta.setDisplayName(name);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemFactory withLore(final String... lore) {
        final ItemMeta meta = this.item.getItemMeta();
        meta.setLore((List) Arrays.asList(lore));
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemFactory withColor(final DyeColor color) {
        this.item.setDurability((short)color.getWoolData());
        return this;
    }

    public ItemFactory withOwner(final String owner) {
        if (this.item.getType().equals((Object)Material.SKULL_ITEM)) {
            this.item.setDurability((short)3);
            final SkullMeta m = (SkullMeta)this.item.getItemMeta();
            m.setOwner(owner);
            this.item.setItemMeta((ItemMeta)m);
        }
        return this;
    }

    public ItemFactory withAmount(final int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemFactory withEnchant(final Enchantment e, final int lvl) {
        final ItemMeta m = this.item.getItemMeta();
        m.addEnchant(e, lvl, true);
        this.item.setItemMeta(m);
        return this;
    }

    public ItemFactory withEffect(final PotionEffect e) {
        if (!this.item.getType().equals((Object)Material.POTION)) {
            return this;
        }
        final PotionMeta pm = (PotionMeta)this.item.getItemMeta();
        pm.addCustomEffect(e, true);
        this.item.setItemMeta((ItemMeta)pm);
        return this;
    }

    public ItemFactory addFlag(final ItemFlag... f) {
        this.item.getItemMeta().removeItemFlags(f);
        return this;
    }

    public ItemStack done() {
        return this.item;
    }

}
