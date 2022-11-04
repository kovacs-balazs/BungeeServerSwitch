package me.koba1.serverswitch.Commands;

import me.koba1.serverswitch.Files.Configs;
import me.koba1.serverswitch.Utils.playertools;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

public class Command_main extends Command {

    public Command_main() {
        super("proxystaff" /*"serverswitch.command.proxystaff", "ps, proxys, pstaff, proxystaffs"*/);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (playertools.isStaff(p)) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("help")) {
                        for (String text : Configs.get().getStringList("help_box"))
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
                    }
                    //
                    else if(args[0].equalsIgnoreCase("list")) {
                        String message = Configs.get().getString("list")
                                .replace("%list%", playertools.formatStaffs());
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                    }
                }
                //
                else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("add")) {
                        if(playertools.isOnline(args[1])) {
                            ProxiedPlayer target = playertools.getPlayer(args[1]);
                            if(!playertools.isAdded(args[1])) {
                                playertools.addToListAndSave(args[1]);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Configs.get().getString("staff_added")
                                        .replace("%player%", target.getName())));
                            } else
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Configs.get().getString("staff_already_added")
                                        .replace("%player%", target.getName())));
                        } else p.sendMessage(ChatColor.translateAlternateColorCodes('&', Configs.get().getString("player_not_found")
                                .replace("%player%", args[1])));
                    }
                    //
                    else if(args[0].equalsIgnoreCase("remove")) {
                        if(playertools.isAdded(args[1])) {
                            playertools.removeAndSave(args[1]);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Configs.get().getString("staff_removed")
                                    .replace("%player%", args[1])));
                        } else p.sendMessage(ChatColor.translateAlternateColorCodes('&', Configs.get().getString("staff_not_added")
                                .replace("%player%", args[1])));
                    }
                } else
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Configs.get().getString("unknown_command")));
            }
        }
    }
}
