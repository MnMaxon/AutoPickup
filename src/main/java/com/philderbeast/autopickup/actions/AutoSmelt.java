package com.philderbeast.autopickup.actions;

import com.philderbeast.autopickup.Config;
import com.philderbeast.autopickup.util.Message;
import com.philderbeast.autopickup.util.AutoResult;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.Iterator;

@SuppressWarnings("ALL")
public class AutoSmelt
{

    public static AutoResult smelt(ItemStack is)
    {
        if (is == null)
        {
            return new AutoResult(null, false);
        }

        Iterator<Recipe> iter = Bukkit.recipeIterator();
        while (iter.hasNext())
        {
            Recipe recipe = iter.next();
            if (!(recipe instanceof FurnaceRecipe))
            {
                continue;
            }
            if (((FurnaceRecipe) recipe).getInput().getType() != is.getType())
            {
                continue;
            }
            ItemStack newItem = recipe.getResult();
            if ((!Config.smeltList.isEmpty()
                && !Config.smeltList.contains(is.getType().name())) || (Config.smeltBlacklist.containsKey(newItem.getType())
                && (Config.smeltBlacklist.get(newItem.getType()) < 0 || Config.smeltBlacklist.get(newItem.getType()) == newItem.getDurability())))
            {
                return new AutoResult(is, false);
            }
            newItem.setAmount(is.getAmount());
            return new AutoResult(newItem, true);
        }
        return new AutoResult(is, false);
    }

    public static void smelt(Player p)
    {
        if (p == null || !p.isValid())
        {
            return;
        }
        boolean changed = false;
        ItemStack[] cont = p.getInventory().getContents();
        for (int i = 0; i < cont.length; i++)
        {
            AutoResult result = smelt(cont[i]);
            if (result.isChanged())
            {
                changed = true;
                cont[i] = result.getNewItem();
            }
        }
        if (changed)
        {
            p.getInventory().setContents(cont);
            p.updateInventory();
            p.sendMessage(Message.SUCCESS0SMELTED_INVENTORY + "");
        } else
        {
            p.sendMessage(Message.ERROR0SMELTED_INVENTORY + "");
        }
    }
}
