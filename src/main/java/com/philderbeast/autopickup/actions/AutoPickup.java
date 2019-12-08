/**
 * Copyright 2017 (c) Phillip Ledger <PLB Technology Group> and Contributors.
 * All rights reserved.
 * <p>
 * This file is licensed under the BSD 2-Clause License, which accompanies this project
 * and is available under https://opensource.org/licenses/BSD-2-Clause.
 **/
package com.philderbeast.autopickup.actions;

import com.philderbeast.autopickup.API.DropToInventoryEvent;
import com.philderbeast.autopickup.AutoPickupPlugin;
import com.philderbeast.autopickup.Config;
import com.philderbeast.autopickup.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

public class AutoPickup
{
    public static void pickup(Player player, ItemStack item)
    {
        ArrayList<ItemStack> items = new ArrayList<>();
        items.add(item);
        DropToInventoryEvent die = new DropToInventoryEvent(player, items);
        Bukkit.getServer().getPluginManager().callEvent(die);
        Collection<ItemStack> remaining = new ArrayList<>();

        if (die.isCancelled())
        {
            LocationActions.locations.remove(die.getPlayer().getLocation().getBlock().getLocation());
            for (ItemStack spawn : die.getItems())
            {
                player.getWorld().dropItem(player.getLocation(), spawn);
            }
            return;

        } else
        {
            for (ItemStack give : die.getItems())
            {
                if (AutoPickupPlugin.autoBlock.contains(player.getName()))
                {
                    remaining.addAll(AutoBlock.addItem(player, give).values());
                } else
                {
                    remaining.addAll(Util.giveItem(player, give).values());
                }
            }
        }

        if (!remaining.isEmpty())
        {
            if (!die.isCancelled())
            {
                Util.warn(player);
            }

            if (!Config.deleteOnFull)
            {
                for (ItemStack is : remaining)
                {
                    player.getWorld().dropItem(player.getLocation(), is);
                    return;
                }
            }
            return;
        }
    }
}
