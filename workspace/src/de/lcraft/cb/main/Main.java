package de.lcraft.cb.main;

import de.lcraft.cb.commands.TPSCommand;
import de.lcraft.cb.languages.LanguagesManager;
import de.lcraft.cb.permissions.PermissionsManager;
import de.lcraft.cb.utils.Command;
import de.lcraft.cb.utils.Config;
import de.lcraft.cb.utils.Starter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.*;
import org.bukkit.event.*;

public class Main extends JavaPlugin {

    private static Main plugin;
    private static Config mainCFG;
    private static LanguagesManager langManager;
    private static PermissionsManager permsManager;

    public void load() {
        plugin = this;
        new Starter().startPlugin(mainCFG, plugin);
        permsManager = new PermissionsManager(plugin);
        langManager = new LanguagesManager();
    }

    public static LanguagesManager getLangManager() {
        return langManager;
    }

    @Override
    public void onEnable() {
        load();
        plugin = this;

        // Register all Commands
        registerCommand("tps", new TPSCommand(plugin));

        Bukkit.broadcastMessage(Starter.ON_START);
    }

    @Override
    public void onDisable() {
        Bukkit.broadcastMessage(Starter.ON_STOP);
        Bukkit.getScheduler().cancelTasks(plugin);
    }

    public void registerCommand(String cmd, Command executor) {
        getCommand(cmd).setExecutor(executor);
    }

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static Config getMainCFG() {
        return mainCFG;
    }

    public static PermissionsManager getPermsManager() {
        return permsManager;
    }

    public static int getHighestY(Location loc) {
        int y = 255;
        while(new Location(loc.getWorld(), loc.getX(), y, loc.getZ()).getBlock().getType() == Material.AIR) {
            y--;
        }
        return y;
    }

}
