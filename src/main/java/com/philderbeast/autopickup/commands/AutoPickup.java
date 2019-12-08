package com.philderbeast.autopickup.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.philderbeast.autopickup.AutoPickupPlugin;
import com.philderbeast.autopickup.util.Message;

public class AutoPickup implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Player p = (Player)sender;
        if (args.length == 0)
        {
            Common.openGui(p);
            return true;
        }
        else if (args[0].equalsIgnoreCase("toggle"))
        {
            if ( ! p.hasPermission("AutoPickup.toggle"))
            {
                p.sendMessage(Message.ERROR0NO_PERM + "");
            }
            else if (AutoPickupPlugin.autoPickup.contains(p.getName()))
            {
                AutoPickupPlugin.autoPickup.remove(p.getName());
                p.sendMessage(Message.SUCCESS0TOGGLE0PICKUP_OFF + "");
            }else
            {
                AutoPickupPlugin.autoPickup.add(p.getName());
                p.sendMessage(Message.SUCCESS0TOGGLE0PICKUP_ON + "");
            }
            return true;
        }
        return false;
    }

}

