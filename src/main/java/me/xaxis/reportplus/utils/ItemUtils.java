package me.xaxis.reportplus.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    private final ItemStack item;
    private final ItemMeta itemMeta;

    public ItemUtils(Material material){
        item = new ItemStack(material);
        itemMeta = item.getItemMeta();
    }

    public void lore(String @NotNull ... lore){

        List<String> l = new ArrayList<>();

        for(String s : lore){
            l.add(Utils.chat(s));
        }

        Bukkit.getServer().getConsoleSender().sendMessage(l.toString());

        itemMeta.setLore(l);

        item.setItemMeta(itemMeta);

    }

    public void setTitle(String s){
        itemMeta.setDisplayName(Utils.chat(s));
        item.setItemMeta(itemMeta);
    }

    public ItemStack i(){
        return item;
    }



}