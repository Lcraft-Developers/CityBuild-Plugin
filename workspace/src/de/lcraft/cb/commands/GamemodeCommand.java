package de.lcraft.cb.commands;

import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Command;
import de.lcraft.cb.utils.User;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GamemodeCommand extends Command {

    public GamemodeCommand(Main plugin) {
        super(plugin);
    }

    @Override
    public boolean run(CommandSender s, org.bukkit.command.Command cmd, String label, String[] args) {
        ArrayList<String> before;

        // /gamemode [Player, Gamemode]
        before = new ArrayList<>();
        before.add("0");
        before.add("1");
        before.add("2");
        before.add("3");
        before.add("survival");
        before.add("creative");
        before.add("adventure");
        before.add("spectator");
        for(User p : Main.users) {
            if(p.getPlayer() != null) {
                before.add(p.getPlayer().getName());
            }
        }
        addTabComplete("gamemode", new String[0], before);
        addTabComplete("gm", new String[0], before);

        // /gamemode [Player] Gamemode
        for(User p : Main.users) {
            before = new ArrayList<>();
            if(p.getPlayer() != null) {
                String[] a = new String[1];
                a[0] = p.getPlayer().getName();
                before.add("0");
                before.add("1");
                before.add("2");
                before.add("3");
                before.add("survival");
                before.add("creative");
                before.add("adventure");
                before.add("spectator");
                addTabComplete("gamemode", a, before);
            }
        }

        if(args.length == 2) {
            String cS = args[0];
            Player cU = Bukkit.getPlayer(cS);
            if(cU != null) {
                User c = Main.getUser(cU.getUniqueId());
                String gm = args[1];
                boolean hasPermissions = false;
                if(s instanceof Player) {
                    Player p = (Player) s;
                    if(hasPermissions(p, "cb.gamemode.other") ||
                            hasPermissions(p, "cb.gamemode.other.*") ||
                            hasPermissions(p, "cb.gamemode.other.0") ||
                            hasPermissions(p, "cb.gamemode.other.1") ||
                            hasPermissions(p, "cb.gamemode.other.2") ||
                            hasPermissions(p, "cb.gamemode.other.3") ||
                            hasPermissions(p, "cb.admin")) {
                        hasPermissions = true;
                    }
                } else {
                    hasPermissions = true;
                }

                if(hasPermissions) {
                    if(gm.equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                        if(s instanceof Player) {
                            if(hasPermissions((Player)s, "cb.admin") || hasPermissions((Player)s, "cb.gamemode.other.*") || hasPermissions((Player)s, "cb.gamemode.other.0")) {
                                c.setGamemode("0");
                                cU.sendMessage(PREFIX + translate(cU, "§aYour gamemode has changed to §6Survival"));
                                s.sendMessage(translate((Player)s, PREFIX + "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Survival").replace("%PLAYER%", cU.getName()));
                            } else {
                                s.sendMessage(NO_PERMISSIONS(((Player) s).getUniqueId()));
                            }
                        } else {
                            c.setGamemode("0");
                            cU.sendMessage(PREFIX + translate(cU, "§aYour gamemode has changed to §6Survival"));
                            s.sendMessage(translate((Player)s, PREFIX + "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Survival").replace("%PLAYER%", cU.getName()));
                        }
                    } else if(gm.equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                        if(s instanceof Player) {
                            if(hasPermissions((Player)s, "cb.admin") || hasPermissions((Player)s, "cb.gamemode.other.*") || hasPermissions((Player)s, "cb.gamemode.other.1")) {
                                c.setGamemode("1");
                                cU.sendMessage(PREFIX + translate(cU, "§aYour gamemode has changed to §6Creative"));
                                s.sendMessage(translate((Player)s, PREFIX + "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Creative").replace("%PLAYER%", cU.getName()));
                            } else {
                                s.sendMessage(NO_PERMISSIONS(((Player) s).getUniqueId()));
                            }
                        } else {
                            c.setGamemode("0");
                            cU.sendMessage(PREFIX + translate(cU, "§aYour gamemode has changed to §6Creative"));
                            s.sendMessage(translate((Player)s, PREFIX + "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Creative").replace("%PLAYER%", cU.getName()));
                        }
                    } else if(gm.equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                        if(s instanceof Player) {
                            if(hasPermissions((Player)s, "cb.admin") || hasPermissions((Player)s, "cb.gamemode.other.*") || hasPermissions((Player)s, "cb.gamemode.other.2")) {
                                c.setGamemode("2");
                                cU.sendMessage(PREFIX + translate(cU, "§aYour gamemode has changed to §6Adventure"));
                                s.sendMessage(translate((Player)s, PREFIX + "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Adventure").replace("%PLAYER%", cU.getName()));
                            } else {
                                s.sendMessage(NO_PERMISSIONS(((Player) s).getUniqueId()));
                            }
                        } else {
                            c.setGamemode("2");
                            cU.sendMessage(PREFIX + translate(cU, "§aYour gamemode has changed to §6Adventure"));
                            s.sendMessage(translate((Player)s, PREFIX + "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Adventure").replace("%PLAYER%", cU.getName()));
                        }
                    } else if(gm.equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                        if(s instanceof Player) {
                            if(hasPermissions((Player)s, "cb.admin") || hasPermissions((Player)s, "cb.gamemode.other.*") || hasPermissions((Player)s, "cb.gamemode.other.0")) {
                                c.setGamemode("3");
                                cU.sendMessage(PREFIX + translate(cU, "§aYour gamemode has changed to §6Spectator"));
                                s.sendMessage(translate((Player)s, PREFIX + "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Spectator").replace("%PLAYER%", cU.getName()));
                            } else {
                                s.sendMessage(NO_PERMISSIONS(((Player) s).getUniqueId()));
                            }
                        } else {
                            c.setGamemode("3");
                            cU.sendMessage(PREFIX + translate(cU, "§aYour gamemode has changed to §6Spectator"));
                            s.sendMessage(translate((Player)s, PREFIX + "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Speactator").replace("%PLAYER%", cU.getName()));
                        }
                    } else {
                        s.sendMessage(getHelpMessage("gm [Player] [0, 1, 2, 3]", "gm [Player] [survival, creative, adventure, spectator]", "gamemode [Player] [0, 1, 2, 3]", "gamemode [Player] [survival, creative, adventure, spectator]"));
                    }
                } else {
                    s.sendMessage(NO_PERMISSIONS(null));
                }
            } else {
                s.sendMessage(NO_PLAYER(null));
            }
        } else if(args.length == 1) {
            if(s instanceof Player) {
                Player p = (Player) s;
                User u = Main.getUser(p.getUniqueId());
                if(hasPermissions(p, "cb.gamemode.self") ||
                        hasPermissions(p, "cb.gamemode.self.*") ||
                        hasPermissions(p, "cb.gamemode.self.0") ||
                        hasPermissions(p, "cb.gamemode.self.1") ||
                        hasPermissions(p, "cb.gamemode.self.2") ||
                        hasPermissions(p, "cb.gamemode.self.3") ||
                        hasPermissions(p, "cb.admin")) {
                    if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                        if(hasPermissions(p, "cb.admin") || hasPermissions(p, "cb.gamemode.self.*") || hasPermissions(p, "cb.gamemode.self.0")) {
                            u.setGamemode("0");
                            p.sendMessage(PREFIX + translate(p, "§aYour gamemode has changed to §6Survival"));
                        } else {
                            p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                        }
                    } else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                        if(hasPermissions(p, "cb.admin") || hasPermissions(p, "cb.gamemode.self.*") || hasPermissions(p, "cb.gamemode.self.1")) {
                            u.setGamemode("1");
                            p.sendMessage(PREFIX + translate(p, "§aYour gamemode has changed to §6Creative"));
                        } else {
                            p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                        }
                    } else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                        if(hasPermissions(p, "cb.admin") || hasPermissions(p, "cb.gamemode.self.*") || hasPermissions(p, "cb.gamemode.self.2")) {
                            u.setGamemode("2");
                            p.sendMessage(PREFIX + translate(p, "§aYour gamemode has changed to §6Adventure"));
                        } else {
                            p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                        }
                    } else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                        if(hasPermissions(p, "cb.admin") || hasPermissions(p, "cb.gamemode.self.*") || hasPermissions(p, "cb.gamemode.self.3")) {
                            u.setGamemode("3");
                            p.sendMessage(PREFIX + translate(p, "§aYour gamemode has changed to §6Spectator"));
                        } else {
                            p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                        }
                    } else {
                        p.sendMessage(getHelpMessage(p, "gm [0, 1, 2, 3]", "gm [survival, creative, adventure, spectator]", "gamemode [0, 1, 2, 3]", "gamemode [survival, creative, adventure, spectator]"));
                    }
                } else {
                    p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                }
            } else {
                s.sendMessage(NO_PLAYER(null));
            }
        } else {
            s.sendMessage(getHelpMessage("gamemode [Gamemode]", "gamemode [Player] [Gamemode]", "gm [Gamemode]", "gm [Player] [Gamemode]"));
        }

        return false;
    }

}
