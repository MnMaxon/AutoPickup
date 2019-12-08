package com.philderbeast.autopickup.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.philderbeast.autopickup.AutoPickupPlugin;
import com.philderbeast.autopickup.actions.AutoSmelt;
import com.philderbeast.autopickup.util.Message;

public class AutoSmeltCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Player p = (Player)sender;
        if (args.length == 0)
        {
            if ( ! p.hasPermission("AutoSmelt.command"))
            {
                p.sendMessage(Message.ERROR0NO_PERM + "");
            }
            else
            {
                AutoSmelt.smelt(p);
            }
            return true;
        } else if (args[0].equalsIgnoreCase("toggle"))
        {
            if ( ! p.hasPermission("AutoSmelt.toggle"))
            {
                p.sendMessage(Message.ERROR0NO_PERM + "");
            } else if (AutoPickupPlugin.autoSmelt.contains(p.getName()))
            {
                AutoPickupPlugin.autoSmelt.remove(p.getName());
                p.sendMessage(Message.SUCCESS0TOGGLE0SMELT_OFF + "");
            }else
            {
                AutoPickupPlugin.autoSmelt.add(p.getName());
                p.sendMessage(Message.SUCCESS0TOGGLE0SMELT_ON + "");
            }
            return true;
        }
        return false;
    }

}

