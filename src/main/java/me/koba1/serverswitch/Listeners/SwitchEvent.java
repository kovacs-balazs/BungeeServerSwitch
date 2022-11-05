package me.koba1.serverswitch.Listeners;

import me.koba1.serverswitch.Files.Configs;
import me.koba1.serverswitch.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;

public class SwitchEvent implements Listener {

    private static Main m = Main.getInstance();

    @EventHandler
    public void onProxyJoin(ServerSwitchEvent e) {
        if(e.getFrom() == null) return;
        if(e.getPlayer().getServer() == null) return;
        ProxiedPlayer p = e.getPlayer();

        String message = ChatColor.translateAlternateColorCodes('&', Configs.get().getString("server_switch"))
                .replace("%player%", p.getName())
                .replace("%from%", convertName(e.getFrom()))
                .replace("%to%", convertName(p.getServer().getInfo()));

        List<String> admins = Configs.get().getStringList("names");
        if(!admins.contains(p.getName())) return;

        for (ProxiedPlayer all : m.getProxy().getPlayers()) {
            if (admins.contains(all.getName())) {
                all.sendMessage(message);
            }
        }
    }

    private static String convertName(ServerInfo serverInfo) {
        return serverInfo.getName().substring(0, 1).toUpperCase() + serverInfo.getName().substring(1);
    }
}
