package de.lcraft.cb.listeners;

import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Config;
import de.lcraft.cb.utils.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    private Main plugin;

    public JoinListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        boolean isMsg = (boolean) Config.getOption(plugin.getMainCFG(), "server.join.msg.enabled", false);
        String msg = Config.getOption(plugin.getMainCFG(), "server.join.msg", "&6%PLAYER% &ahas joined the game.").toString().replace("%PLAYER%", e.getPlayer().getDisplayName()).replace("&", "§");
        Main.users.add(new User(e.getPlayer().getUniqueId()));

        if(isMsg) {
            e.setJoinMessage(msg);
        } else {
            e.setJoinMessage("");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        boolean isMsg = (boolean) Config.getOption(plugin.getMainCFG(), "server.leave.msg.enabled", false);
        String msg = Config.getOption(plugin.getMainCFG(), "server.leave.msg", "&6%PLAYER% &ahas left the game.").toString().replace("%PLAYER%", e.getPlayer().getDisplayName()).replace("&", "§");
        Main.users.remove(Main.getUser(e.getPlayer().getUniqueId()));

        if(isMsg) {
            e.setQuitMessage(msg);
        } else {
            e.setQuitMessage("");
        }
    }

}
