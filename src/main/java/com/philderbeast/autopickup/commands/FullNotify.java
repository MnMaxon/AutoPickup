package com.philderbeast.autopickup.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.philderbeast.autopickup.AutoPickupPlugin;
import com.philderbeast.autopickup.util.Message;

public class FullNotify implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Player p = (Player)sender;
        if (!p.hasPermission("FullNotify.command"))
        {
            p.sendMessage(Message.ERROR0NO_PERM + "");
            return true;
        }else if (args.length == 0)
        {
            return false;
        } else if (args.length == 1 && args[0].equalsIgnoreCase("toggle"))
        {
            if (!p.hasPermission("FullNotify.toggle"))
            {
                p.sendMessage(Message.ERROR0NO_PERM + "");
            } else if (AutoPickupPlugin.fullNotify.contains(p.getName()))
            {
                AutoPickupPlugin.fullNotify.remove(p.getName());
                p.sendMessage(Message.SUCCESS0TOGGLE0NOTIFY_OFF + "");
            } else
            {
                AutoPickupPlugin.fullNotify.add(p.getName());
                p.sendMessage(Message.SUCCESS0TOGGLE0NOTIFY_ON + "");
            }
            return true;
        }
        return false;
    }

}