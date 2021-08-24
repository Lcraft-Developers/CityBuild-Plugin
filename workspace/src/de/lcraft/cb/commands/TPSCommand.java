package de.lcraft.cb.commands;

import de.lcraft.cb.languages.Language;
import de.lcraft.cb.languages.LanguagesManager;
import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Command;
import de.lcraft.cb.utils.TPS;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPSCommand extends Command {

    public TPSCommand(Main plugin) {
        super(plugin);
    }

    @Override
    public boolean run(CommandSender s, org.bukkit.command.Command cmd, String label, String[] args) {
        if(args.length == 0) {
            boolean hasPermissions = true;
            if(s instanceof Player) {
                hasPermissions = hasPermissions((Player) s, "citybuild.commands.tps", "citybuild.commands.admin", "citybuild.admin");
            }

            if(hasPermissions) {
                if(s instanceof Player) {
                    s.sendMessage(PREFIX + LanguagesManager.translate("§aCurrent TPS: §6", ((Player) s).getUniqueId()) + TPS.getTPS());
                } else {
                    s.sendMessage(PREFIX + LanguagesManager.translate("§aCurrent TPS: §6", LanguagesManager.getNormalLanguage()) + TPS.getTPS());
                }
            } else {
                s.sendMessage(NO_PERMISSIONS);
            }
        } else {
            if(s instanceof Player) {
                s.sendMessage(getHelpMessage((Player) s, "tps"));
            } else {
                s.sendMessage(getHelpMessage("tps"));
            }
        }
        return false;
    }

}
