package me.koba1.serverswitch.Utils;

import jdk.jfr.Frequency;
import me.koba1.serverswitch.Files.Configs;
import me.koba1.serverswitch.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.text.Collator;
import java.util.Collection;
import java.util.List;

public class playertools {

    public static boolean isStaff(ProxiedPlayer p) {
        if(Configs.config.getStringList("names").contains(p.getName()))
            return true;
        return false;
    }

    public static String formatStaffs() {
        String output = "";
        List<String> configNames = Configs.get().getStringList("names");
        java.util.Collections.sort(configNames, Collator.getInstance());
        String design = Configs.get().getString("list_server_design");

        for (String s : configNames) {
            if(isOnline(s)) {
                ProxiedPlayer p = getPlayer(s);
                output += "§a" + design.replace("%player%", p.getName()).replace("%server_name%", convertUpper(p.getServer().getInfo().getName()));
            } else
                output += "§c" + s;

            output += "§7, ";
        }

        return output.substring(0, output.length() - 2);
    }

    public static String convertUpper(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static boolean isOnline(String name) {
        return Main.getInstance().getProxy().getPlayer(name) != null;
    }

    public static boolean isAdded(String name) {
        List<String> list = Configs.get().getStringList("names");
        return list.contains(name);
    }

    public static void addToListAndSave(String name) {
        if(isAdded(name)) return;
        List<String> list = Configs.get().getStringList("names");
        list.add(name);

        Configs.get().set("names", list);
        Configs.save();
    }

    public static void removeAndSave(String name) {
        if(!isAdded(name)) return;
        List<String> list = Configs.get().getStringList("names");
        list.remove(name);

        Configs.get().set("names", list);
        Configs.save();
    }

    public static ProxiedPlayer getPlayer(String name) {
        return Main.getInstance().getProxy().getPlayer(name);
    }

    public static boolean isSameServer(ProxiedPlayer sender, String name) {
        if(isOnline(name)) {
            ProxiedPlayer target = Main.getInstance().getProxy().getPlayer(name);
            if(sender.getServer().getInfo().getName().equalsIgnoreCase(target.getServer().getInfo().getName()))
                return true;
        }
        return false;
    }
}
