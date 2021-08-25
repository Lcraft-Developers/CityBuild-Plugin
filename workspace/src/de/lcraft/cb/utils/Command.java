package de.lcraft.cb.utils;

import de.lcraft.cb.languages.Language;
import de.lcraft.cb.languages.LanguagesManager;
import de.lcraft.cb.main.Main;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Command extends Starter implements CommandExecutor, Listener, TabCompleter {
	
	protected static Main plugin;

	public Command(Main plugin) {
		this.plugin = plugin;
		plugin.registerListener(this);
	}
	
	public abstract boolean run(CommandSender s, org.bukkit.command.Command cmd, String label, String[] args);
	
	@Override
	public boolean onCommand(CommandSender arg0, org.bukkit.command.Command arg1, String arg2, String[] arg3) {
		return run(arg0, arg1, arg2, arg3);
	}

	public boolean hasPermissions(Player p, String perm) {
		return Main.getPermsManager().hasPermissions(p, perm);
	}

	public boolean hasPermissions(Player p, String... perm) {
		boolean a = false;
		for(String c : perm) {
			if(Main.getPermsManager().hasPermissions(p, c)) {
				a = true;
			}
		}
		return a;
	}

	public de.lcraft.cb.utils.TabCompleter addTabComplete(String commandSlah, String[] beforeArgs, ArrayList<String> possebilitis) {
		return new de.lcraft.cb.utils.TabCompleter(plugin, commandSlah, beforeArgs, possebilitis);
	}

	public String getHelpMessage(Language lang, String... help) {
		String end = PREFIX + LanguagesManager.translate("§cPlease use", lang) + " §6/" + help[0];
		for(int i = 1; i < help.length; i++) {
			end = end + " " + LanguagesManager.translate("§cor", lang) + " §6/" + help[i];
		}
		end = end + " §c!";
		if(lang == null) {
			return end;
		}
		return end;
	}

	public String NO_PERMISSIONS(UUID p) {
		if(p == null) {
			return LanguagesManager.translate(NO_PERMISSIONS, LanguagesManager.getNormalLanguage());
		} else {
			return LanguagesManager.translate(NO_PERMISSIONS, p);
		}
	}

	public String NO_PLAYER(UUID p) {
		if(p == null) {
			return LanguagesManager.translate(NO_PLAYER, LanguagesManager.getNormalLanguage());
		} else {
			return LanguagesManager.translate(NO_PLAYER, p);
		}
	}

	public String translate(Player p, String msg) {
		return LanguagesManager.translate(msg, p.getUniqueId());
	}

	public String getHelpMessage(Player p, String... help) {
		return getHelpMessage(LanguagesManager.getPlayer(p.getUniqueId()), help);
	}

	public String getHelpMessage(String... help) {
		return getHelpMessage(LanguagesManager.getNormalLanguage(), help);
	}

	@Override
	public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
		return null;
	}
}
