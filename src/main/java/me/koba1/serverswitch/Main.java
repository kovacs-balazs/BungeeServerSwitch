package me.koba1.serverswitch;

import me.koba1.serverswitch.Commands.Command_main;
import me.koba1.serverswitch.Files.Configs;
import me.koba1.serverswitch.Listeners.BungeeJoin;
import me.koba1.serverswitch.Listeners.DisconnectEvent;
import me.koba1.serverswitch.Listeners.SwitchEvent;
import net.md_5.bungee.api.plugin.Plugin;

public final class Main extends Plugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Configs.setup();

        getProxy().getPluginManager().registerListener(this, new BungeeJoin());
        getProxy().getPluginManager().registerListener(this, new DisconnectEvent());
        getProxy().getPluginManager().registerListener(this, new SwitchEvent());

        getProxy().getPluginManager().registerCommand(this, new Command_main());
        getLogger().info("Bungee Server Broadcaster Successfully Loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
