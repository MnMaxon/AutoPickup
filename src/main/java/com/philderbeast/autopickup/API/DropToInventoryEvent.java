package com.philderbeast.autopickup.API;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by MnMaxon on 10/10/2015.  Aren't I great?
 */
public class DropToInventoryEvent extends Event
{
    private final ArrayList<ItemStack> items;
    private final Player player;
    private boolean cancelled = false;
    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }

    public DropToInventoryEvent(Player player, ArrayList<ItemStack> items)
    {
        super();
        this.player=player;
        this.items = items;
    }

    public ArrayList<ItemStack> getItems()
    {
        return items;
    }

    public boolean isCancelled()
    {
        return cancelled;
    }

    public void setCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }

    public Player getPlayer()
    {
        return player;
    }
}
