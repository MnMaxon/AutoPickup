package com.philderbeast.autopickup.util;

import com.philderbeast.autopickup.Config;
import org.bukkit.ChatColor;

public enum Message
{
    ERROR0NO_PERM("&cYou do not have permission to do that!"), 
    SUCCESS0RELOADED("&aPlugin reloaded!"), 
    SUCCESS0TOGGLE0PICKUP_OFF("&aAuto Pickup &cDISABLED&a!"), 
    SUCCESS0TOGGLE0PICKUP_ON("&aAuto Pickup ENABLED!"), 
    SUCCESS0TOGGLE0BLOCK_OFF("&aAuto Block &cDISABLED&a!"), 
    SUCCESS0TOGGLE0BLOCK_ON("&aAuto Block ENABLED!"), 
    SUCCESS0TOGGLE0NOTIFY_OFF("&aFull Notify &cDISABLED&a!"), 
    SUCCESS0TOGGLE0NOTIFY_ON("&aFull Notify ENABLED!"), 
    SUCCESS0TOGGLE0SMELT_OFF("&aAuto Smelt &cDISABLED&a!"), 
    SUCCESS0TOGGLE0SMELT_ON("&aAuto Smelt ENABLED!"), 
    SUCCESS0BLOCKED_INVENTORY("&aYour inventory has been auto blocked!"), 
    ERROR0BLOCKED_INVENTORY("&cNothing in your inventory could be auto blocked!"), 
    SUCCESS0SMELTED_INVENTORY("&aYour inventory has been auto smelted!"), 
    ERROR0SMELTED_INVENTORY("&cNothing in your inventory could be auto smelted!"), 
    ERROR0FULL_INVENTORY(ChatColor.RED + "Your inventory is full!"), 
    ERROR0BLACKLISTED0WORLD("&cYou are not allowed to do that in this world!"), 
    SUCCESS0TOGGLE0AUTOSELL_ON("&aAutoSell ENABLED!"), 
    SUCCESS0TOGGLE0AUTOSELL_OFF("&aAutoSell &cDISABLED&a!"), 
    ERROR0NO_QUICKSELL("&cSorry, this command requires the plugin QuickSell"); 

    String defaultMessage = ""; 
    String message = defaultMessage; 

    Message(String defaultMessage)
    {
        this.defaultMessage = colorize(defaultMessage); 
        this.message = defaultMessage; 
    }

    public static void setup()
    {
        boolean update = false; 
        for (Message message:values())
        {
            if (message.reload())
            {
                update = true; 
            }
        }
        if (update)
        {
            Config.saveAll();
        }
    }

    private boolean reload()
    {
        String path = name().replace("_", " ").replace("0", "."); 
        message = colorize(Config.messageConfig.getString(path)); 
        if (message != null)
        {
            return false; 
        }
        message = defaultMessage; 
        Config.messageConfig.set(path, decolorize(defaultMessage));
        return true; 
    }

    private static String decolorize(String msg)
    {
        if (msg == null)
        {
            return null;
        }

        return ChatColor.translateAlternateColorCodes('\u00a7', msg).replaceAll("Å", "");
    }

    private static String colorize(String msg)
    {
        if (msg == null)
        {
            return null; 
        }
        return ChatColor.translateAlternateColorCodes('&', msg); 
    }

    public String toString()
    {
        return message; 
    }
}
