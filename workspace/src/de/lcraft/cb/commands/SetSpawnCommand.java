package de.lcraft.cb.commands;

import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Command;
import de.lcraft.cb.utils.Config;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends Command {

    public SetSpawnCommand(Main plugin) {
        super(plugin);
    }

    @Override
    public boolean run(CommandSender s, org.bukkit.command.Command cmd, String label, String[] args) {
        if(s instanceof Player) {
            Player p = (Player) s;
            if(args.length == 0) {
                if(hasPermissions(p, "cb.admin") || hasPermissions(p, "cb.command.setspawn") || hasPermissions(p, "cb.command.admin")) {
                    Config cfg = new Config("spawn.yml");
                    cfg.saveLocation("spawn.loc", p.getLocation());
                    p.sendMessage(PREFIX + translate(p, "§aThe Spawn is now setted."));
                } else {
                    p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                }
            } else {
                p.sendMessage(getHelpMessage(p, "setspawn"));
            }
        } else {
            s.sendMessage(NO_PLAYER(null));
        }
        return false;
    }

}
