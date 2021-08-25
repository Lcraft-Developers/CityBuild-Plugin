package de.lcraft.cb.commands;

import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Command;
import de.lcraft.cb.utils.Config;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand extends Command {

    public SpawnCommand(Main plugin) {
        super(plugin);
    }

    @Override
    public boolean run(CommandSender s, org.bukkit.command.Command cmd, String label, String[] args) {
        if(s instanceof Player) {
            Player p = (Player) s;
            if(args.length == 0) {
                if(hasPermissions(p, "cb.admin") || hasPermissions(p, "cb.command.spawn")  || hasPermissions(p, "cb.command.admin")) {
                    Config cfg = new Config("spawn.yml");
                    Location loc = cfg.getLocation("spawn.loc");
                    if(loc == null) {
                        p.sendMessage(PREFIX + translate(p, "§cThe spawn location has not been set yet."));
                    } else {
                        p.teleport(loc);
                        p.sendMessage(PREFIX + translate(p, "§aYou have been teleported to the spawn."));
                    }
                } else {
                    p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                }
            } else {
                p.sendMessage(getHelpMessage(p, "spawn"));
            }
        } else {
            s.sendMessage(NO_PLAYER(null));
        }
        return false;
    }

}