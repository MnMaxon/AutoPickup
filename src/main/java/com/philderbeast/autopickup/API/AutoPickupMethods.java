package com.philderbeast.autopickup.API;

import com.philderbeast.autopickup.actions.AutoBlock;
import com.philderbeast.autopickup.AutoPickupPlugin;
import com.philderbeast.autopickup.actions.AutoPickup;
import com.philderbeast.autopickup.util.AutoResult;
import com.philderbeast.autopickup.actions.AutoSmelt;
import com.philderbeast.autopickup.commands.Common;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AutoPickupMethods
{
    public static void openGui(Player player)
    {
        Common.openGui(player);
    }

    public static void autoGive(Player player, ItemStack item)
    {

        if (AutoPickupPlugin.autoBlock.contains(player.getName()))
        {
            blockInventory(player, false);
        }

        if (AutoPickupPlugin.autoSmelt.contains(player.getName()))
        {
            item = smelt(item).getNewItem();
        }

        if (AutoPickupPlugin.autoPickup.contains(player.getName()))
        {
            AutoPickup.pickup(player, item);
        }
    }

    public static AutoResult smelt(ItemStack item)
    {
        return AutoSmelt.smelt(item);
    }

    @Deprecated
    public static void smeltInventory(Player player)
    {
        AutoSmelt.smelt(player);
    }

    @Deprecated
    public static void blockInventory(Player player)
    {
        AutoBlock.block(player);
    }

    public static void blockInventory(Player player, boolean notify)
    {
        AutoBlock.block(player, notify);
    }

    public static void addBlockedItem(Player player, ItemStack is)
    {
        AutoBlock.addItem(player, is);
    }
}
