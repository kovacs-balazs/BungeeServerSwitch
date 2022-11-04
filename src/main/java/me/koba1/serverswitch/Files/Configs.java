package me.koba1.serverswitch.Files;

import me.koba1.serverswitch.Main;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;

public class Configs {

    public static Main m = Main.getInstance();
    public static Configuration config;
    public static File file;

    public static void setup() {
        try {
            file = new File(m.getDataFolder(), "config.yml");
            if (!m.getDataFolder().exists())
                m.getLogger().info("Created config folder: " + m.getDataFolder().mkdir());

            if(!file.exists())
                m.getLogger().info("Created config file: " + file.createNewFile());

            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

            if (!config.contains("names"))
                config.set("names", Arrays.asList(
                        "name1", "name2", "name3", "more"
                ));

            if (!config.contains("server_switch"))
                config.set("server_switch", "&8(&cTeam&8) &e%player% &7switch server: &c%from% &7-> &c%to%");

            if (!config.contains("bungee_connected"))
                config.set("bungee_connected", "&8(&cTeam&8) &e%player% &7joined to proxy!");

            if (!config.contains("disconnect"))
                config.set("disconnect", "&8(&cTeam&8) &e%player% &7disconnected!");

            if (!config.contains("help_box"))
                config.set("help_box", Arrays.asList(
                        "&eServer Switch Broadcaster for Staffs",
                        " ",
                        "&7Commands:",
                        "&e/proxystaff add <playername> - &7Add player to staff list",
                        "&e/proxystaff remove <playername> - &7Remove player from staff list",
                        "&e/proxystaff list - &7List of added staffs"
                ));

            if (!config.contains("list"))
                config.set("list", "&eStaffs: %list%");

            if (!config.contains("list_server_design"))
                config.set("list_server_design", "%player% &8(&c%server_name%&8)");

            if (!config.contains("staff_added"))
                config.set("staff_added", "&8(&cTeam&8) &a%player% successfully added to staffs!");

            if (!config.contains("staff_already_added"))
                config.set("staff_already_added", "&8(&cTeam&8) &c%player% is already added!");

            if (!config.contains("staff_removed"))
                config.set("staff_removed", "&8(&cTeam&8) &a%player% successfully removed from staffs!");

            if (!config.contains("staff_not_added"))
                config.set("staff_not_added", "&8(&cTeam&8) &c%player% is not added to list!");

            if (!config.contains("unknown_command"))
                config.set("unknown_command", "&8(&cTeam&8) &cUnknown command. Please type /proxystaff help to list commands!");

            if (!config.contains("player_not_found"))
                config.set("player_not_found", "&8(&cTeam&8) &c%player% not found!");

            save();
        } catch (IOException e) {
            // owww
            e.printStackTrace();
        }
    }

    public static Configuration get() {
        return config;
    }

    public static void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
