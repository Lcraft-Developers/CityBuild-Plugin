package de.lcraft.cb.manager;

import de.lcraft.cb.utils.Config;
import org.bukkit.Location;
import java.util.ArrayList;

public class WarpSystem {

    private Config warps;

    public WarpSystem() {
        warps = new Config("warps.yml");
    }

    public void setWarp(Warp w) {
        warps.cfg().set("warps." + w.getName() + "." + ".enabled", w.isEnabled());
        warps.save();
        warps.saveLocation("warps." + w.getName() + ".loc", w.getLoc());
    }

    public boolean delWarp(String name) {
        if(existsWarp(name)) {
            Warp w = getWarp(name);
            w.setEnabled(false);
            setWarp(w);
            return true;
        } else {
            return false;
        }
    }

    public boolean existsWarp(String name) {
        if(getWarp(name) != null) {
            return true;
        }
        return false;
    }

    public Warp getWarp(String name) {
        for(Warp c : getAllWarps()) {
            if(c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Warp> getAllWarps() {
        ArrayList<Warp> all = new ArrayList<>();
        if(warps.cfg().contains("warps")) {
            if(warps.cfg().contains("warps")) {
                for(String name : warps.cfg().getConfigurationSection("warps").getKeys(false)) {
                    String root = "warps." + name + ".name";
                    Location loc = warps.getLocation(root + ".loc");
                    boolean enabled = warps.cfg().getBoolean(root + ".enabled");
                    all.add(new Warp(name, loc, enabled));
                }
            }
        }
        return all;
    }

    public class Warp {

        private String name;
        private Location loc;
        private boolean enabled;

        public Warp(String name, Location loc, boolean enabled) {
            this.name = name;
            this.loc = loc;
            this.enabled = enabled;
        }

        public String getName() {
            return name;
        }

        public Location getLoc() {
            return loc;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

    }

}
