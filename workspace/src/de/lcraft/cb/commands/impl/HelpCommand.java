package de.lcraft.cb.commands.impl;

import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class HelpCommand extends Command {

    public HelpCommand(Main plugin) {
        super(plugin);
    }

    @Override
    public boolean run(CommandSender s, org.bukkit.command.Command cmd, String label, String[] args) {
        return false;
    }

    @Override
    public ArrayList<String> allPermissions(ArrayList<String> allPerms) {
        return null;
    }

    @Override
    public ArrayList<String> allLanguages(ArrayList<String> allLang) {
        return null;
    }

}
