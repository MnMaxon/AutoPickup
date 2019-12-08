package com.philderbeast.autopickup.util;

import org.bukkit.inventory.ItemStack; 

public class AutoResult
{
    private final ItemStack newItem;
    private final boolean changed; 

    public AutoResult(ItemStack newItem, boolean changed)
    {
        this.newItem = newItem;
        this.changed = changed; 
    }

    public ItemStack getNewItem()
    {
        return newItem; 
    }

    public boolean isChanged()
    {
        return changed; 
    }
}
