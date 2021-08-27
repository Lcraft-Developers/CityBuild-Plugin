package de.lcraft.cb.main;

import de.lcraft.cb.commands.GamemodeCommand;
import de.lcraft.cb.commands.SetSpawnCommand;
import de.lcraft.cb.commands.SpawnCommand;
import de.lcraft.cb.commands.TPSCommand;
import de.lcraft.cb.languages.LanguagesManager;
import de.lcraft.cb.listeners.JoinListener;
import de.lcraft.cb.permissions.PermissionsManager;
import de.lcraft.cb.utils.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.*;
import org.bukkit.event.*;
import java.util.ArrayList;
import java.util.UUID;

public class Main extends JavaPlugin {

    private static Main plugin;
    private static Config mainCFG;
    private static LanguagesManager langManager;
    private static PermissionsManager permsManager;
    public static ArrayList<User> users;

    public void load() {
        plugin = this;
        users = new ArrayList<>();
        mainCFG = new Starter().startPlugin(mainCFG, plugin);
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

        for(Player p : Bukkit.getOnlinePlayers()) {
            p.kickPlayer(Config.getOption(mainCFG, "server.reload.msg", "§6Please rejoin").toString());
        }

        if(Internet.SpigotMc.isOutdated(95641, "1.0.2")) {
            Internet.SpigotMc.getLatestVersion(95641, version -> {
                Bukkit.broadcastMessage(Starter.PREFIX + LanguagesManager.translate("§cPlease update. New Version: %NEW%, Current Version: %OLD%", LanguagesManager.getNormalLanguage())
                        .replace("%NEW%", version).replace("%OLD%", "1.0.2"));
            });
        }

        // Register all Listeners
        registerListener(new JoinListener(plugin));

        // Register all Commands
        registerCommand("tps", new TPSCommand(plugin));
        registerCommand("gm", new GamemodeCommand(plugin));
        registerCommand("gamemode", new GamemodeCommand(plugin));
        registerCommand("spawn", new SpawnCommand(plugin));
        registerCommand("setspawn", new SetSpawnCommand(plugin));

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

    public Config getMainCFG() {
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

    public static User getUser(UUID p) {
        for(User c : users) {
            if(c != null && c.getUUID() != null && c.getUUID().equals(p)) {
                return c;
            }
        }
        return null;
    }

}
