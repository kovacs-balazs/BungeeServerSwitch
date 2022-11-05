package me.koba1.serverswitch.Listeners;

import me.koba1.serverswitch.Files.Configs;
import me.koba1.serverswitch.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;

public class DisconnectEvent implements Listener {

    private Main m = Main.getInstance();

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent e) {
        ProxiedPlayer p = e.getPlayer();
        String message = ChatColor.translateAlternateColorCodes('&', Configs.get().getString("disconnect")).replace("%player%", p.getName());
        List<String> admins = Configs.get().getStringList("names");
        if(!admins.contains(p.getName())) return;

        for (ProxiedPlayer all : m.getProxy().getPlayers()) {
            if(admins.contains(all.getName())) {
                all.sendMessage(message);
            }
        }
    }
}
