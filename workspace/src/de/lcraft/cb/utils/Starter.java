package de.lcraft.cb.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.plugin.java.JavaPlugin;

public class Starter {

    public static String PREFIX = "§7[§6CityBuild§7] §r",
            START_PREFIX = " §7>> §r",
            NO_PERMISSIONS = "§cYou do not have permissions for that!",
            NO_PLAYER = "§cYou must be a player to use that!",
            NO_COMMAND_FOUND = "§cThat command do not exists!",
            NO_NUMBER = "§cThats not a normal number!",
            ON_LOAD = PREFIX + "§aThe Plugin is loaded.",
            ON_START = PREFIX + "§aThe Plugin is started.",
            ON_STOP = PREFIX + "§aThe Plugin is stopped.",
            NO_PLAYER_FOUND = "§cThis Player doesn't exists!";

    public Config startPlugin(Config mainCFG, JavaPlugin plugin) {
        try {
            mainCFG = new Config("config.yml");
            PREFIX = (String) Config.getOption(mainCFG, "challenges.PREFIX", PREFIX);
            START_PREFIX = (String) Config.getOption(mainCFG, "challenges.START_PREFIX", START_PREFIX);
            NO_PERMISSIONS = (String) Config.getOption(mainCFG, "challenges.NO_PERMISSIONS", NO_PERMISSIONS);
            NO_PLAYER = (String) Config.getOption(mainCFG, "challenges.NO_PLAYER", NO_PLAYER);
            NO_COMMAND_FOUND = (String) Config.getOption(mainCFG, "challenges.NO_COMMAND_FOUND", NO_COMMAND_FOUND);
            NO_NUMBER = (String) Config.getOption(mainCFG, "challenges.NO_NUMBER", NO_NUMBER);
            NO_PLAYER_FOUND = (String) Config.getOption(mainCFG, "challenges.NO_PLAYER_FOUND", NO_PLAYER_FOUND);

            ON_LOAD = (String) Config.getOption(mainCFG, "challenges.ON_LOAD", ON_LOAD);
            ON_START = (String) Config.getOption(mainCFG, "challenges.ON_START", ON_START);
            ON_STOP = (String) Config.getOption(mainCFG, "challenges.ON_STOP", ON_STOP);

            Bukkit.broadcastMessage(ON_LOAD);
            return mainCFG;
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.broadcastMessage("§cERRRRRROR!!!");
        }
        return null;
    }

    public static void sendActionBar(Player p, String msg){
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(msg));
    }

}