package me.koba1.serverswitch.Listeners;

import me.koba1.serverswitch.Files.Configs;
import me.koba1.serverswitch.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;

public class BungeeJoin implements Listener {

    private static Main m = Main.getInstance();

    @EventHandler
    public void onProxyJoin(ServerConnectEvent e) {
        if(e.getReason() == ServerConnectEvent.Reason.JOIN_PROXY) {
            ProxiedPlayer p = e.getPlayer();
            String message = ChatColor.translateAlternateColorCodes('&', Configs.get().getString("bungee_connected")).replace("%player%", p.getName());
            List<String> admins = Configs.get().getStringList("names");

            for (ProxiedPlayer all : m.getProxy().getPlayers()) {
                if(admins.contains(all.getName())) {
                    all.sendMessage(message);
                }
            }
        }
    }
}
